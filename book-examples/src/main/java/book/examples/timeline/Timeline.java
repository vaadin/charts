package book.examples.timeline;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Navigator;
import com.vaadin.addon.charts.model.PlotOptionsSeries;
import com.vaadin.addon.charts.model.RangeSelector;
import com.vaadin.addon.charts.model.style.ButtonTheme;
import com.vaadin.addon.charts.model.style.FontWeight;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;

public class Timeline {

    public void chartsTimelineIntroSnippet1() {
        Chart chart = new Chart();
        Configuration configuration = chart.getConfiguration();
        Navigator navigator = configuration.getNavigator();
        navigator.setEnabled(true);
        navigator.setMargin(75);
        PlotOptionsSeries plotOptions=new PlotOptionsSeries();
        plotOptions.setColor(SolidColor.BROWN);
        navigator.setSeries(plotOptions);
    }

    public void chartsTimelineIntroSnippet2() {
        RangeSelector rangeSelector = new RangeSelector();
        rangeSelector.setSelected(4);
        ButtonTheme theme = new ButtonTheme();
        Style style = new Style();
        style.setColor(new SolidColor("#0766d8"));
        style.setFontWeight(FontWeight.BOLD);
        theme.setStyle(style);
        rangeSelector.setButtonTheme(theme);

        Chart chart = new Chart();
        chart.setTimeline(true);
        Configuration configuration = chart.getConfiguration();
        configuration.setRangeSelector(rangeSelector);
    }

    public void chartsTimelineIntroSnippet3() {
        Chart chart = new Chart();
        Configuration configuration = chart.getConfiguration();
        RangeSelector rangeSelector = new RangeSelector();
        rangeSelector.setInputDateFormat("%YYYY-%MM-%DD:%H:%M");
        rangeSelector.setInputEditDateFormat("%YYYY-%MM-%DD:%H:%M");
        rangeSelector.setInputDateParser(
                "function(value) {" +
                        "value = value.split(/[:\\-]/);\n" +
                        "return Date.UTC(\n" +
                        "   parseInt(value[0], 10),\n" +
                        "   parseInt(value[1], 10),\n" +
                        "   parseInt(value[2], 10),\n" +
                        "   parseInt(value[3], 10),\n" +
                        "   parseInt(value[4], 10),\n" +
                        ");}");
        configuration.setRangeSelector(rangeSelector);
    }
}
