package com.example.ginatuango.views.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;

public class Counter extends Div {

    public Counter(int count){
        this.getStyle().setMargin("0px");
        this.getStyle().setPadding("0px");

        Span spanCount = new Span(String.valueOf(count));
        spanCount.getElement().getThemeList().add("badge pill small contrast");
        spanCount.getStyle().set("margin-inline-start", "var(--lumo-space-s)");
        add(spanCount);
    }
}
