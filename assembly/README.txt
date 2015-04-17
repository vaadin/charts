Vaadin Charts ${project.version}
===========================

Vaadin Charts is based on popular Highcharts ${highcharts.version} JavaScript
library. Vaadin Charts also  includes the previously separate Vaadin Timeline
add-on, which uses the HTML5 canvas for drawing.

Installation
============

The add-on works like normal Vaadin Add-ons. Note, that the package also has 
client side extensions, so make sure that you compile your widgetset after 
installation!

!!!!! NOTE !!!!!
Widget set compilation will fail unless you have a license for Vaadin
Charts. A free trial key can be obtained by clicking the big orange "Free
trial key" button on the right hand side of http://vaadin.com/addon/vaadin-charts
Please find instructions for how to install the license at
https://vaadin.com/directory/help/installing-cval-license

Maven
-----

Dependency snippet for Maven users:

<dependency>
	<groupId>com.vaadin.addon</groupId>
	<artifactId>vaadin-charts</artifactId>
	<version>${project.version}</version>
</dependency>

The add-on is available in Vaadin Add-Ons repository:

<repository>
	<id>vaadin-addons</id>
	<url>http://maven.vaadin.com/vaadin-addons</url>
</repository>

Ivy
---

IVY dependency snippet:

<dependency org="com.vaadin.addon" name="vaadin-charts" rev="${project.version}" conf="default->default" />

Using plain Jar
---------------

If you wan't to use the add-on jar directly, add it to your classpath. The add-on 
also depends on following Apache 2 licensed libraries: 
 * Jackson
 * commons-io 

Unless you are already using them in your project add them also. They are also 
included in this zip package in the "lib" directory.

Licensing
=========

Vaadin Charts is a commercial product. After 30 days of evaluation use,
you must either acquire a license or stop using it. More information about
Commercial Vaadin Add-on License is available in LICENSE file or at 
https://vaadin.com/license/cval-3. 

You may obtain a valid license by subscribing to Vaadin Pro Account at
https://vaadin.com/pro or by purchasing a perpetual license at
https://vaadin.com/directory.

A valid license key is your perpetual license key purchased from Vaadin
Directory or alternatively the email address you use to login to an active
Vaadin Pro Account.   

Register your copy of Vaadin Charts by creating a file named
.vaadin.charts.developer.license containing the license key in your home 
directory or by setting the vaadin.charts.developer.license=license_key
system property to disable license warning message.

Third Party Licensing
=====================

This Add-on component and related documentation is a derivative work of 
Highcharts JS product ("Highchart JS").

Highsoft Solutions AS retains all rights, title and interest in and to the
Highchart JS (resource files: highcharts.js, highcharts-more.js and
highcharts.src.js)

Using Vaadin Charts Add-on does not require a separate Highcharts JS license
purchase. 

Please note that: 
- A valid Highcharts JS license does not entitle you to use Vaadin Charts Add-on under the terms of CVALv3.
- The Vaadin Charts Add-on (CVALv3) license does not entitle you to use the 
Highcharts JS product in other ways than through the Vaadin Charts Add-on API.

Vaadin Charts also requires Jackson library licensed under liberal Apache 2 license.

Links
=====

Homepage:
https://vaadin.com/add-ons/charts

Highcharts JS Homepage: 
http://www.highcharts.com
 
Code and usage examples:
http://demo.vaadin.com/charts/

Issue tracker:
http://dev.vaadin.com/

Documentation:
https://vaadin.com/book/vaadin7/-/page/charts.html

SCM (Git):
https://github.com/vaadin/charts
