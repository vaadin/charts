/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.examples;

import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.util.IndexedContainer;

@SuppressWarnings("unchecked")
public final class ExampleUtil {

    public static final Object ORDER_DESCRIPTION_PROPERTY_ID = "description";
    public static final Object ORDER_QUANTITY_PROPERTY_ID = "quantity";
    public static final Object ORDER_UNITPRICE_PROPERTY_ID = "unitprice ($)";
    public static final Object ORDER_ITEMPRICE_PROPERTY_ID = "itemprice ($)";

    public static IndexedContainer getOrderContainer() {
        IndexedContainer container = new IndexedContainer();

        // Create the container properties
        container.addContainerProperty(ORDER_DESCRIPTION_PROPERTY_ID,
                String.class, "");
        container.addContainerProperty(ORDER_QUANTITY_PROPERTY_ID,
                Integer.class, 0);
        container.addContainerProperty(ORDER_UNITPRICE_PROPERTY_ID,
                Double.class, 0);
        container.addContainerProperty(ORDER_ITEMPRICE_PROPERTY_ID,
                Double.class, 0);

        // Create some orders
        addOrderToContainer(container, "Domain Name", 3, 7.99);
        addOrderToContainer(container, "SSL Certificate", 1, 119.00);
        addOrderToContainer(container, "Web Hosting", 1, 19.95);
        addOrderToContainer(container, "Email Box", 20, 0.15);
        addOrderToContainer(container, "E-Commerce Setup", 1, 25.00);
        addOrderToContainer(container, "Technical Support", 1, 50.00);

        return container;
    }

    private static void addOrderToContainer(Container container,
            String description, int quantity, double price) {
        Object itemId = container.addItem();
        Item item = container.getItem(itemId);
        item.getItemProperty(ORDER_DESCRIPTION_PROPERTY_ID).setValue(
                description);
        item.getItemProperty(ORDER_QUANTITY_PROPERTY_ID).setValue(quantity);
        item.getItemProperty(ORDER_UNITPRICE_PROPERTY_ID).setValue(price);
        item.getItemProperty(ORDER_ITEMPRICE_PROPERTY_ID).setValue(
                (price * quantity));
    }

}
