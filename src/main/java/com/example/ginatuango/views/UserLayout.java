package com.example.ginatuango.views;

import com.example.ginatuango.data.entities.*;
import com.example.ginatuango.services.CartItemService;
import com.example.ginatuango.services.CartService;
import com.example.ginatuango.services.ImageService;
import com.example.ginatuango.services.UserService;
import com.example.ginatuango.utils.UTILS;
import com.example.ginatuango.views.admin.AdminDashboardView;
import com.example.ginatuango.views.components.Counter;
import com.example.ginatuango.views.user.UserAccountView;
import com.example.ginatuango.views.user.UserOrdersView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserLayout extends AppLayout {


    private final transient AuthenticationContext authContext;
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ImageService imageService;
    private final VerticalLayout drawerList = new VerticalLayout();
    public static Counter counter;
    protected List<CartItem> cartItems;
    protected Optional<User> user;

    public UserLayout(AuthenticationContext authContext, UserService userService,
                      CartService cartService, CartItemService cartItemService,
                      ImageService imageService){
        this.authContext = authContext;
        this.userService = userService;
        this.cartService = cartService;
        this.cartItemService = cartItemService;
        this.imageService = imageService;
        createHeader();
        createDrawer();
        this.setDrawerOpened(false);
    }

    private void createHeader() {
        UserDetails userDetails = authContext.getAuthenticatedUser(UserDetails.class).get();
        user = userService.getUserByUsername(userDetails.getUsername());
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

        cartButton.addClickListener(buttonClickEvent -> {
            cartItems = cartItemService.getCartItemsByCart(cart.getCart_id());
            Dialog cartPopup = new Dialog();
            cartPopup.setWidthFull();
            cartPopup.setHeaderTitle("Cart");

            //date picker
            DatePicker datePicker = new DatePicker("Order Date\nYYYY-MM-DD");
            DatePicker.DatePickerI18n singleFormatI18n = new DatePicker.DatePickerI18n();
            singleFormatI18n.setDateFormat("yyyy-MM-dd");
            datePicker.setI18n(singleFormatI18n);

            LocalDate today = LocalDate.now(ZoneId.systemDefault());
            datePicker.setMin(today);
            datePicker.setMax(today.plusDays(7));

            datePicker.addValueChangeListener(datePickerLocalDateComponentValueChangeEvent -> {
                LocalDate value = datePicker.getValue();
                String errorMessage = null;
                if (value != null) {
                    if (value.compareTo(datePicker.getMin()) < 0) {
                        errorMessage = "Too early, choose later date";
                    } else if (value.compareTo(datePicker.getMax()) > 0) {
                        errorMessage = "Too late, choose earlier date";
                    }
                }
                datePicker.setErrorMessage(errorMessage);

            });

            cartPopup.add(datePicker);

            if (cartItems.isEmpty()) {
                Text emptyCartMessage = new Text("Empty cart");
                cartPopup.add(emptyCartMessage);
            }

            VirtualList<CartItem> cartItemVirtualList = new VirtualList<>();
            ComponentRenderer<Component,CartItem> cartItemComponentRenderer = new ComponentRenderer<>(cartItem -> {

                //get images
                List<Image> imagesUrls = imageService.getImagesByItemId(cartItem.getItem().getId());

                VerticalLayout card = new VerticalLayout();
                card.addClassName("card");
                card.setSpacing(false);

                HorizontalLayout horizontalLayout = new HorizontalLayout();
                horizontalLayout.addClassName("cardHorizontal");
                horizontalLayout.setPadding(false);
                horizontalLayout.setMargin(false);



                com.vaadin.flow.component.html.Image image = new com.vaadin.flow.component.html.Image();
                image.setSrc(imagesUrls.get(0).getImageUrl());
                image.setMaxHeight("200px");
                image.setMaxWidth("200px");
                image.setMinHeight("150px");
                image.setMinWidth("150px");

                VerticalLayout info = new VerticalLayout();
                H6 cartItemTitle = new H6(cartItem.getItem().getName());
                Text type = new Text(cartItem.getType().getType());
                NumberField quantityField = new NumberField();
                quantityField.setMin(1);
                quantityField.setStepButtonsVisible(true);
                quantityField.setMaxWidth("100px");
                quantityField.setValue(cartItem.getQuantity());

                info.add(cartItemTitle,type,quantityField);
                info.setPadding(false);
                info.setMargin(false);

                VerticalLayout buttons = new VerticalLayout();

                Button delete = new Button();
                delete.setText("Delete");
                delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
                delete.addClickListener(buttonClickEvent1 -> {
                    cartItems.remove(cartItems.indexOf(cartItem));
                    cartItemService.deleteById(cartItem);
                    cartItems = cartItemService.getCartItemsByCart(cart.getCart_id());
                    cartItemVirtualList.setItems(cartItems);

//                    Span span = new Span(String.valueOf(cartItems.size()));
//                    span.getElement().getThemeList().add("badge pill small primary");
//                    span.getStyle().set("margin-inline-start", "var(--lumo-space-s)");
//
//                    UserLayout.counter.setSpanCount(span);
                    UserLayout.counter.decrement();

                    UTILS.showNotification(new Notification(),"Removed from cart",true);
                });

                Button save = new Button();
                save.setText("Save");
                save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
                save.addClickListener(buttonClickEvent1 -> {
                    if(quantityField.getValue() <= 0){
                        UTILS.showNotification(new Notification(),"Please delete from cart",false);
                        return;
                    }
                    cartItem.setQuantity(quantityField.getValue());
                    cartItemService.updateCartItem(cartItem);
                    UTILS.showNotification(new Notification(),"Updated cart",true);

                });

                buttons.add(delete,save);
                horizontalLayout.add(image,info,buttons);
                card.add(horizontalLayout);

                return card;
            });

            Button close = new Button("Close", (e) -> cartPopup.close());
            close.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            Button order = new Button("Order");
            order.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);
            order.addClickListener(buttonClickEvent1 -> {

            });

            cartItemVirtualList.setItems(cartItems);
            cartItemVirtualList.setRenderer(cartItemComponentRenderer);
            cartPopup.add(cartItemVirtualList);
            cartPopup.getFooter().add(order,close);
            cartPopup.open();
        });
        counter = new Counter(cartItems.size());

        cartButtonDiv.add(cartButton);

        cartButtonDiv.add(counter);



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
