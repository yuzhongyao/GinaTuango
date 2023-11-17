package com.example.ginatuango.views;


import com.example.ginatuango.data.entities.Address;
import com.example.ginatuango.data.entities.User;
import com.example.ginatuango.services.AddressService;
import com.example.ginatuango.services.UserService;
import com.example.ginatuango.utils.UTILS;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/newcustomer", layout = AdminLayout.class)
public class AdminNewCustomerView extends VerticalLayout {

    private final UserService userService;
    private final AddressService addressService;

    @Autowired
    public AdminNewCustomerView(UserService userService,AddressService addressService){
        this.userService = userService;
        this.addressService = addressService;
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
        submit.addClickListener(buttonClickEvent -> {
            createNewCustomer(
                    name,passwordField,email,number,street,postal,city
            );
        });

        form.add(name, passwordField, email, number, street, postal, city, submit);
        formContainer.add(form);
        add(title,formContainer);

    }

    private void createNewCustomer(TextField name, PasswordField passwordField, EmailField email, TextField number, TextField street, TextField postal, TextField city) {
        try {

            //create entities
            Address address = new Address();
            User user = new User();

            //set address attributes
            address.setCity(city.getValue());
            address.setCountry("Canada");
            address.setPostal(postal.getValue());
            address.setStreet(street.getValue());
            address.setProvince("Ontario");

            //add to db
            addressService.insertNewAddress(address);

            //set user attributes
            user.setAdmin(false);
            user.setEmail(email.getValue());
            user.setAddress(address);
            user.setName(name.getValue());
            user.setPassword(passwordField.getValue());
            user.setPhone(number.getValue());

            //add to db
            userService.insertNewUser(user);

            UTILS.showNotification(new Notification(),"SUCCESSFULLY CREATED NEW USER",true);

        }catch (Exception e){
            e.printStackTrace();
            UTILS.showNotification(new Notification(),"ERROR Please try again later",false);

        }

    }


}
