package com.example.ginatuango.views.user;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "/user")
@PageTitle("Account Details")
@RolesAllowed("USER")
public class UserAccountView extends VerticalLayout implements HasUrlParameter<Integer> {

    public UserAccountView(){
        H1 title = new H1("Welcome");
        add(title);
    }


    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {

    }
}
