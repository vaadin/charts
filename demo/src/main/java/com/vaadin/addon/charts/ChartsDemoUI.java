package com.vaadin.addon.charts;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.reflections.Reflections;
import org.vaadin.googleanalytics.tracking.GoogleAnalyticsTracker;

import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.themes.GrayTheme;
import com.vaadin.addon.charts.themes.GridTheme;
import com.vaadin.addon.charts.themes.HighChartsDefaultTheme;
import com.vaadin.addon.charts.themes.SkiesTheme;
import com.vaadin.addon.charts.themes.VaadinTheme;
import com.vaadin.addon.charts.themes.ValoDarkTheme;
import com.vaadin.addon.charts.themes.ValoLightTheme;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.server.Page.UriFragmentChangedListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@JavaScript("prettify.js")
@Theme("charts-demo")
@Title("Vaadin Charts Demo")
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

    private static final String[] GROUP_ORDER = { "columnandbar", "pie",
            "area", "lineandscatter", "dynamic", "combinations", "other",
            "container", "timeline", "threed", "declarative" };
    private static final String[] GROUP_CAPTIONS = { "Column and Bar", "Pie",
            "Area", "Line and Scatter", "Dynamic", "Combinations", "Other",
            "Container", "Timeline", "3D", "Declarative" };

    static String splitCamelCase(String s) {
        String replaced = s.replaceAll(String.format("%s|%s|%s",
                "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
                "(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
        // Make things look nicer with some special case handling
        replaced = replaced.replaceAll("And", "and");
        replaced = replaced.replaceAll("With", "with");
        replaced = replaced.replaceAll("To", "to");
        replaced = replaced.replaceAll("Of", "of");
        replaced = replaced.replaceAll("Api", "API");
        replaced = replaced.replaceAll("3 D", "3D");
        return replaced.substring(0, 1).toUpperCase() + replaced.substring(1);
    }

    static {
        Reflections reflections = new Reflections(
                "com.vaadin.addon.charts.examples");

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
    private ComboBox themeSelector;
    private GoogleAnalyticsTracker tracker;
    private TabSheet tabSheet;

    @Override
    protected void init(VaadinRequest request) {
        initGATracker();

        tabSheet = new TabSheet();
        tabSheet.addSelectedTabChangeListener(new SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(SelectedTabChangeEvent event) {
                com.vaadin.ui.JavaScript
                        .eval("setTimeout(function(){prettyPrint();},300);");
            }
        });
        tabSheet.setSizeFull();
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

        Link homepage = new Link("Home Page", new ExternalResource(
                "https://vaadin.com/components/vaadin-charts"));
        Link javadoc = new Link("JavaDoc", new ExternalResource(
                "http://demo.vaadin.com/javadoc/com.vaadin.addon/vaadin-charts/"
                        + getVersion() + "/"));
        Link manual = new Link("Manual", new ExternalResource(
                "https://vaadin.com/docs/v7/charts/charts-overview.html"));
        Link pricing = new Link("Pricing",
                new ExternalResource("https://vaadin.com/pricing"));
        Label version = new Label("Version " + getVersion());
        version.addStyleName("version");

        HorizontalLayout links = new HorizontalLayout(homepage, pricing,
                javadoc, manual);
        links.setSpacing(true);
        links.addStyleName("links");

        final HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
        horizontalSplitPanel.setSecondComponent(tabSheet);
        horizontalSplitPanel.setSplitPosition(300, Unit.PIXELS);
        horizontalSplitPanel.addStyleName("main-layout");

        ChartOptions.get().setTheme(new ValoLightTheme());

        themeSelector = new ComboBox("Charts Theme:");
        themeSelector.addStyleName("theme-selector");
        themeSelector.addStyleName(ValoTheme.COMBOBOX_SMALL);
        themeSelector.setTextInputAllowed(false);
        themeSelector.setNullSelectionAllowed(false);
        themeSelector.addItem(ValoLightTheme.class);
        themeSelector.setItemCaption(ValoLightTheme.class, "Valo Light");
        themeSelector.addItem(ValoDarkTheme.class);
        themeSelector.setItemCaption(ValoDarkTheme.class, "Valo Dark");
        themeSelector.addItem(VaadinTheme.class);
        themeSelector.setItemCaption(VaadinTheme.class, "Vaadin");
        themeSelector.addItem(HighChartsDefaultTheme.class);
        themeSelector
                .setItemCaption(HighChartsDefaultTheme.class, "Highcharts");
        themeSelector.addItem(GridTheme.class);
        themeSelector.setItemCaption(GridTheme.class, "Grid");
        themeSelector.addItem(GrayTheme.class);
        themeSelector.setItemCaption(GrayTheme.class, "Gray");
        themeSelector.addItem(SkiesTheme.class);
        themeSelector.setItemCaption(SkiesTheme.class, "Skies");
        themeSelector.setImmediate(true);
        themeSelector.select(ValoLightTheme.class);
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

        final HierarchicalContainer container = getContainer();

        VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);
        content.setMargin(false);

        Label logo = new Label("Vaadin Charts for Vaadin 7");
        logo.addStyleName("h3");
        logo.addStyleName("logo");

        TextField filterField = new TextField();
        filterField.setInputPrompt("Filter examples");
        filterField.setIcon(FontAwesome.SEARCH);
        filterField.addStyleName("filter");
        filterField.setWidth("100%");
        filterField.addTextChangeListener(new TextChangeListener() {

            @Override
            public void textChange(TextChangeEvent event) {
                container.removeAllContainerFilters();
                String text = event.getText();
                if (text != null && !text.isEmpty()) {
                    expandForFiltering();
                    container.addContainerFilter("searchName", text, true,
                            false);
                } else {
                    restoreExpandedStates();
                }
            }
        });

        tree = new Tree();
        tree.setImmediate(true);
        tree.setContainerDataSource(container);
        tree.setItemCaptionPropertyId("displayName");
        tree.setNullSelectionAllowed(false);
        tree.setWidth("100%");
        tree.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                Object value = event.getProperty().getValue();
                if (value instanceof Class) {
                    updateTabSheet((Class) value);
                } else {
                    tree.expandItemsRecursively(value);
                }
            }
        });

        Button trial = new Button("Start Free Trial");
        trial.addStyleName(ValoTheme.BUTTON_PRIMARY);
        trial.addStyleName(ValoTheme.BUTTON_TINY);
        trial.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                getUI().getPage().open("https://vaadin.com/trial", "_blank");
            }
        });

        
        content.addComponents(logo, links, trial, filterField, tree,
                version);
        content.setComponentAlignment(trial, Alignment.MIDDLE_CENTER);

        horizontalSplitPanel.setFirstComponent(content);

        selectItem();

        Page.getCurrent().addUriFragmentChangedListener(
                new UriFragmentChangedListener() {
                    @Override
                    public void uriFragmentChanged(UriFragmentChangedEvent event) {
                        selectItem();
                    }
                });

        setContent(new CssLayout() {
            {
                setSizeFull();
                addComponent(horizontalSplitPanel);
                addComponent(themeSelector);
            }
        });

        if (tracker != null) {
            tracker.trackPageview("/charts");
        }
    }

    /**
     * Updates main tabSheet
     * 
     * Adds one tab with one example instance, one with the java source and
     * another one with html source in case of declarative example
     * 
     * @param chartExample
     */
    private void updateTabSheet(Class chartExample) {
        try {
            tabSheet.removeAllComponents();

            AbstractVaadinChartExample newInstance = (AbstractVaadinChartExample) chartExample
                    .newInstance();
            tabSheet.addTab(newInstance, "Example");

            addResourceTab(chartExample,
                    chartExample.getSimpleName() + ".java", "Java Source");

            if (chartExample.isAnnotationPresent(DesignRoot.class)) {
                String designRoot = ((DesignRoot) chartExample
                        .getAnnotation(DesignRoot.class)).value();
                addResourceTab(chartExample, designRoot, "HTML Source");
            }
            Page.getCurrent().setUriFragment(chartExample.getSimpleName(),
                    false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a new tab to main tabsheet with the specified resource and name
     * 
     * @param clazz
     * @param resourceName
     * @param tabName
     */
    private void addResourceTab(Class clazz, String resourceName, String tabName) {
        try {
            InputStream resourceAsStream = clazz
                    .getResourceAsStream(resourceName);
            String code = IOUtils.toString(resourceAsStream);

            Panel p = getSourcePanel(code);

            tabSheet.addTab(p, tabName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a Panel with the code input pretty printed in a Label
     * 
     * @param code
     * @return
     */
    private Panel getSourcePanel(String code) {
        Panel p = new Panel();
        p.setWidth("100%");
        p.setStyleName(ValoTheme.PANEL_BORDERLESS);
        code = code.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;");
        Label c = new Label("<pre class='prettyprint'>" + code + "</pre>");
        c.setContentMode(ContentMode.HTML);
        c.setSizeUndefined();
        p.setContent(c);
        return p;
    }

    HashSet<Object> expandedItemIds = new HashSet<Object>();

    private void expandForFiltering() {
        expandedItemIds.clear();
        for (Object itemId : tree.getVisibleItemIds()) {
            if (tree.isExpanded(itemId)) {
                expandedItemIds.add(itemId);
            }
            tree.expandItemsRecursively(itemId);
        }
    }

    private void restoreExpandedStates() {
        for (Object itemId : tree.getVisibleItemIds()) {
            if (!expandedItemIds.contains(itemId) && tree.isExpanded(itemId)) {
                tree.collapseItem(itemId);
            }
        }
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
        hierarchicalContainer.addContainerProperty("searchName", String.class,
                "");

        for (int i = 0; i < GROUP_CAPTIONS.length; i++) {
            String group = GROUP_ORDER[i];
            String groupName = GROUP_CAPTIONS[i];
            Item groupItem = hierarchicalContainer.addItem(group);
            groupItem.getItemProperty("displayName").setValue(groupName);
            groupItem.getItemProperty("searchName").setValue(groupName);
            List<Class<? extends AbstractVaadinChartExample>> list = tests
                    .get(group);
            for (Class<? extends AbstractVaadinChartExample> class1 : list) {
                Item testItem = hierarchicalContainer.addItem(class1);
                String itemName = splitCamelCase(class1.getSimpleName());
                testItem.getItemProperty("displayName").setValue(itemName);
                testItem.getItemProperty("searchName").setValue(
                        groupName + " " + itemName);
                hierarchicalContainer.setParent(class1, group);
                hierarchicalContainer.setChildrenAllowed(class1, false);
            }

        }

        return hierarchicalContainer;
    }

    private void initGATracker() {
        // Provide a Google Analytics tracker id here
        String trackerId = "UA-658457-6";
        if (trackerId != null) {
            tracker = new GoogleAnalyticsTracker(trackerId, "demo.vaadin.com");
            tracker.extend(UI.getCurrent());
        }
    }

}
