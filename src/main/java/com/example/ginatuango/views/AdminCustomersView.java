package com.example.ginatuango.views;

import com.example.ginatuango.data.entities.User;
import com.example.ginatuango.services.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/admin/customers",layout = AdminLayout.class)
public class AdminCustomersView extends VerticalLayout {

    private final UserService userService;
    Grid<User> usersGrid = new Grid<>(User.class, false);


    @Autowired
    public AdminCustomersView(UserService service){
        userService = service;

        H1 userTitle = new H1();
        userTitle.setText("Users");
        userTitle.addClassName("center");
        userTitle.setWidthFull();
        configureUserGrid();

        Button addUser = new Button("ADD CUSTOMER");
        addUser.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate("/newcustomer");
        });

        add(userTitle, addUser, usersGrid);
    }

    //configures user columns accordingly
    private void configureUserGrid() {
//        usersGrid.setSizeFull();
        usersGrid.addColumn(user -> user.getName()).setHeader("Name");
        usersGrid.addColumn(user -> user.getPhone()).setHeader("Phone");

        usersGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        usersGrid.setItems(userService.getUsers());
    }



}
