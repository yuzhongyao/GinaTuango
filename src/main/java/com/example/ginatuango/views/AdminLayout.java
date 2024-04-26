package com.example.ginatuango.views;

import com.example.ginatuango.views.admin.AdminCustomersView;
import com.example.ginatuango.views.admin.AdminDashboardView;
import com.example.ginatuango.views.admin.AdminItemsView;
import com.example.ginatuango.views.admin.AdminOrdersView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.security.AuthenticationContext;

public class AdminLayout extends AppLayout {

    private final transient AuthenticationContext authContext;
    //desktop
    public AdminLayout(AuthenticationContext authenticationContext){
        this.authContext = authenticationContext;
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

        H1 title = new H1("");
        title.setText("GINA TUANGO");
        title.addClassName("center");
        RouterLink home = new RouterLink("", MainView.class);
        home.add(title);

        header.add(home,toggle);

        //add components
        addToNavbar(header);
    }

    public void createDrawer(){

        VerticalLayout list = new VerticalLayout();

        RouterLink home = new RouterLink("Home", MainView.class);
        home.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink dashboard = new RouterLink("Dashboard", AdminDashboardView.class);

        RouterLink orders = new RouterLink("Orders", AdminOrdersView.class);
        RouterLink customers = new RouterLink("Customers", AdminCustomersView.class);
        RouterLink items = new RouterLink("Items", AdminItemsView.class);


        Button logout = new Button();
        logout.setText("Logout");
        logout.addClickListener(buttonClickEvent -> {
            authContext.logout();;
        });

        list.add(home,dashboard,orders,customers,items,logout);
        addToDrawer(list);


    }



}
