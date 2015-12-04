import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/*", asyncSupported = true, initParams = {
        @WebInitParam(name = "heartbeatInterval", value = "10"),
        @WebInitParam(name = "widgetset", value = "com.vaadin.addon.charts.AppWidgetSet"),
        @WebInitParam(name = "UIProvider", value = "com.vaadin.addon.charts.demoandtestapp.AbstractUIProviderImpl")})
public class TestServlet extends VaadinServlet {
}
