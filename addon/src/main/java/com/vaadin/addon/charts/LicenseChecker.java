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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

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
            boolean licenseFileFound = false;
            String homedir = System.getProperty("user.home");
            File file = new File(homedir + "/.vaadin.charts.developer.license");
            licenseFileFound = file.exists();
            if(!licenseFileFound) {
                // Still check if license file is added to war files class path
                URL resource = LicenseChecker.class.getResource("/vaadin.charts.developer.license");
                if(resource == null) {
                    resource = LicenseChecker.class.getResource("/.vaadin.charts.developer.license");
                }
                licenseFileFound = resource != null;
            }
            if (!licenseFileFound) {
                printCVALInformationAndHowToGetRidOfThisInformation();
            }
        }
    }

    private static void printCVALInformationAndHowToGetRidOfThisInformation() {
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(LicenseChecker.class
                            .getResourceAsStream("licensenag.txt"), "UTF-8"));
            String l;
            while((l = bufferedReader.readLine()) != null) {
                System.err.println(l);
            }
        } catch (IOException e) {
            System.err
                    .println("VAADIN CHARTS IS COMMERCIAL SOFTWARE, SEE https://vaadin.com/license/cval-2.0");
        }
    }
}
