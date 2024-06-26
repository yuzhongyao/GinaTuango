package com.example.ginatuango.views.admin;


import com.example.ginatuango.data.entities.User;
import com.example.ginatuango.services.CustomService;
import com.example.ginatuango.views.AdminLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

@Route(value = "/admin",layout = AdminLayout.class)
@PageTitle("Home")
@RolesAllowed("ADMIN")
public class AdminDashboardView extends VerticalLayout implements BeforeEnterObserver {


    private final CustomService customService;
    Grid<Object[]> orderSum = new Grid<>();

    @Autowired
    public AdminDashboardView(CustomService customService,AuthenticationContext authenticationContext) {

        this.customService = customService;
        configureGrid();

        //H1 Title
        H1 title = new H1();
        title.setText("Todays Orders");
        title.addClassName("center");
        title.setWidthFull();

        //date picker
        DatePicker datePicker = new DatePicker("Order Date\nYYYY-MM-DD");
        DatePicker.DatePickerI18n singleFormatI18n = new DatePicker.DatePickerI18n();
        singleFormatI18n.setDateFormat("yyyy-MM-dd");
        datePicker.setI18n(singleFormatI18n);

        //To Implement
        //call orderservice to get that dates order
        datePicker.addValueChangeListener(event ->{
            updateGrid(event.getValue());
        });

        add(title);
        add(datePicker);
        add(orderSum);
    }




    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
    }


    private void configureGrid(){
        List<Object[]> rows = customService.getTodaysOrders();
        ListDataProvider<Object[]> dataProvider = DataProvider.ofCollection(rows);
        orderSum.setDataProvider(dataProvider);

        orderSum.addColumn(arr -> arr[0]).setHeader("ID");
        orderSum.addColumn(arr -> arr[1]).setHeader("Item");
        orderSum.addColumn(arr -> arr[2]).setHeader("Total");
        orderSum.addColumn(arr -> arr[3]).setHeader("Sale Type");
        orderSum.addColumn(arr -> arr[4]).setHeader("Sale");


    }

    private void updateGrid(LocalDate date){
        List<Object[]> rows = customService.getOrders(date);
        ListDataProvider<Object[]> dataProvider = DataProvider.ofCollection(rows);
        orderSum.setDataProvider(dataProvider);

//        orderSum.addColumn(arr -> arr[0]).setHeader("ID");
//        orderSum.addColumn(arr -> arr[1]).setHeader("Item");
//        orderSum.addColumn(arr -> arr[2]).setHeader("Total");
//        orderSum.addColumn(arr -> arr[3]).setHeader("Sale Type");
//        orderSum.addColumn(arr -> arr[4]).setHeader("Sale");
    }



}

