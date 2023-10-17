package com.example.XingCheng.views;

import com.example.XingCheng.data.entities.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value = "")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    public LoginView(){
        this.addClassName("login-form");

        H1 title = new H1();
        title.setText("GINA TUANGO");

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

        buttonContainer.add(apply,login);



        //background image div
        Div imageContainer = new Div();
        imageContainer.setWidthFull();
        imageContainer.addClassName("image-container");

        add(title,greeting,email,password,buttonContainer);


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
