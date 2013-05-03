package com.vaadin.addon.charts.demoandtestapp;

import com.vaadin.Application;
import com.vaadin.ui.Window;

public class TestApp extends Application {
	@Override
	public void init() {
		setMainWindow(new TListUi());
	}

	@Override
	public Window getWindow(String name) {
		Window window = super.getWindow(name);
		if (window == null) {
			if (name == null || name.isEmpty()) {
				window = getMainWindow();
			} else {
				window = new TestUI(name);
				addWindow(window);
			}
		}
		return window;
	}
}