package com.example.ginatuango.views;

import com.example.ginatuango.data.entities.User;
import com.example.ginatuango.views.components.LoginForm;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value = "/login")
public class LoginView extends VerticalLayout {

    public LoginView(){
        this.addClassName("login-form");

        H1 title = new H1();
        title.setText("GINA TUANGO");




        //background image div
        Div imageContainer = new Div();
        imageContainer.setWidthFull();
        imageContainer.addClassName("image-container");

        add(title, new LoginForm());



    }

  
}
