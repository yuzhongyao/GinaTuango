package com.example.XingCheng.views.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;

public class LoginForm extends VerticalLayout {

    public LoginForm(){
        H4 greeting = new H4();
        greeting.setText("Please Login");

        EmailField email = new EmailField();
        email.setLabel("Email:");

        PasswordField password = new PasswordField();
        password.setLabel("Password:");

        //button div
        HorizontalLayout buttonContainer = new HorizontalLayout();
        buttonContainer.setAlignItems(Alignment.CENTER);

        Button login = new Button();
        login.setText("LOGIN");

        Button apply = new Button();
        apply.setText("APPLY");
        apply.addClickListener(buttonClickEvent -> {
           UI ui = UI.getCurrent();
           ui.navigate("/apply");
        });

        buttonContainer.add(apply,login);

        this.addClassName("login-form");
        add(greeting,email,password,buttonContainer);
    }

}
