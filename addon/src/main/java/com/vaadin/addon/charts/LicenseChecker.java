package com.vaadin.addon.charts;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 2.0
 * (CVALv2).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv2 along with this program.
 * If not, see <http://vaadin.com/license/cval-2.0>.
 * #L%
 */

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 * This class nags about license information whenever Charts usage is started
 * unless the vaadin.charts.developer.license system property is set or the
 * ~/.vaadin.charts.developer.license file exists.
 */
class LicenseChecker {

    /**
     * Nags about unlicensed software unless a valid key is found.
     */
    static void nag() {
        String userAccount = System
                .getProperty("vaadin.charts.developer.license");
        if (userAccount == null) {
            String homedir = System.getProperty("user.home");
            File file = new File(homedir + "/.vaadin.charts.developer.license");
            if (!file.exists()) {
                printCVALInformationAndHowToGetRidOfThisInformation();
            }
        }
    }

    private static void printCVALInformationAndHowToGetRidOfThisInformation() {
        try {
            List<String> readLines = IOUtils.readLines(
                    LicenseChecker.class.getResourceAsStream("licensenag.txt"),
                    "UTF-8");
            for (String line : readLines) {
                System.err.println(line);
            }
        } catch (IOException e) {
            System.err
                    .println("VAADIN CHARTS IS COMMERCIAL SOFTWARE, SEE https://vaadin.com/license/cval-2.0");
        }
    }
}
