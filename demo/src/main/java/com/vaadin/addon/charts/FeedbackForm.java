package com.vaadin.addon.charts;

import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Window;

public class FeedbackForm extends Window {

    CustomLayout content;

    public FeedbackForm() {
        super();
        content = new CustomLayout("feedback");
        content.setWidth(600, Unit.PIXELS);
        setContent(content);
        setModal(true);
        setResizable(false);
    }

}
