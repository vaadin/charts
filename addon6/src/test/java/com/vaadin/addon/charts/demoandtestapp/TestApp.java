package com.vaadin.addon.charts.demoandtestapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.Window;

public class TestApp extends Application implements HttpServletRequestListener {
	private String relativeUri;

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
			        if(name.contains(".")) {
			            window = new TestUI(name);
			        } else {
                                    window = new TestUI(getClass().getPackage().getName() + relativeUri.replace("/", "."));
			        }
				addWindow(window);
			}
		}
		return window;
	}

    @Override
    public void onRequestStart(HttpServletRequest request,
            HttpServletResponse response) {
        relativeUri = request.getPathInfo();
        
    }

    @Override
    public void onRequestEnd(HttpServletRequest request,
            HttpServletResponse response) {
        // TODO Auto-generated method stub
        
    }
}