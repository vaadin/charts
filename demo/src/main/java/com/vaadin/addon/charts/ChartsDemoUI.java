package com.vaadin.addon.charts;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.reflections.Reflections;

import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.SkipFromDemo;
import com.vaadin.addon.charts.themes.GrayTheme;
import com.vaadin.addon.charts.themes.GridTheme;
import com.vaadin.addon.charts.themes.HighChartsDefaultTheme;
import com.vaadin.addon.charts.themes.SkiesTheme;
import com.vaadin.addon.charts.themes.VaadinTheme;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.server.Page.UriFragmentChangedListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@JavaScript("prettify.js")
public class ChartsDemoUI extends UI {

    static final Properties prop = new Properties();
    static {
        try {
            // load a properties file
            prop.load(ChartsDemoUI.class
                    .getResourceAsStream("config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static String getVersion() {
        return (String) prop.get("charts.version");
    }

    private static Map<String, List<Class<? extends AbstractVaadinChartExample>>> tests;

    private static final String[] GROUP_ORDER = { "Column and Bar", "Pie",
            "Area", "Line and Scatter", "Dynamic", "Combinations", "Other",
            "Container", "Timeline" };

    static {
        Reflections reflections = new Reflections(
                "com.vaadin.addon.charts.demoandtestapp");

        Set<Class<? extends AbstractVaadinChartExample>> subTypes = reflections
                .getSubTypesOf(AbstractVaadinChartExample.class);

        Map<String, List<Class<? extends AbstractVaadinChartExample>>> grouped = new HashMap<String, List<Class<? extends AbstractVaadinChartExample>>>();

        for (Class<? extends AbstractVaadinChartExample> class1 : subTypes) {
            if (class1.getAnnotation(SkipFromDemo.class) != null) {
                continue;
            }
            Package package1 = class1.getPackage();
            String name = package1.getName();
            name = name.substring(name.lastIndexOf(".") + 1);

            List<Class<? extends AbstractVaadinChartExample>> list = grouped
                    .get(name);
            if (list == null) {
                list = new ArrayList<Class<? extends AbstractVaadinChartExample>>();
                grouped.put(name, list);
            }
            list.add(class1);
            Collections
                    .sort(list,
                            new Comparator<Class<? extends AbstractVaadinChartExample>>() {

                                @Override
                                public int compare(
                                        Class<? extends AbstractVaadinChartExample> o1,
                                        Class<? extends AbstractVaadinChartExample> o2) {
                                    String simpleName = o1.getSimpleName();
                                    String simpleName2 = o2.getSimpleName();
                                    return simpleName.compareTo(simpleName2);
                                }
                            });
        }
        tests = grouped;
    }

    private Tree tree;
    private OptionGroup themeSelector;

    @Override
    protected void init(VaadinRequest request) {

        final TabSheet tabSheet = new TabSheet();
        tabSheet.addSelectedTabChangeListener(new SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(SelectedTabChangeEvent event) {
                com.vaadin.ui.JavaScript
                        .eval("setTimeout(function(){prettyPrint();},300);");
            }
        });
        tabSheet.setSizeFull();

        CssLayout logoc = new CssLayout() {
            @Override
            protected String getCss(Component c) {
                if (c instanceof CssLayout) {
                    return "background: #007ea8; border-bottom: 1px solid #004e68;padding-left:6px;";
                }
                return null;
            }
        };
        logoc.setId("logoc");

        String cssString = "#logoc {position:relative; width:100%} #links {position:absolute; top:5px; right: 5px;}  #links a span {text-decoration: none;} #links .v-icon {height:25px;}";

        String script = "if ('\\v'=='v') /* ie only */ {\n"
                + "        document.createStyleSheet().cssText = '"
                + cssString
                + "';\n"
                + "    } else {var tag = document.createElement('style'); tag.type = 'text/css';"
                + " document.getElementsByTagName('head')[0].appendChild(tag);tag[ (typeof "
                + "document.body.style.WebkitAppearance=='string') /* webkit only */ ? 'innerText' "
                + ": 'innerHTML'] = '" + cssString + "';}";

        com.vaadin.ui.JavaScript.eval(script);

        CssLayout logow = new CssLayout();
        logoc.addComponent(logow);
        Image logo = new Image();
        logo.setAlternateText("Vaadin Charts logo");
        logo.setSource(new ClassResource("header.png"));
        logo.setHeight("60px");
        logo.addClickListener(new ClickListener() {

            @Override
            public void click(ClickEvent event) {
                Page.getCurrent().setLocation(
                        "https://vaadin.com/add-ons/charts");
            }
        });
        logow.addComponent(logo);

        Link homepage = new Link(null, new ExternalResource(
                "https://vaadin.com/add-ons/charts"));
        homepage.setIcon(new ClassResource("links_homepage.png"));
        Link javadoc = new Link(null, new ExternalResource(
                "http://demo.vaadin.com/javadoc/com.vaadin.addon/vaadin-charts/"
                        + getVersion() + "/"));
        javadoc.setIcon(new ClassResource("links_javadoc.png"));
        Link manula = new Link(null, new ExternalResource(
                "https://vaadin.com/book/vaadin7/-/page/charts.html"));
        manula.setIcon(new ClassResource("links_manual.png"));
        HorizontalLayout links = new HorizontalLayout(homepage, javadoc, manula);
        links.setSpacing(true);
        links.setId("links");
        logoc.addComponent(links);

        HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
        horizontalSplitPanel.setSecondComponent(tabSheet);
        horizontalSplitPanel.setSplitPosition(20);
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.addComponent(logoc);
        verticalLayout.addComponent(horizontalSplitPanel);
        verticalLayout.setExpandRatio(horizontalSplitPanel, 1);
        setContent(verticalLayout);

        themeSelector = new OptionGroup("Theme");
        themeSelector.addItem(VaadinTheme.class);
        themeSelector.setItemCaption(VaadinTheme.class, "Vaadin");
        themeSelector.addItem(SkiesTheme.class);
        themeSelector.setItemCaption(SkiesTheme.class, "Skies");
        themeSelector.addItem(GridTheme.class);
        themeSelector.setItemCaption(GridTheme.class, "Grid");
        themeSelector.addItem(GrayTheme.class);
        themeSelector.setItemCaption(GrayTheme.class, "Gray");
        themeSelector.addItem(HighChartsDefaultTheme.class);
        themeSelector
                .setItemCaption(HighChartsDefaultTheme.class, "Highcharts");
        themeSelector.setImmediate(true);
        themeSelector.select(VaadinTheme.class);
        themeSelector.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                @SuppressWarnings("unchecked")
                Class<? extends Theme> value = (Class<? extends Theme>) event
                        .getProperty().getValue();
                try {
                    ChartOptions.get().setTheme(
                            (com.vaadin.addon.charts.model.style.Theme) value
                                    .newInstance());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        tree = new Tree("Chart examples");
        tree.setImmediate(true);
        tree.setContainerDataSource(getContainer());
        tree.setItemCaptionPropertyId("displayName");

        VerticalLayout content = new VerticalLayout();
        content.setMargin(true);
        content.setSpacing(true);

        content.addComponent(tree);
        content.addComponent(themeSelector);
        horizontalSplitPanel.setFirstComponent(content);

        tree.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                Object value2 = event.getProperty().getValue();
                if (value2 instanceof Class) {
                    try {
                        Class value = (Class) value2;
                        AbstractVaadinChartExample newInstance = (AbstractVaadinChartExample) value
                                .newInstance();
                        tabSheet.removeAllComponents();
                        tabSheet.addTab(newInstance, "Graph");

                        String r = "/" + value.getName().replace(".", "/")
                                + ".java";
                        r = value.getSimpleName() + ".java";
                        InputStream resourceAsStream = newInstance.getClass()
                                .getResourceAsStream(r);
                        String code = IOUtils.toString(resourceAsStream);
                        Label c = new Label("<pre class='prettyprint'>" + code
                                + "</pre>");
                        c.setContentMode(ContentMode.HTML);
                        tabSheet.addTab(c, "Source");

                        Page.getCurrent().setUriFragment(value.getSimpleName(),
                                false);

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        // e.printStackTrace();
                    }
                } else {
                    tree.expandItemsRecursively(value2);
                }
            }
        });

        selectItem();

        Page.getCurrent().addUriFragmentChangedListener(
                new UriFragmentChangedListener() {
                    @Override
                    public void uriFragmentChanged(UriFragmentChangedEvent event) {
                        selectItem();
                    }
                });

    }

    private void selectItem() {
        String uriFragment = Page.getCurrent().getUriFragment();
        if (uriFragment != null) {
            Collection<?> itemIds = tree.getItemIds();
            for (Object object : itemIds) {
                if (object instanceof Class) {
                    @SuppressWarnings("unchecked")
                    Class<? extends AbstractVaadinChartExample> t = (Class<? extends AbstractVaadinChartExample>) object;
                    if (t.getSimpleName().equals(uriFragment)) {
                        Object parent2 = tree.getParent(t);
                        tree.expandItem(parent2);
                        tree.setValue(t);
                        return;
                    }
                }
            }
        } else {
            Iterator<?> iterator = tree.getItemIds().iterator();
            tree.expandItem(iterator.next());
            tree.select(iterator.next());
        }
        tree.focus();

    }

    private HierarchicalContainer getContainer() {

        HierarchicalContainer hierarchicalContainer = new HierarchicalContainer();
        hierarchicalContainer.addContainerProperty("displayName", String.class,
                "");

        for (String g : GROUP_ORDER) {
            String group = g.replaceAll(" ", "").toLowerCase();
            Item groupItem = hierarchicalContainer.addItem(group);
            groupItem.getItemProperty("displayName").setValue(g);
            List<Class<? extends AbstractVaadinChartExample>> list = tests
                    .get(group);
            for (Class<? extends AbstractVaadinChartExample> class1 : list) {
                Item testItem = hierarchicalContainer.addItem(class1);
                testItem.getItemProperty("displayName").setValue(
                        class1.getSimpleName());
                hierarchicalContainer.setParent(class1, group);
                hierarchicalContainer.setChildrenAllowed(class1, false);
            }

        }

        return hierarchicalContainer;
    }

}
