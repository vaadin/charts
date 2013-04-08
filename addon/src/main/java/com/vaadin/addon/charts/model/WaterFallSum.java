package com.vaadin.addon.charts.model;

/**
 * DataSeriesItem that can hold booleas relevant for waterfall charts. Note tha
 * sums don't support all standard point features and their value don't need be
 * be set.
 */
public class WaterFallSum extends DataSeriesItem {

    @SuppressWarnings("unused")
    private Boolean isSum = Boolean.TRUE;
    private Boolean isIntermediateSum;

    public WaterFallSum(String name) {
        setName(name);
    }

    /**
     * @param intermediate true if the sum is should be intermediate
     */
    public void setIntermediate(boolean intermediate) {
        if(intermediate) {
            this.isIntermediateSum = Boolean.TRUE;
            this.isSum = null;
        } else {
            this.isIntermediateSum = null;
            this.isSum = Boolean.TRUE;
        }
    }
    
    public boolean isIntermediate() {
        return isIntermediateSum != null;
    }
}
