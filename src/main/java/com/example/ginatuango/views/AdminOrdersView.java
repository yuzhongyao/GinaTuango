package com.example.ginatuango.views;

import com.example.ginatuango.data.entities.Order;
import com.example.ginatuango.services.OrderService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/orders",layout = AdminLayout.class)
public class AdminOrdersView extends VerticalLayout implements HasUrlParameter<Integer> {

    private final OrderService orderService;
    Grid<Order> orderGrid = new Grid<>(Order.class, false);
    private boolean hasParameter = false;

    @Autowired
    public AdminOrdersView(OrderService orderService){
        this.orderService = orderService;
        H1 orderTitle = new H1();
        orderTitle.setText("Orders");
        orderTitle.addClassName("center");
        orderTitle.setWidthFull();
        configureUserGrid();

        add(orderTitle, orderGrid);
    }

    private void configureUserGrid() {
        orderGrid.addColumn(order -> order.getDate()).setHeader("Date");
        orderGrid.addColumn(order -> order.getId()).setHeader("ID");
        orderGrid.addColumn(order -> order.getUser().getId()).setHeader("User ID");
        orderGrid.addColumn(order -> order.getUser().getName()).setHeader("User Name");


        orderGrid.setItems(orderService.getOrders());
        orderGrid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent,@OptionalParameter Integer integer) {
        hasParameter = true;

    }
}
