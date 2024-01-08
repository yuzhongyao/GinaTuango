package com.example.ginatuango.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "", layout = UserLayout.class)
@PermitAll
public class MainView extends VerticalLayout {

    public MainView(){
//        H1 title = new H1("Hello World");
//        add(title);

        Tabs tabs = new Tabs();

        Tab fruitsTab = new Tab("Fruits");
        Tab vegetablesTab = new Tab("Vegetables");
        Tab groceryTab = new Tab("Grocery");
        Tab othersTab = new Tab("Other");

        tabs.add(fruitsTab,vegetablesTab,groceryTab,othersTab);
        tabs.setSelectedTab(fruitsTab);
        tabs.setWidthFull();
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);

        add(tabs);
    }

    





}
