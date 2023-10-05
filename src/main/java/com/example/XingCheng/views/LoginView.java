package com.example.XingCheng.views;

import com.example.XingCheng.data.models.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value = "",layout = AdminLayout.class)
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    public LoginView(){
        this.addClassName("login-form");

        H1 title = new H1();
        title.setText("Please Login");

        EmailField email = new EmailField();
        email.setLabel("Email:");
        

        PasswordField password = new PasswordField();
        password.setLabel("Password:");

        Button login = new Button();
        login.setText("LOGIN");

        add(title,email,password,login);


    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        Object userObject = VaadinSession.getCurrent().getAttribute("user");
        if(userObject instanceof User){
            User user = (User) userObject;
            if(user.isAdmin()){
                beforeEnterEvent.rerouteTo("/admin");
            }
            else{
                //add later for non admin users
            }
        }


    }
}
