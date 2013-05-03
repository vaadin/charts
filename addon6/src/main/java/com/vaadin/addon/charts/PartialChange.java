package com.vaadin.addon.charts;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;

interface PartialChange {

    public void paint(Chart chart, PaintTarget target)
            throws PaintException;

}