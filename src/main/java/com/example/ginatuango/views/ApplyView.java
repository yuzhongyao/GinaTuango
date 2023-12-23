package com.example.ginatuango.views;

import com.example.ginatuango.views.components.ApplyForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Application")
@Route(value = "/apply")
@AnonymousAllowed
public class ApplyView extends VerticalLayout {

    public ApplyView(){
        add(new ApplyForm());
    }


}
