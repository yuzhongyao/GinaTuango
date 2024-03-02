package com.example.ginatuango.views.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;

public class Counter extends Div {

    private Span spanCount;
    public Counter(int count){
        this.getStyle().setMargin("0px");
        this.getStyle().setPadding("0px");

        spanCount = new Span(String.valueOf(count));
        spanCount.getElement().getThemeList().add("badge pill small primary");
        spanCount.getStyle().set("margin-inline-start", "var(--lumo-space-s)");
        add(spanCount);
    }

    public Span getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(Span spanCount) {
        this.removeAll();
        this.spanCount = spanCount;
        add(spanCount);
    }
}
