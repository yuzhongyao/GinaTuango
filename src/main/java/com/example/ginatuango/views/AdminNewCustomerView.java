package com.example.ginatuango.views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "/newcustomer", layout = AdminLayout.class)
public class AdminNewCustomerView extends VerticalLayout {


    public AdminNewCustomerView(){
//        this.setWidthFull();
        H1 title = new H1("NEW CUSTOMER");
        title.addClassName("center");
        title.setWidthFull();

        VerticalLayout formContainer = new VerticalLayout();
        formContainer.setAlignItems(Alignment.CENTER);

        FormLayout form = new FormLayout();
        form.setMaxWidth("500px");
//        form.setWidthFull();

        TextField name = new TextField("Name:");
        name.setRequired(true);

        PasswordField passwordField = new PasswordField("Password:");

        EmailField email = new EmailField("Email:");

        TextField number = new TextField("Phone Number:");

        TextField street = new TextField("Street # and Name");

        TextField postal = new TextField("Postal Code:");

        TextField city = new TextField("City");

        Button submit = new Button("SUBMIT");


        form.add(name, passwordField, email, number, street, postal, city, submit);
        formContainer.add(form);
        add(title,formContainer);

    }



}
