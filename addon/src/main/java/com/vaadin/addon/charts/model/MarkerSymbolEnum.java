package com.vaadin.addon.charts.model;

/**
 * A predefined shape or symbol for the marker. When null, the symbol is pulled
 * from options.symbols. Other possible values are "circle", "square",
 * "diamond", "triangle" and "triangle-down". Additionally, the URL to a graphic
 * can be given on this form: URL.setUrl("url(graphic.png)").
 * 
 */
public enum MarkerSymbolEnum implements MarkerSymbol, ChartEnum {
    CIRCLE("circle"), SQUARE("square"), DIAMOND("diamond"), TRIANGLE("triangle"), TRIANGLE_DOWN(
            "triangle-down");

    private String symbol;

    private MarkerSymbolEnum(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
