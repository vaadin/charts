package com.vaadin.addon.charts.examples.pie;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.LegendItemClickEvent;
import com.vaadin.addon.charts.LegendItemClickListener;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.shared.MouseEventDetails;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

@SkipFromDemo
public class PieWithLegendAndMouseDetails extends PieWithLegend {

    public static final String MOUSE_DETAILS = "mouse-details";
    private Label mouseDetails = new Label();

    @Override
    protected Component getChart() {

        Chart chart = (Chart) super.getChart();
        chart.addLegendItemClickListener(new LegendItemClickListener() {

            @Override
            public void onClick(LegendItemClickEvent event) {
                updateMouseEventDetailsForLegendClick(
                        event.getMouseEventDetails());

            }
        });
        return chart;
    }

    @Override
    protected void setup() {
        super.setup();
        addComponent(mouseDetails);
        mouseDetails.setId(MOUSE_DETAILS);
    }

    protected void updateMouseEventDetailsForLegendClick(
            MouseEventDetails mouseEventDetails) {

        StringBuilder sb = new StringBuilder();

        sb.append("AbsX: " + mouseEventDetails.getAbsoluteX());
        sb.append(" ").append("AbsY: " + mouseEventDetails.getAbsoluteY());
        sb.append(" ").append("RelX: " + mouseEventDetails.getxValue());
        sb.append(" ").append("RelY: " + mouseEventDetails.getyValue());
        sb.append(" ").append("Bttn: " + mouseEventDetails.getButtonName());
        sb.append(" ").append("Alt: " + mouseEventDetails.isAltKey());
        sb.append(" ").append("Ctrl: " + mouseEventDetails.isCtrlKey());
        sb.append(" ").append("Meta: " + mouseEventDetails.isMetaKey());
        sb.append(" ").append("Shift: " + mouseEventDetails.isShiftKey());

        mouseDetails.setValue(sb.toString());

    }

}
