package com.example.ginatuango.views;


import com.example.ginatuango.data.entities.Item;
import com.example.ginatuango.services.ItemService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/items", layout = AdminLayout.class)
public class AdminItemsView extends VerticalLayout {

    private final ItemService itemService;

    Grid<Item> itemGrid = new Grid<>(Item.class, false);

    @Autowired
    public AdminItemsView(ItemService itemService){
        this.itemService = itemService;
        H1 itemTitle = new H1();
        itemTitle.addClassName("center");
        itemTitle.setWidthFull();
        itemTitle.setText("Items");
        configureItemsGrid();

        add(itemTitle,itemGrid);

    }

    private void configureItemsGrid() {
//        itemGrid.addComponentColumn(item -> new RouterLink("Edit", AdminSpecificItemView.class, item.getId()));
        itemGrid.setItems(itemService.getItems());
        itemGrid.addColumn(item -> item.getId()).setHeader("ID");
        itemGrid.addColumn(item -> item.getName()).setHeader("Item").setSortable(true);;
        itemGrid.addColumn(item -> item.getCategory().getName()).setHeader("Category").setSortable(true);;
        itemGrid.addColumn(item -> item.getDescription()).setHeader("Description");
        itemGrid.addColumn(item -> item.getStock()).setHeader("Stock").setSortable(true);;
        itemGrid.addColumn(item -> item.getPrice()).setHeader("$ Price").setSortable(true);
        itemGrid.addItemClickListener(itemItemClickEvent -> {
            itemGrid.getUI().ifPresent(ui -> ui.navigate(AdminSpecificItemView.class,(Integer) itemItemClickEvent.getItem().getId()));
        });

        itemGrid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);






    }

}
