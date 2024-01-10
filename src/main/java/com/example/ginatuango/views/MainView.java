package com.example.ginatuango.views;

import com.example.ginatuango.data.entities.Category;
import com.example.ginatuango.data.entities.Item;
import com.example.ginatuango.data.entities.ItemSaleType;
import com.example.ginatuango.services.CategoryService;
import com.example.ginatuango.services.ImageService;
import com.example.ginatuango.services.ItemSaleTypeService;
import com.example.ginatuango.services.ItemService;
import com.example.ginatuango.views.components.Line;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.*;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "", layout = UserLayout.class)
@PermitAll
public class MainView extends VerticalLayout {

    private final ItemService itemService;
    private final ImageService imageService;
    private final ItemSaleTypeService itemSaleTypeService;

    public MainView(ItemService itemService, ImageService imageService, ItemSaleTypeService itemSaleTypeService){
        this.itemService = itemService;
        this.imageService = imageService;
        this.itemSaleTypeService = itemSaleTypeService;
//        H1 title = new H1("Hello World");
//        add(title);

        TabSheet tabs = new TabSheet();

        //fruits
        //tabsheet needs content to be in a div
        //TO-DO need to add item service method to get items based on categories
        Tab fruitsTab = new Tab("Fruits");
        VirtualList<Item> fruitsList = new VirtualList<>();
        fruitsList.setItems(itemService.getItems());
        Div fruitsDiv = new Div();
        fruitsDiv.setWidthFull();
        fruitsDiv.setHeightFull();
        fruitsDiv.add(fruitsList);

        ComponentRenderer<Component,Item> fruitRenderer = new ComponentRenderer<>(fruitItem ->{
            VerticalLayout card = new VerticalLayout();

            HorizontalLayout horizontalLayout = new HorizontalLayout();

            VerticalLayout fruitInfo = new VerticalLayout();

            H4 fruitName = new H4(fruitItem.getName());
            Text fruitDescription = new Text(fruitItem.getDescription());
            Text fruitPrice = new Text("$" + fruitItem.getPrice());
            Html newLine = new Html("<br>");
            fruitInfo.add(fruitName, fruitDescription, newLine, fruitPrice);
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
            quantity.setLabel("Quantity");

            Button addToCart = new Button();
            addToCart.setText("Add To Cart");

            buyLayout.add(categoryComboBox, quantity,addToCart);




            horizontalLayout.add(fruitInfo, buyLayout);
            card.add(horizontalLayout, new Line());

            return card;
        });
        fruitsList.setRenderer(fruitRenderer);

        Tab vegetablesTab = new Tab("Vegetables");
        Tab groceryTab = new Tab("Grocery");
        Tab othersTab = new Tab("Other");

        tabs.add(fruitsTab, fruitsDiv);
        tabs.setSelectedTab(fruitsTab);
        tabs.setWidthFull();
        tabs.addThemeVariants(TabSheetVariant.LUMO_TABS_CENTERED);

        add(tabs);









    }

}
