package com.example.ginatuango.views;

import com.example.ginatuango.data.entities.Order;
import com.example.ginatuango.services.OrderService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "/admin/orders",layout = AdminLayout.class)
public class AdminOrdersView extends VerticalLayout {

    private final OrderService orderService;
    Grid<Order> orderGrid = new Grid<>(Order.class, false);


    @Autowired
    public AdminOrdersView(OrderService orderService){
        this.orderService = orderService;
        H1 orderTitle = new H1();
        orderTitle.addClassName("center");
        orderTitle.setWidthFull();
        orderTitle.setText("Orders");
        configureOrdersGrid();

        add(orderTitle,orderGrid);

    }



    private void configureOrdersGrid() {
        orderGrid.addColumn(order -> order.getDate()).setHeader("Date");
        orderGrid.addColumn(order -> order.getId()).setHeader("ID");
        orderGrid.addColumn(order -> order.getUser().getId()).setHeader("User ID");
        orderGrid.addColumn(order -> order.getUser().getName()).setHeader("User Name");

        orderGrid.addItemClickListener(itemClickEvent -> {
            orderGrid.getUI().ifPresent(ui -> ui.navigate(
                    AdminSpecificOrderView.class, (Integer) itemClickEvent.getItem().getId()));

        });

        orderGrid.setItems(orderService.getOrders());
        orderGrid.getColumns().forEach(col -> col.setAutoWidth(true));
    }


}
