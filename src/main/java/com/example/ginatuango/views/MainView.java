package com.example.ginatuango.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "", layout = AdminLayout.class)
@RolesAllowed("ADMIN")
public class MainView extends VerticalLayout {
    public MainView(){
        H1 title = new H1("Hello World");
    }
}
