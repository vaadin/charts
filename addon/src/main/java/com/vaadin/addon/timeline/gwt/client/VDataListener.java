package com.vaadin.addon.timeline.gwt.client;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 2.0
 * (CVALv2).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv2 along with this program.
 * If not, see <http://vaadin.com/license/cval-2.0>.
 * #L%
 */

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface VDataListener {
    public void dataRecieved(Long requestID, List<Float> values,
            List<Date> dates, Set<String> markers, Set<String> events,
            int density);

    public void dataRecievedAll(List<Long> requestID,
            Map<Integer, List<Float>> values, Map<Integer, List<Date>> dates,
            Set<String> markers, Set<String> events, Map<Integer, Float> min,
            Map<Integer, Float> max, Float totalMinimum, Float totalMaximum,
            int minDensity);

    public void setCurrentRequestId(Long requestID, Integer graphIndex);

    public boolean isVisible();
}
