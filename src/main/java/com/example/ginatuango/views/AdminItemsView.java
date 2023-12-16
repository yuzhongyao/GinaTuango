package com.example.ginatuango.views;


import com.example.ginatuango.data.entities.Category;
import com.example.ginatuango.data.entities.Item;
import com.example.ginatuango.data.entities.ItemSale;
import com.example.ginatuango.data.entities.ItemSaleType;
import com.example.ginatuango.services.CategoryService;
import com.example.ginatuango.services.ImageService;
import com.example.ginatuango.services.ItemSaleService;
import com.example.ginatuango.services.ItemService;
import com.example.ginatuango.utils.UTILS;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "/admin/items", layout = AdminLayout.class)
public class AdminItemsView extends VerticalLayout {

    private final ItemService itemService;
    private final ImageService imageService;
    private final ItemSaleService itemSaleService;
    private final CategoryService categoryService;

    private Grid<Item> itemGrid = new Grid<>(Item.class, false);
    private List<Item> items;

    @Autowired
    public AdminItemsView(ItemService itemService, ImageService imageService,ItemSaleService itemSaleService, CategoryService categoryService){
        this.itemService = itemService;
        this.imageService = imageService;
        this.itemSaleService = itemSaleService;
        this.categoryService = categoryService;
        H1 itemTitle = new H1();
        itemTitle.addClassName("center");
        itemTitle.setWidthFull();
        itemTitle.setText("Items");
        configureItemsGrid();
        Paragraph paragraph = new Paragraph("Click a row to edit");

        Button addItemButton = new Button("ADD NEW ITEM");
        addItemButton.addClickListener(buttonClickEvent -> {
            addItem();
        });
        add(itemTitle,paragraph, addItemButton,itemGrid);

    }

    private void addItem(){
        Dialog dialog = new Dialog();
        Button close = new Button("Cancel", (e) -> dialog.close());
        Button add = new Button("ADD");
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add.addClickListener(buttonClickEvent -> {

        });

        H3 headerTitle = new H3("ADD NEW ITEM");

        VerticalLayout dialogLayout = new VerticalLayout();

        TextField name = new TextField("Name");
        name.setRequired(true);
        TextArea description = new TextArea("Description");
        description.setRequired(true);
        ComboBox<Category> category = new ComboBox<>("Category");
        category.setRequired(true);
        category.setItems(categoryService.getCategories());
        category.setItemLabelGenerator(Category::getName);
        TextField stock = new TextField("Stock");
        stock.setRequired(true);
        TextField price = new TextField("Price");
        price.setRequired(true);


        dialogLayout.add(name,description,category,stock,price);
        dialog.getHeader().add(headerTitle);
        dialog.getFooter().add(close);
        dialog.getFooter().add(add);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private void configureItemsGrid() {
//        itemGrid.addComponentColumn(item -> new RouterLink("Edit", AdminSpecificItemView.class, item.getId()));

        items = itemService.getItems();
        itemGrid.setItems(items);
        itemGrid.addColumn(item -> item.getId()).setHeader("ID");
        itemGrid.addColumn(item -> item.getName()).setHeader("Item").setSortable(true);;
        itemGrid.addColumn(item -> item.getCategory().getName()).setHeader("Category").setSortable(true);;
        itemGrid.addColumn(item -> item.getDescription()).setHeader("Description");
        itemGrid.addColumn(item -> item.getStock()).setHeader("Stock").setSortable(true);;
        itemGrid.addColumn(item -> item.getPrice()).setHeader("$ Price").setSortable(true);

        itemGrid.addItemClickListener(itemItemClickEvent -> {
            Item item = itemItemClickEvent.getItem();

            Dialog editor = new Dialog();
            editor.setHeaderTitle("Edit Item");

            VerticalLayout editorLayout = new VerticalLayout();
            TextField name = new TextField("Name");
            name.setValue(item.getName());
            TextArea description = new TextArea("Description");
            description.setValue(item.getDescription());
            TextField category = new TextField("Category");
            category.setValue(item.getCategory().getName());
            TextField stock = new TextField("Stock");
            stock.setValue(String.valueOf(item.getStock()));
            TextField price = new TextField("Price");
            price.setValue(String.valueOf(item.getPrice()));

            editorLayout.add(name,description,category,stock,price);

            Button delete = new Button("DELETE");
            delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            delete.addClickListener(buttonClickEvent -> {
                imageService.deleteImagesBtItemId(item.getId());
                List<ItemSale> itemsales =itemSaleService.getItemSalesByItemId(item.getId());
                if (!itemsales.isEmpty()){
                    itemSaleService.deleteItemSales(itemsales);
                }
                itemService.deleteItem(item);
                UTILS.showNotification(new Notification(),"SUCCESSFULLY DELETED", true);
                items.remove(item);
                itemGrid.getDataProvider().refreshAll();
                editor.close();

            });

            Button close = new Button("Cancel", (e) -> editor.close());
            close.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            Button save = new Button("Save");
            save.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
            save.addClickListener(buttonClickEvent -> {
                item.setName(name.getValue());
                item.setDescription(description.getValue());
                item.getCategory().setName(category.getValue());
                item.setPrice(Double.parseDouble(price.getValue()));
                item.setStock(Double.parseDouble(stock.getValue()));

                itemService.updateItem(item);
                itemGrid.getDataProvider().refreshItem(item);

                editor.close();
                UTILS.showNotification(new Notification(),"SUCCESSFULLY SAVED", true);
            });

            editor.getFooter().add(delete);
            editor.getFooter().add(close);
            editor.getFooter().add(save);
            editor.add(editorLayout);
            editor.open();

        });

        itemGrid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);






    }

}
