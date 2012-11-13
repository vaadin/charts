package com.vaadin.addon.timeline.gwt.client;

import com.google.gwt.core.client.GWT;
import com.vaadin.addon.timeline.Timeline;
import com.vaadin.client.DirectionalManagedLayout;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.LegacyConnector;
import com.vaadin.shared.ui.Connect;

@Connect(Timeline.class)
public class TimelineConnector extends LegacyConnector implements
DirectionalManagedLayout {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.terminal.gwt.client.DirectionalManagedLayout#layoutVertically
     * ()
     */
    @Override
    public void layoutVertically() {
        int height = getLayoutManager()
                .getOuterHeight(getWidget().getElement());
        getWidget().recalculateHeights(height);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.terminal.gwt.client.DirectionalManagedLayout#layoutHorizontally
     * ()
     */
    @Override
    public void layoutHorizontally() {
        int width = getLayoutManager().getOuterWidth(getWidget().getElement());
        getWidget().recalculateWidths(width);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.terminal.gwt.client.ui.AbstractComponentConnector#getWidget()
     */
    @Override
    public VTimelineWidget getWidget() {
        return (VTimelineWidget) super.getWidget();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.terminal.gwt.client.ui.AbstractComponentConnector#createWidget
     * ()
     */
    @Override
    protected VTimelineWidget createWidget() {
        return GWT.create(VTimelineWidget.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.terminal.gwt.client.ui.AbstractComponentConnector#
     * delegateCaptionHandling()
     */
    @Override
    public boolean delegateCaptionHandling() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.terminal.gwt.client.ui.AbstractComponentConnector#onStateChanged
     * (com.vaadin.terminal.gwt.client.communication.StateChangeEvent)
     */
    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        getWidget().setCaption(getState().caption);
    }
}
