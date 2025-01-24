/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.examples.librarydata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

/**
 * This class consumes the HelMet REST service, which provides data on books in
 * libraries in the Helsinki area.
 */
public class Helmet {

    public static Result search(String query) throws IOException {
        return search(query, 0);
    }

    public static Result search(String query, int page) throws IOException {
        URL url = null;
        if (page > 0) {
            url = new URL("http://data.kirjastot.fi/search/author.json?query="
                    + query + "&page=" + page);
        } else {
            url = new URL("http://data.kirjastot.fi/search/author.json?query="
                    + query);
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(
                url.openStream()));

        ObjectMapper mapper = new ObjectMapper()
                .setPropertyNamingStrategy(
                        PropertyNamingStrategies.SNAKE_CASE)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        false);

        return mapper.readValue(in, Result.class);
    }

    public static class Result {
        public int currentPage;
        public int perPage;
        public int totalEntries;
        public List<Record> records;

        public int getPageCount() {
            return totalEntries / perPage;
        }

        public boolean hasMorePages() {
            return currentPage < getPageCount();
        }
    }

    public static class Record {
        public String author;
        public List<AuthorDetails> authorDetails;
        public List<String> contents;
        public List<String> description;
        public List<String> extent;
        public String isbn;
        public String libraryId;
        public String libraryUrl;
        public String title;
        public String type;

        public int getNumPages() {
            if (extent.isEmpty() || !type.equalsIgnoreCase("book")) {
                return -1;
            }
            try {
                Scanner scanner = new Scanner(extent.get(0));
                return scanner.nextInt();
            } catch (Exception e) {
                return -1;
            }
        }

        @Override
        public String toString() {
            return title;
        }
    }

    public static class AuthorDetails {
        public String name;
        public String role;
    }
}
