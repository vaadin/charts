Vaadin Chart Exporting demo
===========================

This example demonstrates how Vaadin Charts can by used also on the server 
side - naturally with the same clean Java API.

Before using this method, PhantomJS needs to be installed from phantomjs.org. 
Install it into your PATH or provide its location as a system property 
"phantom.exec" for the server.

If you have Maven installed this demo can then be run locally by issuing following 
command:

mvn jetty:run-war

or one liner including svn checkout:

svn co http://dev.vaadin.com/svn/addons/vaadin-charts/chart-export-demo/; cd chart-export-demo; mvn jetty:run-war

