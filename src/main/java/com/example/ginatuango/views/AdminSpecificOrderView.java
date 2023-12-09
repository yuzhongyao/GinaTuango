package com.example.ginatuango.views;

import com.example.ginatuango.services.OrderService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Route(value = "admin/order",layout = AdminLayout.class)
public class AdminSpecificOrderView extends VerticalLayout implements HasUrlParameter<Integer> {
    private final OrderService orderService;
    Grid<Object[]> orderGrid = new Grid<>();
    H1 title = new H1();

    private int orderId = -1;

    @Autowired
    public AdminSpecificOrderView(OrderService orderService){
        this.orderService = orderService;


        title.setText("Order " +orderId);
        title.addClassName("center");
        title.setWidthFull();

        add(title, orderGrid);
    }

    private void configureGrid() {
        List<Object[]> rows = orderService.getItemSalesByOrder(orderId);
        ListDataProvider<Object[]> dataProvider = DataProvider.ofCollection(rows);
        orderGrid.setDataProvider(dataProvider);

        orderGrid.addColumn(arr -> arr[0]).setHeader("Customer");
        orderGrid.addColumn(arr -> arr[1]).setHeader("Item");
        orderGrid.addColumn(arr -> arr[2]).setHeader("Total");
        orderGrid.addColumn(arr -> arr[3]).setHeader("Sale Type");
        orderGrid.addColumn(arr -> arr[4]).setHeader("Date");
        orderGrid.addColumn(arr -> arr[5]).setHeader("Cost");
        orderGrid.addColumn(arr -> arr[6]).setHeader("On Sale");

    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {
        this.orderId = integer;
        configureGrid();
        title.setText("Order "+ orderId);
    }
}
