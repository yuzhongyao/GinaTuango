package com.example.XingCheng.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    //desktop
    public MainLayout(){
       createHeader();
    }

    public void createHeader(){
        //logo div
        Div logoDiv = new Div();
        logoDiv.setText("Xing Cheng");
        logoDiv.addClassName("logo-container");

        //link div
        HorizontalLayout menu = new HorizontalLayout();

        //links
        RouterLink login = new RouterLink("login", LoginView.class);




        //add components
        addToNavbar(logoDiv);
        addToNavbar(menu);
    }

    public void createDrawer(){

    }



}
