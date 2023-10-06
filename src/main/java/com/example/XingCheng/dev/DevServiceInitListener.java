package com.example.XingCheng.dev;

import com.example.XingCheng.data.models.Address;
import com.example.XingCheng.data.models.User;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Component;

@Component
public class DevServiceInitListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent serviceInitEvent) {
        //why not working??????????
        //while in dev mode
        if (!serviceInitEvent.getSource()
                .getDeploymentConfiguration()
                .isProductionMode()){
            serviceInitEvent.getSource().addSessionInitListener(
                    sessionInitEvent -> {
                        Address address = new Address(1,"1 Street","ABCDEF","Toronto","ONTARIO","Canada");
                        User admin = new User(1,"admin","admin@gmail.com","1234567890",address,true);
                        sessionInitEvent.getSession().setAttribute("user",admin);
                    }
            );


        }
    }
}