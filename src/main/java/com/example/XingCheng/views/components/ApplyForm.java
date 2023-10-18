package com.example.XingCheng.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class ApplyForm extends VerticalLayout {


    public ApplyForm(){
        H4 greeting = new H4();
        greeting.setText("Please fill in the form");

        TextField name = new TextField();
        name.setLabel("Name:");
        name.setRequiredIndicatorVisible(true);

        TextField number = new TextField();
        number.setLabel("Phone Number:");
        number.setRequiredIndicatorVisible(true);

        TextArea message = new TextArea();
        message.setLabel("Reasons for applying");
        message.setRequiredIndicatorVisible(true);


        Button submit = new Button();
        submit.setText("SUBMIT");

        this.addClassName("login-form");
        add(greeting,name,number,message,submit);



    }
}
