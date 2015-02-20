package com.vaadin.addon.charts.demoandtestapp.timeline.sources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import com.vaadin.addon.timeline.Timeline;
import com.vaadin.data.util.IndexedContainer;

@SuppressWarnings("serial")
public class VaadinForumDataSource implements Serializable {

    private class DataRow {
        int posts;
        int newThreads;
        int vaadinPosts;
        int vaadinThreads;
        Date timestamp;
    }

    List<DataRow> data;

    public VaadinForumDataSource() {
        data = new ArrayList<DataRow>();
        BufferedReader in = null;

        try {
            URL url = new URL(
                    "http://vaadin.com/116a8f3bdc14cec51d523cfb7ce6e07a/forum.txt");

            in = new BufferedReader(new InputStreamReader(url.openStream()));

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String line;

            while ((line = in.readLine()) != null) {
                StringTokenizer tok = new StringTokenizer(line.trim());
                if (tok.countTokens() < 5) {
                    System.out.println("line has too few tokens: " + line);
                    break;
                }

                final Date d;
                try {
                    d = formatter.parse(tok.nextToken());
                } catch (ParseException e) {
                    continue;
                }
                if (!isDateInRange(d)) {
                    continue;
                }

                DataRow row = new DataRow();
                row.timestamp = d;
                row.posts = Integer.parseInt(tok.nextToken());
                row.newThreads = Integer.parseInt(tok.nextToken());
                row.vaadinPosts = Integer.parseInt(tok.nextToken());
                row.vaadinThreads = Integer.parseInt(tok.nextToken());
                data.add(row);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Date getLastAvailableDate() {
        return data.get(data.size()-1).timestamp;
    }

    private boolean isDateInRange(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);

        if (cal.get(Calendar.YEAR) < 2007) {
            return false;
        }

        if (cal.get(Calendar.YEAR) == 2011
                && cal.get(Calendar.MONTH) == Calendar.OCTOBER) {
            return false;
        }
        return true;
    }

    public void fillPostsContainer(IndexedContainer posts) {
        for (DataRow r : data) {
            addItem(posts, r.posts, r.timestamp);
        }
    }

    public void fillNewThreadsContainer(IndexedContainer newThreads) {
        for (DataRow r : data) {
            addItem(newThreads, r.newThreads, r.timestamp);
        }
    }

    public void fillVaadinPostsContainer(IndexedContainer vaadinPosts) {
        for (DataRow r : data) {
            addItem(vaadinPosts, r.vaadinPosts, r.timestamp);
        }
    }

    public void fillVaadinThreadsContainer(IndexedContainer vaadinThreads) {
        for (DataRow r : data) {
            addItem(vaadinThreads, r.vaadinThreads, r.timestamp);
        }
    }

    @SuppressWarnings("unchecked")
    private void addItem(IndexedContainer container, int value, Date timestamp) {
        Object key = container.addItem();
        container.getItem(key).getItemProperty(Timeline.PropertyId.VALUE)
                .setValue(new Float(value));
        container.getItem(key).getItemProperty(Timeline.PropertyId.TIMESTAMP)
                .setValue(timestamp);
    }
}
