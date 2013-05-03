/*
@VaadinAddonLicenseForJavaFiles@
 */
package com.vaadin.addon.timeline.gwt.client;

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
