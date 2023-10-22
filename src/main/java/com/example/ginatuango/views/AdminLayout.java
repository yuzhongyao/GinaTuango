package com.example.ginatuango.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class AdminLayout extends AppLayout {

    //desktop
    public AdminLayout(){
       createHeader();
       createDrawer();
       this.setDrawerOpened(false);
    }

    public void createHeader(){

        HorizontalLayout header = new HorizontalLayout();
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");

        DrawerToggle toggle = new DrawerToggle();
        toggle.getStyle().set("margin-left", "auto");

        H1 title = new H1();
        title.setText("GINA TUANGO");
        title.addClassName("center");


        header.add(title,toggle);

        //add components
        addToNavbar(header);
    }

    public void createDrawer(){
        VerticalLayout list = new VerticalLayout();

        RouterLink home = new RouterLink("Home", AdminDashboardView.class);
        home.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink orders = new RouterLink("Orders", AdminOrdersView.class);
        RouterLink customers = new RouterLink("Customers", AdminCustomersView.class);
        RouterLink items = new RouterLink("Items", AdminItemsView.class);


        Button logout = new Button();
        logout.setText("Logout");

        list.add(home,orders,customers,items,logout);
        addToDrawer(list);


    }



}
