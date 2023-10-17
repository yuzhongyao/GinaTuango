package com.example.XingCheng.views;

import com.example.XingCheng.data.entities.User;
import com.example.XingCheng.services.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/customers",layout = AdminLayout.class)
public class AdminCustomersView extends VerticalLayout {

    private final UserService userService;
    Grid<User> usersGrid = new Grid<>(User.class, false);


    @Autowired
    public AdminCustomersView(UserService service){
        userService = service;

        H1 userTitle = new H1();
        userTitle.setText("Users");
        configureUserGrid();

        add(userTitle, usersGrid);
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
