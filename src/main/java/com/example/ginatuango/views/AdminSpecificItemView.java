package com.example.ginatuango.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;


@Route(value = "item",layout = AdminLayout.class)
public class AdminSpecificItemView extends VerticalLayout implements HasUrlParameter<Integer> {

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {

    }

}
