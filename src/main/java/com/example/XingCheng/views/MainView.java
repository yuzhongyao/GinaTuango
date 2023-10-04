package com.example.XingCheng.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Xing Cheng")
@Route(value = "", layout = MainLayout.class)
public class MainView extends VerticalLayout {


    public MainView (){
        add(new MainLayout());
    }

}
