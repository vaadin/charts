package com.vaadin.addon.charts.model;

public enum Unit {
        PIXELS("px"),
        PERCENTAGE("%");

        private String symbol;
        private Unit(String symbol) {
                this.symbol = symbol;
        }

        public String getSymbol() {
                return symbol;
        }

        @Override
        public String toString() {
                return symbol;
        }

        public static Unit getUnitFromSymbol(String symbol) {
                if (symbol == null) {
                        return Unit.PIXELS; // Defaults to pixels
                }
                for (Unit unit : Unit.values()) {
                        if (symbol.equals(unit.getSymbol())) {
                                return unit;
                        }
                }
                return Unit.PIXELS; // Defaults to pixels
        }
}
