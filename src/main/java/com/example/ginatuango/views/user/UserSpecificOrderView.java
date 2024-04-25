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
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
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
    Grid<Object[]> orderGrid = new Grid<>();
    H1 title = new H1();




    public UserSpecificOrderView(UserService userService, OrderService orderService, ItemSaleService itemSaleService, AuthenticationContext authenticationContext){
        this.userService = userService;
        this.orderService = orderService;
        this.itemSaleService = itemSaleService;
        this.authenticationContext = authenticationContext;

        user = userService.getUserByUsername(authenticationContext.getAuthenticatedUser(UserDetails.class).get().getUsername());

        title.setText("Order ");
        title.addClassName("center");
        title.setWidthFull();


        add(title, orderGrid);

    }

    private void configureGrid() {
        List<Object[]> rows = orderService.getItemSalesByOrder(integer);
        ListDataProvider<Object[]> dataProvider = DataProvider.ofCollection(rows);
        orderGrid.setDataProvider(dataProvider);

//        orderGrid.addColumn(arr -> arr[0]).setHeader("Customer");
        orderGrid.addColumn(arr -> arr[1]).setHeader("Item");
        orderGrid.addColumn(arr -> arr[2]).setHeader("Total");
        orderGrid.addColumn(arr -> arr[3]).setHeader("Sale Type");
        orderGrid.addColumn(arr -> arr[4]).setHeader("Date");
        orderGrid.addColumn(arr -> arr[5]).setHeader("Cost");
        orderGrid.addColumn(arr -> arr[6]).setHeader("On Sale");

        orderGrid.

    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {

        this.integer = integer;
        configureGrid();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
//        UTILS.showNotification(new Notification(), Boolean.toString(orderService.isUserOrder(user.get().getId(), integer)), true);
        if(!orderService.isUserOrder(user.get().getId(), integer)){
            beforeEnterEvent.rerouteTo(UserOrdersView.class);
        }
    }

}
