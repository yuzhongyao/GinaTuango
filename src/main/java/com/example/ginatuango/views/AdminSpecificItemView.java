package com.example.ginatuango.views;

import com.example.ginatuango.data.entities.Category;
import com.example.ginatuango.data.entities.Item;
import com.example.ginatuango.services.ItemService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "item",layout = AdminLayout.class)
public class AdminSpecificItemView extends VerticalLayout implements HasUrlParameter<Integer> {

    private int id = -1;
    H1 title = new H1();
    TextField name = new TextField();
    TextArea description = new TextArea();
    TextField category = new TextField();
    TextField stock = new TextField();
    TextField price = new TextField();
    Item item;

    private final ItemService itemService;


    //Vaadin constructor runs before the setParameter method
    @Autowired
    public AdminSpecificItemView(ItemService itemService){
        this.itemService = itemService;

            add(title,name,description,category,stock,price);

    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {
        this.id = integer;
        loadItem();

    }

    private void loadItem(){
        item = itemService.getItem(id);
        title.setText(item.getName());
    }


}
