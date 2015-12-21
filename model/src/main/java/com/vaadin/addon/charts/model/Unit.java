package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */

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
