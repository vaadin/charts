package com.vaadin.addon.charts.demoandtestapp;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.demo.sampler.sample.Sample;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class AbstractVaadinChartExample extends VerticalLayout implements Sample {

    private final VerticalLayout content;

    public AbstractVaadinChartExample() {
        content = this;
        content.setSizeFull();
    }

    protected void setup() {
        Component map = getChart();
        content.addComponent(map);
        content.setExpandRatio(map, 1);
    }

    protected abstract Component getChart();

    @Override
    public void attach() {
        super.attach();
        setup();
    }
    
    @Override
    public boolean isResingSamplePanelEnabled() {
        return true;
    }

    @Override
    public Component getWrappedComponent() {
        setup();
        return content;
    }

    @Override
    public Component getComponentUsedByPropertyEditor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object[] getPropertyEditorExcludeList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Field<? extends Object>[] getExtraFieldsForPropertyEditor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Component getConfiguratorComponentToShowBelowPanel() {
        // TODO Auto-generated method stub
        return null;
    }

    private Item sampleItem;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Item getSampleItem() {
        if (sampleItem == null) {
            sampleItem = new BeanItem(getComponentUsedByPropertyEditor());
        }
        return sampleItem;
    }
}
