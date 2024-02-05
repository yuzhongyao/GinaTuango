package com.example.ginatuango.views.user;


import com.example.ginatuango.data.entities.Order;
import com.example.ginatuango.data.entities.User;
import com.example.ginatuango.services.ItemSaleService;
import com.example.ginatuango.services.OrderService;
import com.example.ginatuango.services.UserService;
import com.example.ginatuango.views.UserLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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
    private int orderId;

    public UserOrdersView(UserService userService, OrderService orderService, ItemSaleService itemSaleService, AuthenticationContext authenticationContext) {
        this.userService = userService;
        this.orderService = orderService;
        this.itemSaleService = itemSaleService;
        this.authenticationContext = authenticationContext;

        user = userService.getUserByUsername(authenticationContext.getAuthenticatedUser(UserDetails.class).get().getUsername());
        orders = orderService.getOrdersByUser(user.get());


    }


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

    }


}