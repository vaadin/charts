package com.vaadin.demo.sampler.sample;

import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;

/*
 * FIXME this is hard copy of interface in sampler-sample module until it can be found from a public repository.
 */
public interface Sample {

    /** Should the sample area be resizable */
    boolean isResingSamplePanelEnabled();

    // boolean isSampleWrappedWithCenteringLayoutInSamplePanel();

    /**
     * Get a Component wrapped to a desired layout. Can be a composite of
     * multiple components. The Sampler doesn't handle wrapping itself.
     */
    Component getWrappedComponent();

    /**
     * Get the component whose properties are manipulated trough propertyeditor.<br/>
     * <br/>
     * If returns null, property editor will not be enabled for the sample.
     */
    Component getComponentUsedByPropertyEditor();

    /** Get the property ids exluded in property editor. */
    Object[] getPropertyEditorExcludeList();

    /** Field getCustomFieldForPropertyAndBind(final String propertyId); */
    Field<? extends Object>[] getExtraFieldsForPropertyEditor();

    /** Get the extra components rendered below the main sample area */
    Component getConfiguratorComponentToShowBelowPanel();

    /**
     * Get the same item of the Component used by property editor and possible
     * extra control components
     */
    Item getSampleItem();

}
