package com.example.ginatuango.views;

import com.example.ginatuango.data.entities.ItemSale;
import com.example.ginatuango.data.entities.Order;
import com.example.ginatuango.data.entities.User;
import com.example.ginatuango.services.ItemSaleService;
import com.example.ginatuango.services.OrderService;
import com.example.ginatuango.services.UserService;
import com.example.ginatuango.utils.UTILS;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "/admin/customers",layout = AdminLayout.class)
public class AdminCustomersView extends VerticalLayout {

    private final UserService userService;
    private final OrderService orderService;
    private final ItemSaleService itemSaleService;
    Grid<User> usersGrid = new Grid<>(User.class, false);


    @Autowired
    public AdminCustomersView(UserService service,  OrderService orderService1, ItemSaleService itemSaleService){
        userService = service;
        this.orderService = orderService1;
        this.itemSaleService = itemSaleService;


        H1 userTitle = new H1();
        userTitle.setText("Users");
        userTitle.addClassName("center");
        userTitle.setWidthFull();
        configureUserGrid();

        Button addUser = new Button("ADD CUSTOMER");
        addUser.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate("/newcustomer");
        });

        Paragraph edit = new Paragraph("Click a row to delete");

        add(userTitle, edit, addUser, usersGrid);
    }

    //configures user columns accordingly
    private void configureUserGrid() {
//        usersGrid.setSizeFull();
        usersGrid.addColumn(user -> user.getName()).setHeader("Name");
        usersGrid.addColumn(user -> user.getPhone()).setHeader("Phone");

        usersGrid.addItemClickListener(userItemClickEvent -> {
            User user = userItemClickEvent.getItem();

            Dialog editor = new Dialog();
            editor.setHeaderTitle("DELETE CUSTOMER");

            VerticalLayout editorLayout = new VerticalLayout();
            Paragraph warning = new Paragraph("ARE YOU SURE YOU WANT TO DELETE?");
            Button delete = new Button("DELETE");
            delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            Button cancel = new Button("CANCEL");
            cancel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            delete.addClickListener(buttonClickEvent -> {
                List<Order> userOrders = orderService.getOrdersByUser(user);
                for(Order o: userOrders){
                    List<ItemSale> itemSales = itemSaleService.getItemSalesByOrder(o);
                    itemSaleService.deleteItemSales(itemSales);
                }

                orderService.deleteOrders(userOrders);
                userService.deleteUser(user);
                UTILS.showNotification(new Notification(),"DELETED USER", false);

                //WTF refreshing is not removing the deleted row?????????
                usersGrid.getDataProvider().refreshItem(user);
                usersGrid.getDataProvider().refreshAll();
               
                editor.close();

            });

            cancel.addClickListener(buttonClickEvent -> {
                editor.close();
            });
            editorLayout.add(warning);
            editor.getFooter().add(delete,cancel);

            editor.add(editorLayout);
            editor.open();

        });

        usersGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        usersGrid.setItems(userService.getUsers());
    }



}
