package com.example.ginatuango.views.user;

import com.example.ginatuango.data.entities.User;
import com.example.ginatuango.services.ItemSaleService;
import com.example.ginatuango.services.OrderService;
import com.example.ginatuango.services.UserService;
import com.example.ginatuango.utils.UTILS;
import com.example.ginatuango.views.AdminLayout;
import com.example.ginatuango.views.MainView;
import com.example.ginatuango.views.UserLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Route(value = "user/order",layout = UserLayout.class)
@PageTitle("Order")
@RolesAllowed("USER")
public class UserSpecificOrderView extends Div implements HasUrlParameter<Integer>, BeforeEnterObserver {

    private int integer;
    private final transient AuthenticationContext authenticationContext;
    private final ItemSaleService itemSaleService;
    private final UserService userService;
    private final OrderService orderService;

    private Optional<User> user;



    public UserSpecificOrderView(UserService userService, OrderService orderService, ItemSaleService itemSaleService, AuthenticationContext authenticationContext){
        this.userService = userService;
        this.orderService = orderService;
        this.itemSaleService = itemSaleService;
        this.authenticationContext = authenticationContext;

        user = userService.getUserByUsername(authenticationContext.getAuthenticatedUser(UserDetails.class).get().getUsername());

    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {
        this.integer = integer;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
//        UTILS.showNotification(new Notification(), Boolean.toString(orderService.isUserOrder(user.get().getId(), integer)), true);
        if(!orderService.isUserOrder(user.get().getId(), integer)){
            beforeEnterEvent.rerouteTo(UserOrdersView.class);
        }
    }

}
