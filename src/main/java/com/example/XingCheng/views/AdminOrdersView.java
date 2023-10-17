package com.example.XingCheng.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/orders",layout = AdminLayout.class)
public class AdminOrdersView extends VerticalLayout {


    @Autowired
    public AdminOrdersView(){

    }
}
