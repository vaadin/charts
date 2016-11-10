# Vaadin Charts

Vaadin Charts is a UI component add-on for Vaadin Framework 7+ which provides means to create multiple different types of charts in Vaadin applications.

## License & Author

This Add-on is distributed under [Commercial Vaadin Add-on License version 3](http://vaadin.com/license/cval-3) (CVALv3). For license terms, see LICENSE.txt.

Vaadin Charts is written by Vaadin Ltd.


## Setting up for development:

Clone the project in GitHub (or fork it if you plan on contributing) and required submodules

```
git clone git@github.com:vaadin/charts.git
git submodule init
git submodule update
```

To build and install the project into the local repository run 

```mvn install -DskipITs```

in the root directory. `-DskipITs` will skip the integration tests, which require a TestBench license. If you want to run all tests as part of the build, run

```mvn install```

To run the UI used for development, run

```mvn jetty:run``` in the `demo` directory
and navigate to http://localhost:8080

To run with Super Dev Mode, run 

```mvn vaadin:run-codeserver``` in the `demo` directory.

You need to have the UI jetty running as well, then navigate to http://localhost:8080/?superdevmode

### Project modules

* The `demo` module contains a web app that shows most of the integration tests in a simple demo app.
* The `chart-export-demo` module demonstrates how charts can be converted to SVG and via it used in e.g. PDF documents.
* The `chart-plugin-demo` shows how the underlying library version can be changed and how plugins can be used with Vaadin Charts.
