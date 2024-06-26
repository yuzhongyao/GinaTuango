package com.example.ginatuango.views;

import com.example.ginatuango.data.entities.*;
import com.example.ginatuango.services.*;
import com.example.ginatuango.utils.UTILS;
import com.example.ginatuango.views.components.Line;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.*;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

@Route(value = "", layout = UserLayout.class)
@PermitAll
public class MainView extends VerticalLayout {

    private final ItemService itemService;
    private final ImageService imageService;
    private final ItemSaleTypeService itemSaleTypeService;
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final transient AuthenticationContext authContext;
    private final UserService userService;
    private UserDetails userDetails;
    private Optional<User> user;
    private Cart cart;
    private List<CartItem> cartItems;

    public MainView(ItemService itemService, ImageService imageService, ItemSaleTypeService itemSaleTypeService,
            CartService cartService, CartItemService cartItemService, AuthenticationContext authenticationContext,
                    UserService userService){
        this.itemService = itemService;
        this.imageService = imageService;
        this.itemSaleTypeService = itemSaleTypeService;
        this.cartService = cartService;
        this.cartItemService = cartItemService;
        this.authContext = authenticationContext;
        this.userService = userService;
        userDetails = authContext.getAuthenticatedUser(UserDetails.class).get();
        user = userService.getUserByUsername(userDetails.getUsername());
        cart = cartService.getCartByUser(user.get().getId());
        cartItems = cartItemService.getCartItemsByCart(cart.getCart_id());


//        H1 title = new H1("Hello World");
//        add(title);

        TabSheet tabs = new TabSheet();

        //fruits
        //tabsheet needs content to be in a div
        Tab fruitsTab = new Tab("Fruits");
        VirtualList<Item> fruitsList = new VirtualList<>();
        List<Item> fruitItems = itemService.getItemsByCategory("Fruits");
        fruitsList.setItems(fruitItems);
        Div fruitsDiv = new Div();
        fruitsDiv.setWidthFull();
        fruitsDiv.setHeightFull();
        fruitsDiv.add(fruitsList);

        ComponentRenderer<Component,Item> itemRenderer = new ComponentRenderer<>(item ->{
            VerticalLayout card = new VerticalLayout();

            HorizontalLayout horizontalLayout = new HorizontalLayout();

            VerticalLayout fruitInfo = new VerticalLayout();

            //TO DO
            //update to use AWS to get images
            List<Image> imagesUrls = imageService.getImagesByItemId(item.getId());
            com.vaadin.flow.component.html.Image itemImage = new com.vaadin.flow.component.html.Image();
            itemImage.setSrc(imagesUrls.get(0).getImageUrl());
            itemImage.setMaxHeight("325px");
            itemImage.setMaxWidth("325px");


            H4 itemName = new H4(item.getName());
            Text fruitDescription = new Text(item.getDescription());
            Text fruitPrice = new Text("$" + item.getPrice());
            Html newLine = new Html("<br>");
            fruitInfo.add(itemName, fruitDescription, newLine, fruitPrice);
            horizontalLayout.setMargin(true);

            VerticalLayout buyLayout = new VerticalLayout();

            ComboBox<ItemSaleType> categoryComboBox = new ComboBox<>();
            ComboBox.ItemFilter<ItemSaleType> filter = (itemSaleType, filterString) -> itemSaleType
                    .getType().toLowerCase().startsWith(filterString.toLowerCase());
            categoryComboBox.setItems(filter,itemSaleTypeService.getItemSaleTypes());
            categoryComboBox.setItemLabelGenerator(ItemSaleType::getType);
            categoryComboBox.setLabel("Type");

            IntegerField quantity = new IntegerField();
            quantity.setValue(0);
            quantity.setStepButtonsVisible(true);
            quantity.setMin(0);
            quantity.setLabel("Quantity");

            //ADD TO CART BUTTON
            Button addToCart = new Button();
            addToCart.setText("Add To Cart");
            addToCart.addClickListener(buttonClickEvent -> {

                try{
                    if(categoryComboBox.getValue() == null || quantity.getValue() ==0){
                        throw new Exception();
                    }
                    CartItem exists = cartItemService.getCartItemByCartAndItemAndType(cart.getCart_id(),
                            item.getId(), categoryComboBox.getValue().getId());

                    //if not already in cart
                    if(exists == null){
                        CartItem cartItem = new CartItem(cart.getCart_id(), item,quantity.getValue(), categoryComboBox.getValue());
                        cartItemService.addCartItem(cartItem);
                        cartItems = cartItemService.getCartItemsByCart(cart.getCart_id());

//                        Span span = new Span(String.valueOf(cartItems.size()));
//                        span.getElement().getThemeList().add("badge pill small primary");
//                        span.getStyle().set("margin-inline-start", "var(--lumo-space-s)");
//
//                        UserLayout.counter.setSpanCount(span);
                        UserLayout.counter.increment();
                    }
                    //update cartitem to increment quantity
                    else{
                        exists.setQuantity(exists.getQuantity() + Double.parseDouble(quantity.getValue().toString()));
                        cartItemService.updateCartItem(exists);
                    }


                    UTILS.showNotification(new Notification(),"Added to cart",true);
                }catch (Exception e){
                    UTILS.showNotification(new Notification(),"Error adding to cart", false);
                }
            });


            buyLayout.add(categoryComboBox, quantity,addToCart);




            horizontalLayout.add(itemImage, fruitInfo, buyLayout);
            card.add(horizontalLayout, new Line());

            return card;
        });
        fruitsList.setRenderer(itemRenderer);

        Tab vegetablesTab = new Tab("Vegetables");
        VirtualList<Item> vegetablesList = new VirtualList<>();
        List<Item> vegetableItems = itemService.getItemsByCategory("Vegetables");
        vegetablesList.setItems(vegetableItems);
        Div vegetablesDiv = new Div();
        vegetablesDiv.setWidthFull();
        vegetablesDiv.setHeightFull();
        vegetablesDiv.add(vegetablesList);
        vegetablesList.setRenderer(itemRenderer);

        Tab groceryTab = new Tab("Grocery");
        VirtualList<Item> groceryList = new VirtualList<>();
        List<Item> groceryItems = itemService.getItemsByCategory("Grocery");
        groceryList.setItems(groceryItems);
        Div groceryDiv = new Div();
        groceryDiv.setWidthFull();
        groceryDiv.setHeightFull();
        groceryDiv.add(groceryList);
        groceryList.setRenderer(itemRenderer);

        Tab frozenTab = new Tab("Frozen");
        VirtualList<Item> frozenList = new VirtualList<>();
        List<Item> frozenItems = itemService.getItemsByCategory("Frozen");
        frozenList.setItems(frozenItems);
        Div frozenDiv = new Div();
        frozenDiv.setWidthFull();
        frozenDiv.setHeightFull();
        frozenDiv.add(frozenList);
        frozenList.setRenderer(itemRenderer);

        Tab othersTab = new Tab("Other");
        VirtualList<Item> othersList = new VirtualList<>();
        List<Item> othersItems = itemService.getItemsByCategory("Other");
        othersList.setItems(othersItems);
        Div othersDiv = new Div();
        othersDiv.setWidthFull();
        othersDiv.setHeightFull();
        othersDiv.add(othersList);
        othersList.setRenderer(itemRenderer);

        tabs.add(fruitsTab, fruitsDiv);
        tabs.add(vegetablesTab,vegetablesDiv);
        tabs.add(groceryTab,groceryDiv);
        tabs.add(frozenTab,frozenDiv);
        tabs.add(othersTab,othersDiv);

        tabs.setSelectedTab(fruitsTab);
        tabs.setWidthFull();
        tabs.addThemeVariants(TabSheetVariant.LUMO_TABS_CENTERED);

        add(tabs);
        this.setAlignItems(Alignment.CENTER);








    }

}
