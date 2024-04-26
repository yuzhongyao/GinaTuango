package com.example.ginatuango.views.user;

import com.example.ginatuango.data.entities.User;
import com.example.ginatuango.services.UserService;
import com.example.ginatuango.views.UserLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Route(value = "/user", layout = UserLayout.class)
@PageTitle("Account Details")
@RolesAllowed("USER")
public class UserAccountView extends VerticalLayout implements HasUrlParameter<Integer>, BeforeEnterObserver {

    private int userId;
    private final UserService userService;
    private Optional<User> user;
    private String routeParameters;
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
        Text t = new Text(routeParameters);

        VerticalLayout accountDetailsLayout = new VerticalLayout();
        accountDetailsLayout.setWidthFull();
        accountDetailsLayout.setAlignItems(Alignment.CENTER);

        H3 accountDetailsTitle = new H3("Account Details");

        H6 accountNameTitle = new H6("Name");
        Text accountName = new Text(user.get().getName());

        H6 accountAddressTitle = new H6("Address");
        Text accountAddress = new Text(user.get().getAddress().toString());

        H6 accountNumberTitle = new H6("Number");
        Text accountNumber = new Text(user.get().getPhone());

        H6 accountEmailTitle = new H6("Email");
        Text accountEmail = new Text(user.get().getEmail());

        accountDetailsLayout.add(accountDetailsTitle,accountNameTitle
        , accountName, accountAddressTitle, accountAddress,
                accountNumberTitle, accountNumber,
                accountEmailTitle, accountEmail);



        this.setWidthFull();
        this.setAlignItems(Alignment.CENTER);
        add(title , t, accountDetailsLayout);
    }


    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {
        this.userId = integer;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(user.get().getId() != userId){
            userId = user.get().getId();
        }
        //TO DO
        //update to domain name
        UI.getCurrent().getPage().getHistory().pushState(null, "http://localhost:8080/user/" + userId);
    }
}
