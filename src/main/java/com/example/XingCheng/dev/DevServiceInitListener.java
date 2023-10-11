package com.example.XingCheng.dev;

import com.example.XingCheng.data.entities.User;
import com.example.XingCheng.data.entities.Address;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
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
