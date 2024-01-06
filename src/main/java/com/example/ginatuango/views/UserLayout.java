package com.example.ginatuango.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.security.core.userdetails.UserDetails;

public class UserLayout extends AppLayout {


    private final transient AuthenticationContext authContext;

    public UserLayout(AuthenticationContext authContext){
        this.authContext = authContext;
        createHeader();
        createDrawer();
        this.setDrawerOpened(false);
    }

    private void createHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");

        DrawerToggle toggle = new DrawerToggle();
        toggle.getStyle().set("margin-left", "auto");

        H1 title = new H1("");
        title.setText("GINA TUANGO");
        title.addClassName("center");
        RouterLink home = new RouterLink("",MainView.class);
        home.add(title);

        header.add(home,toggle);

        //add components
        addToNavbar(header);
    }

    private void createDrawer() {
        VerticalLayout list = new VerticalLayout();

        RouterLink home = new RouterLink("Home", MainView.class);
        home.setHighlightCondition(HighlightConditions.sameLocation());

        boolean isAdmin = authContext.getAuthenticatedUser(UserDetails.class).get().getAuthorities().stream().anyMatch(grantedAuthority -> "ROLE_ADMIN".equals(grantedAuthority.getAuthority()));
        if(isAdmin){
            RouterLink admin = new RouterLink("ADMIN", AdminDashboardView.class);
            list.add(admin);
        }

        Button logout = new Button();
        logout.setText("Logout");
        logout.addClickListener(buttonClickEvent -> {
            authContext.logout();;
        });

        list.add(home, logout);
        addToDrawer(list);

    }




}
