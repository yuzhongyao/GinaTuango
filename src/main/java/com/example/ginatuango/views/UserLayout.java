package com.example.ginatuango.views;

import com.example.ginatuango.data.entities.Cart;
import com.example.ginatuango.data.entities.CartItem;
import com.example.ginatuango.data.entities.User;
import com.example.ginatuango.services.CartItemService;
import com.example.ginatuango.services.CartService;
import com.example.ginatuango.services.UserService;
import com.example.ginatuango.views.admin.AdminDashboardView;
import com.example.ginatuango.views.components.Counter;
import com.example.ginatuango.views.user.UserAccountView;
import com.example.ginatuango.views.user.UserOrdersView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public class UserLayout extends AppLayout {


    private final transient AuthenticationContext authContext;
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final VerticalLayout drawerList = new VerticalLayout();
    public static Counter counter;
    protected List<CartItem> cartItems;

    public UserLayout(AuthenticationContext authContext, UserService userService, CartService cartService, CartItemService cartItemService){
        this.authContext = authContext;
        this.userService = userService;
        this.cartService = cartService;
        this.cartItemService = cartItemService;
        createHeader();
        createDrawer();
        this.setDrawerOpened(false);
    }

    private void createHeader() {
        UserDetails userDetails = authContext.getAuthenticatedUser(UserDetails.class).get();
        Optional<User> user = userService.getUserByUsername(userDetails.getUsername());
        Cart cart =cartService.getCartByUser(user.get().getId());
        cartItems = cartItemService.getCartItemsByCart(cart.getCart_id());

        HorizontalLayout header = new HorizontalLayout();
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");

        Div div = new Div();
        div.getStyle().set("margin-left", "auto");

        DrawerToggle toggle = new DrawerToggle();

        HorizontalLayout cartButtonDiv = new HorizontalLayout();
        cartButtonDiv.setSpacing(false);
        Button cartButton = new Button();
        cartButton.setIcon(VaadinIcon.CART.create());
        counter = new Counter(cartItems.size());

        cartButtonDiv.add(cartButton);
        if(cartItems.size() > 0){
            cartButtonDiv.add(counter);
        }


        div.add(cartButtonDiv, toggle);

        H1 title = new H1("");
        title.setText("GINA TUANGO");
        title.addClassName("center");
        RouterLink home = new RouterLink("",MainView.class);
        home.add(title);

        header.add(home,div);

        //add components
        addToNavbar(header);
    }

    private void createDrawer() {

        RouterLink home = new RouterLink("Home", MainView.class);
        home.setHighlightCondition(HighlightConditions.sameLocation());

        UserDetails userDetails = authContext.getAuthenticatedUser(UserDetails.class).get();
        boolean isAdmin = userDetails.getAuthorities().stream().anyMatch(grantedAuthority -> "ROLE_ADMIN".equals(grantedAuthority.getAuthority()));
        if(isAdmin){
            RouterLink admin = new RouterLink("ADMIN", AdminDashboardView.class);
            drawerList.add(admin);
        }
        else {
            Optional<User> user = userService.getUserByUsername(userDetails.getUsername());

            user.ifPresent(user1 -> {
                RouterLink account = new RouterLink("Account", UserAccountView.class, user.get().getId());
                RouterLink orders = new RouterLink("Orders", UserOrdersView.class);
                drawerList.add(account, orders);
            });

        }


        Button logout = new Button();
        logout.setText("Logout");
        logout.addClickListener(buttonClickEvent -> {
            authContext.logout();;
        });

        drawerList.add(home, logout);
        addToDrawer(drawerList);

    }




}
