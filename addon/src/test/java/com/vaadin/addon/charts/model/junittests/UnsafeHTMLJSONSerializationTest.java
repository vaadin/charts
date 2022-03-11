package com.vaadin.addon.charts.model.junittests;

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Tooltip;

/**
 * Tests for the sanitization of unsafe HTML in JSON serialization in
 * {@link Configuration}
 *
 */
public class UnsafeHTMLJSONSerializationTest {

    @Test
    public void configurationJSONSerialization_tablePreserved() {
        Configuration conf = new Configuration();
        Tooltip tooltip = new Tooltip();
        tooltip.setUseHTML(true);
        tooltip.setPointFormat("<table><tr><td>{series.name}</td></tr><tr><td>{point.y}</td></tr></table>");
        conf.setTooltip(tooltip);
            assertEquals(
                "{\"tooltip\":{\"pointFormat\":\""<table>[\n <tbody>\n  <tr>\n   <td>{series.name}</td>\n  </tr>\n  <tr>\n   <td>{point.y}</td>\n  </tr>\n </tbody>\n</table>\"},\"plotOptions\":{},\"series\":[],\"exporting\":{\"enabled\":false}}",
                toJSON(conf));
    }

    @Test
    public void configurationJSONSerialization_plainString_notTouched() {
        Configuration conf = new Configuration();
        conf.setTitle(
                "This & that is this & that");
            assertEquals(
                "{\"title\":{\"text\":\"This & that is this & that\"},\"plotOptions\":{},\"series\":[],\"exporting\":{\"enabled\":false}}",
                toJSON(conf));
    }

    @Test
    public void configurationJSONSerialization_unsafeTitle_unsafeHTMLNotSerialized() {
        Configuration conf = new Configuration();
        conf.setTitle(
                "1. JavaScript injected in plain href: <a href=\'#\";alert(\"XSS 1\");var fake=\"\'>Click here</a><br> 2. JavaScript injected as href=\"javascript:...\": <a href=\"javascript:alert(\'XSS 2\')\">Click here</a><br> Source: <a href=\"http://thebulletin.metapress.com/content/c4120650912x74k7/fulltext.pdf\">thebulletin.metapress.com</a>");

        assertEquals(
                "{\"title\":{\"text\":\"1. JavaScript injected in plain href: <a rel=\\\"nofollow\\\">Click here</a>\\n<br> 2. JavaScript injected as href=\\\"javascript:...\\\": <a rel=\\\"nofollow\\\">Click here</a>\\n<br> Source: <a href=\\\"http://thebulletin.metapress.com/content/c4120650912x74k7/fulltext.pdf\\\" rel=\\\"nofollow\\\">thebulletin.metapress.com</a>\"},\"plotOptions\":{},\"series\":[],\"exporting\":{\"enabled\":false}}",
                toJSON(conf));
    }

    @Test
    public void configurationJSONSerialization_unsafeSubTitle_unsafeHTMLNotSerialized() {
        Configuration conf = new Configuration();
        conf.setSubTitle(
                "1. JavaScript injected in plain href: <a href=\'#\";alert(\"XSS 1\");var fake=\"\'>Click here</a><br> 2. JavaScript injected as href=\"javascript:...\": <a href=\"javascript:alert(\'XSS 2\')\">Click here</a><br> Source: <a href=\"http://thebulletin.metapress.com/content/c4120650912x74k7/fulltext.pdf\">thebulletin.metapress.com</a>");
        assertEquals(
                "{\"subtitle\":{\"text\":\"1. JavaScript injected in plain href: <a rel=\\\"nofollow\\\">Click here</a>\\n<br> 2. JavaScript injected as href=\\\"javascript:...\\\": <a rel=\\\"nofollow\\\">Click here</a>\\n<br> Source: <a href=\\\"http://thebulletin.metapress.com/content/c4120650912x74k7/fulltext.pdf\\\" rel=\\\"nofollow\\\">thebulletin.metapress.com</a>\"},\"plotOptions\":{},\"series\":[],\"exporting\":{\"enabled\":false}}",
                toJSON(conf));
    }
}
