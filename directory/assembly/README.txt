Vaadin Charts ${project.version}
===========================

Installation
============

The add-on works like normal Vaadin Add-ons. Note, that the package also has
client side extensions, so make sure that you compile your widgetset after
installation!

!!!!! NOTE !!!!!
Widget set compilation will fail unless you have a license for Vaadin
Charts. See https://vaadin.com/pricing for details.
Please find instructions for how to install the license at
https://vaadin.com/docs/v8/framework/addons

Maven
-----

Dependency snippet for Maven users:

<dependency>
	<groupId>com.vaadin</groupId>
	<artifactId>vaadin-charts</artifactId>
	<version>${project.version}</version>
</dependency>

The add-on is available in Vaadin Add-Ons repository:

<repository>
	<id>vaadin-addons</id>
	<url>https://tools.vaadin.com/nexus/content/repositories/vaadin-addons</url>
</repository>

Ivy
---

IVY dependency snippet:

<dependency org="com.vaadin" name="vaadin-charts" rev="${project.version}" conf="default->default" />

Using plain Jar
---------------

If you wan't to use the add-on jar directly, add it to your classpath. The add-on
also depends on following Apache 2 licensed libraries:
 * Jackson

Unless you are already using them in your project add them also. They are also
included in this zip package in the "lib" directory.

Licensing
=========

Vaadin Charts is a commercial product. You must either acquire a license or
stop using it. More information about Vaadin Commercial License and Service 
Terms is available in LICENSE file or at
https://vaadin.com/commercial-license-and-service-terms.

You may obtain a valid license by subscribing to Vaadin Pro.
See https://vaadin.com/pricing for details.

Once you have the subscription, the license key can be found in
https://vaadin.com/myaccount/licenses#classic

Register your copy of Vaadin Charts by creating a file named
.vaadin.charts.developer.license containing the license key in your home
directory or by setting the vaadin.charts.developer.license=license_key
system property to disable license warning message.

Third Party Licensing
=====================

This Add-on component and related documentation is a derivative work of
Highstock JS product ("Highstock JS").

Highsoft Solutions AS retains all rights, title and interest in and to the
Highstock JS (resource files: highstock.js, highcharts-more.js, highcharts-3d.js,
standalone-framework.js, no-data-to-display.js, drilldown.js, exporting.js,
funnel.js, heatmap.js, solid-gauge.js and treemap.js)

Please note that:
- A valid Highstock JS license does not entitle you to use Vaadin Charts Add-on
under the terms of VCL-2.
- The Vaadin Charts Add-on (VCL-2) license does not entitle you to use the 
Highstock JS product in other ways than through the Vaadin Charts Add-on API.

Vaadin Charts also requires Jackson library licensed under liberal Apache 2 license.

Links
=====

Homepage:
https://vaadin.com/add-ons/charts

Code and usage examples:
http://demo.vaadin.com/charts/

Issue tracker:
https://github.com/vaadin/charts/issues

Documentation:
https://vaadin.com/docs/-/part/charts/charts-overview.html

SCM (Git):
https://github.com/vaadin/charts
