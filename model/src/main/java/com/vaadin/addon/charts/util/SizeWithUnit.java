package com.vaadin.addon.charts.util;

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
import com.vaadin.addon.charts.model.Unit;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SizeWithUnit implements Serializable {
    public static String pattern="^(-?\\d*(?:\\.\\d+)?)(%|px)?$";
    /**
     * RegEx pattern to extract the width/height values.
     */
    public static final Pattern sizePattern = Pattern.compile(pattern);;
    private float size;
    private Unit unit;

    /**
     * Constructs a new SizeWithUnit object representing the pair (size, unit).
     *
     * @param size
     *            a numeric value
     * @param unit
     *            a unit
     */
    public SizeWithUnit(float size, Unit unit) {
        this.size = size;
        this.unit = unit;
    }

    /**
     * Returns the numeric value stored in this object.
     *
     * @return the value of this (value, unit) pair
     */
    public float getSize() {
        return size;
    }

    /**
     * Returns the unit stored in this object.
     *
     * @return the unit of this (value, unit) pair
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Returns an object whose numeric value and unit are taken from the string
     * s. If s does not specify a unit and defaultUnit is not null, defaultUnit
     * is used as the unit. If defaultUnit is null and s is a nonempty string
     * representing a unitless number, an exception is thrown. Null or empty
     * string will produce {-1,Unit#PIXELS}.
     *
     * @param s
     *            the string to be parsed
     * @param defaultUnit
     *            The unit to be used if s does not contain any unit. Use null
     *            for no default unit.
     * @return an object containing the parsed value and unit
     */
    public static SizeWithUnit parseStringSize(String s, Unit defaultUnit) {
        if (s == null) {
            return null;
        }
        s = s.trim();
        if ("".equals(s)) {
            return null;
        }
        float size = 0;
        Unit unit = null;
        Matcher matcher = sizePattern.matcher(s);
        if (matcher.find()) {
            size = Float.parseFloat(matcher.group(1));
            if (size < 0) {
                size = -1;
                unit = Unit.PIXELS;
            } else {
                String symbol = matcher.group(2);
                if ((symbol != null && symbol.length() > 0)
                        || defaultUnit == null) {
                    unit = Unit.getUnitFromSymbol(symbol);
                } else {
                    unit = defaultUnit;
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid size argument: \"" + s
                    + "\" (should match " + sizePattern.pattern() + ")");
        }
        return new SizeWithUnit(size, unit);
    }

    /**
     * Returns an object whose numeric value and unit are taken from the string
     * s. Null or empty string will produce {-1,Unit#PIXELS}. An exception is
     * thrown if s specifies a number without a unit.
     *
     * @param s
     *            the string to be parsed
     * @return an object containing the parsed value and unit
     */
    public static SizeWithUnit parseStringSize(String s) {
        return parseStringSize(s, null);
    }
}