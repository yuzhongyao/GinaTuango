package com.example.ginatuango.views.user;

import com.example.ginatuango.data.entities.User;
import com.example.ginatuango.services.UserService;
import com.example.ginatuango.views.UserLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Route(value = "/user", layout = UserLayout.class)
@PageTitle("Account Details")
@RolesAllowed("USER")
public class UserAccountView extends VerticalLayout implements HasUrlParameter<Integer> {

    private int userId;
    private final UserService userService;
    private Optional<User> user;
    private final transient AuthenticationContext authContext;

    public UserAccountView(UserService userService, AuthenticationContext authenticationContext){
        this.userService = userService;
        this.authContext = authenticationContext;


        user = userService.getUserByUsername(authContext.getAuthenticatedUser(UserDetails.class).get().getUsername());

        String name;
        if(user.isPresent()){
            name = user.get().getName();
        }
        else{
            name = "";
        }

        H1 title = new H1("Welcome " + name);

        this.setWidthFull();
        this.setAlignItems(Alignment.CENTER);
        add(title);
    }


    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {
        this.userId = integer;
    }
}
