package com.example.ginatuango.utils;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class UTILS {

    private UTILS(){
    }

    public static void showNotification(Notification notification,String error, boolean type){
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
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        notification.add(layout);
        notification.setDuration(5000);
        notification.setPosition(Notification.Position.MIDDLE);
        notification.open();
    }
}
