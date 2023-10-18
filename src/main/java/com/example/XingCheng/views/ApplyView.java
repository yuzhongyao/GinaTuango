package com.example.XingCheng.views;

import com.example.XingCheng.views.components.ApplyForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Application")
@Route(value = "/apply")
public class ApplyView extends VerticalLayout {

    public ApplyView(){
        add(new ApplyForm());
    }


}
