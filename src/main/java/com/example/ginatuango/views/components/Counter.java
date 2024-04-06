package com.example.ginatuango.views.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;

public class Counter extends Div {
    private int count;
    private Span spanCount;
    public Counter(int count){
        this.getStyle().setMargin("0px");
        this.getStyle().setPadding("0px");
        this.count = count;
        spanCount = new Span(String.valueOf(this.count));
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
    public void increment (){
        this.count++;
        this.spanCount.setText(String.valueOf(this.count));
    }
    public void decrement (){
        if(count == 1 || count == 0){
            this.count = 0;
        }
        else{
            this.count--;
        }
        this.spanCount.setText(String.valueOf(this.count));
    }

    public void setCount(int count){
        this.count = count;
        this.spanCount.setText(String.valueOf(this.count));

    }
}
