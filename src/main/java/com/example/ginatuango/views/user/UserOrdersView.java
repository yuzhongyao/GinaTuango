package com.example.ginatuango.views.user;


import com.example.ginatuango.services.OrderService;
import com.example.ginatuango.services.UserService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.security.AuthenticationContext;

@Route(value = "/user/orders")
public class UserOrdersView extends VerticalLayout implements BeforeEnterObserver {

   private final UserService userService;
   private final OrderService orderService;
   private final transient AuthenticationContext authenticationContext;

   public UserOrdersView(UserService userService, OrderService orderService, AuthenticationContext authenticationContext){
       this.userService = userService;
       this.orderService = orderService;
       this.authenticationContext = authenticationContext;



   }


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

    }
}
