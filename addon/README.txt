Vaadin Charts ${project.version}
===========================

This is a beta level build of the Vaadin Charts add-on. Vaadin Charts is based on
popular Highcharts ${highcharts.version} JavaScript library. Vaadin Charts also 
includes the previously separate Vaadin Timeline add-on, which uses the HTML5 
canvas for drawing.

This version is built against Vaadin ${vaadin.version.maven}.

Installation
============
Vaadin Charts is a standard Vaadin Add-on package. To use it, add the 
vaadin-charts-${project.version}.jar file in this package (and 
required dependencies) to your projects classpath 
or use the following Maven module:

<dependency>
	<groupId>com.vaadin.addon</groupId>
	<artifactId>vaadin-charts</artifactId>
	<version>${project.version}</version>
</dependency>

Maven users also need to have Vaadin Add-ons repository defined or install add-on 
jar the their local repository:

<repository>
	<id>vaadin-addons</id>
	<url>http://maven.vaadin.com/vaadin-addons</url>
</repository>

The add-on also depends on following Apache 2 licensed libraries: 
 * gson
 * commons-io 

These dependencies are defined in the pom.xml and will be automatically 
resolved for e.g. Maven and IVY users. They are also included in this zip 
package in the "lib" directory.

Note, that the package also has client side extensions, so make sure that you
compile your widgetset after installation!

Licensing
=========

Vaadin Charts is a commercial product. After 30 days of evaluation use,
you must either acquire a license or stop using it. More information about
Commercial Vaadin Add-on License is available in LICENSE file or at 
https://vaadin.com/license/cval-2.0. 

You may obtain a valid license by subscribing to Vaadin Pro Account at
https://vaadin.com/pro or by purchasing a perpetual license at
https://vaadin.com/directory.

A valid license key is your perpetual license key purchased from Vaadin
Directory or alternatively the email address you use to login to an active
Vaadin Pro Account.   

Register your copy of Vaadin Charts by creating a file named
.vaadin.charts.developer.license containing the license key in your home 
directory or by setting the vaadin.charts.developer.license=license_key
system property to disable this message.

Third Party Licensing
=====================

This Add-on component and related documentation is a derivative work of 
Highcharts JS product ("Highchart JS").

Highsoft Solutions AS retains all rights, title and interest in and to the
Highchart JS (resource files: highcharts.js, highcharts-more.js and
highcharts.src.js)

Using Vaadin Charts Add-on does not require a separate Highcharts JS license
purchase. 

Please note that; 
- A valid Highcharts JS license does not entitle you to use Vaadin Charts Add-on under the terms of CVALv2.
- The Vaadin Charts Add-on (CVALv2) license does not entitle you to use the 
Highcharts JS product in other ways than through the Vaadin Charts Add-on API.

Vaadin Charts also requires Gson library licensed under liberal Apache 2 license.

Links
=====

Homepage:
https://vaadin.com/add-ons/charts

Highcharts JS Homepage: 
http://www.highcharts.com
 
Code and usage examples:
http://demo.vaadin.com/charts-demo/

Issue tracker:
http://dev.vaadin.com/

Documentation:
http://vaadin.com/download/book-of-vaadin/vaadin-charts/pdf/book-of-vaadin.pdf

SCM (SVN):
http://dev.vaadin.com/svn/addons/vaadin-charts

