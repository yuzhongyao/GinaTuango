package com.example.ginatuango.security;

import com.example.ginatuango.data.entities.User;
import com.example.ginatuango.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

//    NoOpPasswordEncoder for testing
//    BCryptPasswordEncoder
//    Argon2PasswordEncoder
//    DelegatingPasswordEncoder for production
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//    @Autowired
//    private DelegatingPasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder(){
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
            return NoOpPasswordEncoder.getInstance();
    }



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<User> user = userService.getUserByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("Username not found");
        }
        if(passwordEncoder().matches(password, user.get().getPassword())){
            List<GrantedAuthority> authorityList = new ArrayList<>();
            SimpleGrantedAuthority simpleGrantedAuthority;
            UserDetails userDetails;
            //Roles need to be prefixed with ROLE_
            //e.g. @RolesAllowed("ADMIN") requires adding authority("ROLE_ADMIN")
            if(user.get().isAdmin()){
                simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");

            }
            else{
                simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");

            }

            authorityList.add(simpleGrantedAuthority);
            userDetails = new org.springframework.security.core.userdetails.User(
                    user.get().getName(),
                    user.get().getPassword(),
                    authorityList
            );
            return new UsernamePasswordAuthenticationToken(userDetails, password,authorityList);
        }
        else{
            throw new BadCredentialsException("Incorrect login information");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
