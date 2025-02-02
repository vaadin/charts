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
package com.vaadin.addon.charts.junittests;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import junit.framework.TestCase;

import org.junit.Test;

public class TestClassesSerializable extends TestCase {

    /**
     * JARs that will be scanned for classes to test, in addition to classpath
     * directories.
     */
    private static String JAR_PATTERN = ".*vaadin-charts.*\\.jar";

    private static String[] BASE_PACKAGES = { "com.vaadin.addon" };

    private static String[] EXCLUDED_PATTERNS = {
            "com\\.vaadin\\.addon\\.charts\\.client\\..*",
            "com\\.vaadin\\.addon\\.charts\\.declarative\\.ChartDesignAttributeHandler",
            "com\\.vaadin\\.addon\\.charts\\.declarative\\.ChartDesignFormatter",
            "com\\.vaadin\\.addon\\.charts\\.shared\\..*",
            "com\\.vaadin\\.addon\\.charts\\.demoandtestapp\\..*",
            "com\\.vaadin\\.addon\\.charts\\.model\\.serializers\\..*",
            "com\\.vaadin\\.addon\\.charts\\.model\\.MarkerSymbol",
            "com\\.vaadin\\.addon\\.charts\\.utils\\.ExampleUtil",
            "com\\.vaadin\\.addon\\.charts\\.model\\.Series",
            "com\\.vaadin\\.addon\\.charts\\.model\\.style\\.ThemeGradientColorSerializer",
            "com\\.vaadin\\.addon\\.charts\\.testbenchtests\\.ChartsBrowserFactory",
            "com\\.vaadin\\.addon\\.charts\\.util\\.SVGGenerator",
            "com\\.vaadin\\.addon\\.charts\\.util\\.Util",
            "com\\.vaadin\\.addon\\.charts\\.util\\.SVGGenerator\\$1", };

    /**
     * Tests that all the relevant classes and interfaces under
     * {@link #BASE_PACKAGES} implement Serializable.
     *
     * @throws Exception
     */
    public void testClassesSerializable() throws Exception {
        List<String> rawClasspathEntries = getRawClasspathEntries();

        List<String> classes = new ArrayList<String>();
        for (String location : rawClasspathEntries) {
            classes.addAll(findServerClasses(location));
        }

        ArrayList<Class<?>> nonSerializableClasses = new ArrayList<Class<?>>();
        for (String className : classes) {
            Class<?> cls = Class.forName(className);
            // skip annotations and synthetic classes
            if (cls.isAnnotation() || cls.isSynthetic()) {
                continue;
            }
            // Don't add classes that have a @Test annotation on any methods
            boolean testPresent = false;
            for (Method method : cls.getMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    testPresent = true;
                    break;
                }
            }
            if (testPresent) {
                continue;
            }

            // report non-serializable classes and interfaces
            if (!Serializable.class.isAssignableFrom(cls)) {
                if (cls.getSuperclass() == Object.class
                        && cls.getInterfaces().length == 1) {
                    // Single interface implementors
                    Class<?> iface = cls.getInterfaces()[0];

                    if (iface == Runnable.class) {
                        // Ignore Runnables used with access()
                        continue;
                    } else if (iface == Comparator.class) {
                        // Ignore inline comparators
                        continue;
                    }
                }
                nonSerializableClasses.add(cls);
            }
        }

        // useful failure message including all non-serializable classes and
        // interfaces
        if (!nonSerializableClasses.isEmpty()) {
            String nonSerializableString = "";
            Iterator<Class<?>> it = nonSerializableClasses.iterator();
            while (it.hasNext()) {
                Class c = it.next();
                nonSerializableString += ", " + c.getName();
                if (c.isAnonymousClass()) {
                    nonSerializableString += "(super: ";
                    nonSerializableString += c.getSuperclass().getName();
                    nonSerializableString += ", interfaces: ";
                    for (Class i : c.getInterfaces()) {
                        nonSerializableString += i.getName();
                        nonSerializableString += ",";
                    }
                    nonSerializableString += ")";
                }
            }
            fail("Serializable not implemented by the following classes and interfaces: "
                    + nonSerializableString);
        }
    }

    /**
     * Lists all class path entries by splitting the class path string.
     *
     * Adapted from ClassPathExplorer.getRawClasspathEntries(), but without
     * filtering.
     *
     * @return List of class path segment strings
     */
    //
    private final static List<String> getRawClasspathEntries() {
        // try to keep the order of the classpath
        List<String> locations = new ArrayList<String>();

        String pathSep = System.getProperty("path.separator");
        String classpath = System.getProperty("java.class.path");

        if (classpath.startsWith("\"")) {
            classpath = classpath.substring(1);
        }
        if (classpath.endsWith("\"")) {
            classpath = classpath.substring(0, classpath.length() - 1);
        }

        String[] split = classpath.split(pathSep);
        for (int i = 0; i < split.length; i++) {
            String classpathEntry = split[i];
            locations.add(classpathEntry);
        }

        return locations;
    }

    /**
     * Finds the server side classes/interfaces under a class path entry -
     * either a directory or a JAR that matches {@link #JAR_PATTERN}.
     *
     * Only classes under {@link #BASE_PACKAGES} are considered, and those
     * matching {@link #EXCLUDED_PATTERNS} are filtered out.
     *
     * @param classpathEntry
     * @return
     * @throws IOException
     */
    private List<String> findServerClasses(String classpathEntry)
            throws IOException {
        Collection<String> classes = new ArrayList<String>();

        File file = new File(classpathEntry);
        if (file.isDirectory()) {
            classes = findClassesInDirectory(null, file);
        } else if (file.getName().matches(JAR_PATTERN)) {
            classes = findClassesInJar(file);
        } else {
            System.out.println("Ignoring " + classpathEntry);
            return Collections.emptyList();
        }

        List<String> filteredClasses = new ArrayList<String>();
        for (String className : classes) {
            boolean ok = false;
            for (String basePackage : BASE_PACKAGES) {
                if (className.startsWith(basePackage + ".")) {
                    ok = true;
                    break;
                }
            }
            for (String excludedPrefix : EXCLUDED_PATTERNS) {
                if (className.matches(excludedPrefix)) {
                    ok = false;
                    break;
                }
            }

            // Don't add test classes
            if (className.contains("Test")) {
                ok = false;
            }

            if (ok) {
                filteredClasses.add(className);
            }
        }

        return filteredClasses;
    }

    /**
     * Lists class names (based on .class files) in a JAR file.
     *
     * @param file
     *            a valid JAR file
     * @return collection of fully qualified class names in the JAR
     * @throws IOException
     */
    private Collection<String> findClassesInJar(File file) throws IOException {
        Collection<String> classes = new ArrayList<String>();

        JarFile jar = new JarFile(file);
        Enumeration<JarEntry> e = jar.entries();
        while (e.hasMoreElements()) {
            JarEntry entry = e.nextElement();
            if (entry.getName().endsWith(".class")) {
                String nameWithoutExtension = entry.getName().replaceAll(
                        "\\.class", "");
                String className = nameWithoutExtension.replace('/', '.');
                classes.add(className);
            }
        }
        return classes;
    }

    /**
     * Lists class names (based on .class files) in a directory (a package path
     * root).
     *
     * @param parentPackage
     *            parent package name or null at root of hierarchy, used by
     *            recursion
     * @param parent
     *            File representing the directory to scan
     * @return collection of fully qualified class names in the directory
     */
    private final static Collection<String> findClassesInDirectory(
            String parentPackage, File parent) {
        if (parent.isHidden()
                || parent.getPath().contains(File.separator + ".")) {
            return Collections.emptyList();
        }

        if (parentPackage == null) {
            parentPackage = "";
        } else {
            parentPackage += ".";
        }

        Collection<String> classNames = new ArrayList<String>();

        // add all directories recursively
        File[] files = parent.listFiles();
        for (File child : files) {
            if (child.isDirectory()) {
                classNames.addAll(findClassesInDirectory(
                        parentPackage + child.getName(), child));
            } else if (child.getName().endsWith(".class")) {
                classNames.add(parentPackage.replace(File.separatorChar, '.')
                        + child.getName().replaceAll("\\.class", ""));
            }
        }

        return classNames;
    }

}
