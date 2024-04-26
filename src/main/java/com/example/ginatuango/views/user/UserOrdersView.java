package com.example.ginatuango.views.user;


import com.example.ginatuango.data.entities.Order;
import com.example.ginatuango.data.entities.User;
import com.example.ginatuango.services.ItemSaleService;
import com.example.ginatuango.services.OrderService;
import com.example.ginatuango.services.UserService;
import com.example.ginatuango.views.UserLayout;
import com.example.ginatuango.views.admin.AdminSpecificOrderView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

@Route(value = "/user/orders", layout = UserLayout.class)
@PageTitle("Orders")
@RolesAllowed("USER")
public class UserOrdersView extends VerticalLayout implements  BeforeEnterObserver {

    private final UserService userService;
    private final OrderService orderService;
    private final transient AuthenticationContext authenticationContext;

    private final ItemSaleService itemSaleService;

    private Optional<User> user;
    private List<Order> orders;
    private Grid<Order> orderGrid = new Grid<>();
    private int orderId;

    public UserOrdersView(UserService userService, OrderService orderService, ItemSaleService itemSaleService, AuthenticationContext authenticationContext) {
        this.userService = userService;
        this.orderService = orderService;
        this.itemSaleService = itemSaleService;
        this.authenticationContext = authenticationContext;

        user = userService.getUserByUsername(authenticationContext.getAuthenticatedUser(UserDetails.class).get().getUsername());
        orders = orderService.getOrdersByUser(user.get());

        H1 title = new H1();
        title.setText("Your Orders");

        configureGrid();
        add(title, orderGrid);

    }

    private void configureGrid() {
        ListDataProvider<Order> dataProvider = new ListDataProvider<>(orders);
        orderGrid.setDataProvider(dataProvider);
        orderGrid.addColumn(order -> order.getDate()).setHeader("Date");
        orderGrid.addColumn(order -> order.getUser().getName()).setHeader("User");
        orderGrid.addItemClickListener(orderItemClickEvent -> {
            orderGrid.getUI().ifPresent(ui -> ui.navigate(
                    UserSpecificOrderView.class, (Integer) orderItemClickEvent.getItem().getId()));
        });
//        orderGrid.addColumn(order -> order.getUser().getId()).setHeader("User ID");
    }


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

    }


}