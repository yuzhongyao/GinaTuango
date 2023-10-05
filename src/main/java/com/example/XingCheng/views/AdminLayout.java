package com.example.XingCheng.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;

public class AdminLayout extends AppLayout {

    //desktop
    public AdminLayout(){
       createHeader();
    }

    public void createHeader(){
        //logo div
        Div logoDiv = new Div();
        logoDiv.setText("Xing Cheng");
        logoDiv.addClassName("logo-container");

        //add components
        addToNavbar(logoDiv);
    }

    public void createDrawer(){

    }



}
