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
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

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

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        Object userObject = VaadinSession.getCurrent().getAttribute("user");
        if(userObject instanceof User){
            User user = (User) userObject;
            //admins redirected to admin dashboard
            if(user.isAdmin()){
                beforeEnterEvent.rerouteTo("/admin");
            }
            //users redirected to user dashboard
            else{
                //add later for non admin users
            }
        }


    }
}
