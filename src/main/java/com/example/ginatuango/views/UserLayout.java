package com.example.ginatuango.views;

import com.example.ginatuango.data.entities.User;
import com.example.ginatuango.services.UserService;
import com.example.ginatuango.utils.UTILS;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class UserLayout extends AppLayout {


    private final transient AuthenticationContext authContext;
    private final UserService userService;
    private final VerticalLayout drawerList = new VerticalLayout();

    public UserLayout(AuthenticationContext authContext, UserService userService){
        this.authContext = authContext;
        this.userService = userService;
        createHeader();
        createDrawer();
        this.setDrawerOpened(false);
    }

    private void createHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");

        Div div = new Div();
        div.getStyle().set("margin-left", "auto");

        DrawerToggle toggle = new DrawerToggle();

        Button cartButton = new Button();
        cartButton.setIcon(VaadinIcon.CART.create());

        div.add(cartButton, toggle);

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
                drawerList.add(account);
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
