package com.example.XingCheng.views.components;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;

import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class ApplyForm extends VerticalLayout {

    private static final Logger logger = LoggerFactory.getLogger(ApplyForm.class);
    private static final Notification notification = new Notification();

    public ApplyForm(){
        H4 greeting = new H4();
        greeting.setText("Please fill in the form");

        TextField nameField = new TextField();
        nameField.setLabel("Name:");
        nameField.setRequiredIndicatorVisible(true);

        TextField numberField = new TextField();
        numberField.setLabel("Phone Number:");
        numberField.setHelperText("XXXXXXXXXX Format. No dashes. No spaces.");
        numberField.setRequiredIndicatorVisible(true);

        TextArea messageArea = new TextArea();
        messageArea.setLabel("Reasons for applying");
        messageArea.setRequiredIndicatorVisible(true);


        Button submit = new Button();
        submit.setText("SUBMIT");
        submit.addClickListener(buttonClickEvent -> {
            String name = nameField.getValue();
            String number = numberField.getValue();
            String message = messageArea.getValue();

            // Validate input
            //Notification.show() does not apply theme variants??????
            if(name.isEmpty() || number.isEmpty() || message.isEmpty()){
                showNotification("Please fill in all fields",false);
                return;
            }

            if(!isValidNumber(number)){
                showNotification("Please enter a valid phone number",false);
                numberField.clear();
                return;
            }

            // Send email
            try{
                sendEmail(name, number, message);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                showNotification("Application sent successfully!",true);
                nameField.clear();
                numberField.clear();
                messageArea.clear();
//                UI.getCurrent().navigate("/");
            }catch (Exception e){
                logger.error("Error sending email: {}", e.getMessage(), e);
                showNotification("An error occurred while applying. Please try again later.",false);

            }


        });

        this.addClassName("login-form");
        add(greeting,nameField,numberField,messageArea,submit);

    }

    private Boolean isValidNumber(String number){
        Boolean result = false;

        // Define a regular expression pattern for phone numbers without parentheses, dashes, or spaces
        String regex = "^\\d{10}$"; // Matches a sequence of 10 digits

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(number);

        return matcher.matches();

    }

    private void sendEmail(String name, String number, String message) throws ConfigurationException{


        String to = "ginatuango@gmail.com"; // Your business email address
        String from = to; // Your application's email address
        String host = "smtp.gmail.com"; // Gmail SMTP server
        String ssl = "465";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", ssl); // Port for SMTP server (e.g., 587 for TLS)
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", ssl);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", ssl);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // Load the properties file
        Configurations configs = new Configurations();
        FileBasedConfigurationBuilder<PropertiesConfiguration> builder = configs.propertiesBuilder("application.properties");
        Configuration config = builder.getConfiguration();

        // Retrieve the credentials from the properties file
        String username = config.getString("gmail.username");
        String password = config.getString("gmail.password");

        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getInstance(properties, authenticator);
        session.setDebug(false); //turn true for debug

        try{
            // create a message with headers
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            InternetAddress address = new InternetAddress(to);
            msg.setRecipient(Message.RecipientType.TO, address);
            msg.setSubject("New Application");
            msg.setSentDate(new Date());
            // create message body
            Multipart mp = new MimeMultipart();
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setText("Sender Name: " + name+ " \n "
                    +"Sender Number: " +number+ " \n\n " + message, "us-ascii");
            mp.addBodyPart(mbp);

            msg.setContent(mp);

            // send the message
            Transport.send(msg);

        }catch (Exception e){
            e.printStackTrace();

        }


    }



    private void showNotification(String error, boolean type){
        notification.removeAll();
        notification.removeThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.removeThemeVariants(NotificationVariant.LUMO_SUCCESS);

        //errors for 0
        if(type == false){
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
        else{
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        }
        Div text = new Div(new Text(error));

        Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.setAriaLabel("Close");
        closeButton.addClickListener(event -> {
            notification.close();
        });

        HorizontalLayout layout = new HorizontalLayout(text, closeButton);
        layout.setAlignItems(Alignment.CENTER);

        notification.add(layout);
        notification.setDuration(5000);
        notification.setPosition(Notification.Position.MIDDLE);
        notification.open();
    }

}
