package com.example.ginatuango.views;

import com.example.ginatuango.data.entities.User;
import com.example.ginatuango.views.components.LoginForm;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value = "/login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver{

    LoginI18n i18n = LoginI18n.createDefault();
    private final com.vaadin.flow.component.login.LoginForm login = new com.vaadin.flow.component.login.LoginForm();

    public LoginView(){
        this.addClassName("login-form");

        H1 title = new H1();
        title.setText("GINA TUANGO");



        //background image div
        Div imageContainer = new Div();
        imageContainer.setWidthFull();
        imageContainer.addClassName("image-container");

        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setForgotPassword("Apply");


        login.setI18n(i18n);
        login.setAction("login");
        login.addForgotPasswordListener(e ->{
            UI ui = UI.getCurrent();
            ui.navigate("/apply");
        });



        add(title, login);



    }


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation().getQueryParameters()
                .getParameters().containsKey("error")){
            login.setError(true);
        }
    }
}
