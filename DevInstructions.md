Basic instructions for developers when using Eclipse
=====
Instructions written for Luna/Mars, but should work similarly for Kepler. Earlier versions don't have Maven built-in, so you'll have to install that separately if you don't want to upgrade your Eclipse.

Clone repository
-----
command line: <code>git clone --recursive https://github.com/vaadin/charts.git</code>

EGit:

1. Open Git repository perspective
    *-* *Window* > *Open Perspective* > *Other...* > *Git* (Windows)
    *-* *Eclipse* > *Open Perspective* > *Other...* > *Git* (Mac)
1. Click *Clone a Git Repository and add the clone to this view*
1. Check 'clone submodules' on the third view of the wizard.

Import project
-----
*File* > *Import...* > *Maven* > *Existing Maven Projects*

You'll need the main project if you want to be able to export the demo war. Vaadin 7 version of the add-on is named by default *vaadin-charts* (addon/pom.xml), Vaadin 6 isn't supported in newer Charts versions. Regression tests are located in *vaadin-charts-integration-tests* (vaadin-charts-integration-tests/pom.xml). In most cases you shouldn't need modules *book-examples*, *vaadin-charts-demo*, and *vaadin-charts-directorypackage*, although if you want to run any Maven goals on the parent pom.xml without them you'll need to comment them out from the module listing first. Whether you'll import the parent module or not is a matter of preference, it's not necessary for most use cases.


Maven
=====

Sync the Eclipse project settings with the pom.xml:

* right-click project > *Maven* > *Update Project*

If there are problems in the pom.xml, right-click the problematic place > quick fix > experimental


Running Maven goals
-----
command line: <code>mvn <i>goal</i></code>

Eclipse: right-click project > *Run As* > *Maven build...* > *goal*

Note: running all the tests might take quite a while on Windows laptops at least.

#skipping tests:

Eclipse: check the *skip tests* when you run any goal

command line: <code>mvn <i>goal</i> -DskipTests</code>


#download dependencies:
goal: <code>install</code>

#compile widgetset:
goal for Vaadin 7: <code>vaadin:compile</code><br>
goal for Vaadin 6: <code>gwt:compile</code>

#run in jetty
goal: <code>jetty:run</code>

#superdevmode:
goal: <code>vaadin:run-codeserver</code>

#clean:
goal: <code>clean</code>

#run all tests:
goal: <code>test</code>
OR
goal: <code>verify</code>

#run single test
1. run goal <code>jetty:run</code> for project *vaadin-charts-integration-tests*
1. find a test class within package com/vaadin/addon/charts/testbenchtests
3. right-click -> run as JUnit

If you are within Vaadin network this will execute the test on a test hub. Running the tests through VPN access may require you to modify <code>AbstractParallelTest.findAutoHostname()</code> to return the IP address that was assigned for the VPN connection.

Note: To run the test locally add a @RunLocally annotation to the test class. Default browser is Firefox, but it can be overridden e.g. by using @RunLocally(Browser.PHANTOMJS) annotation instead.

#create demo war:
goal: <code>package</code> (for main project, should appear to /demo/target - for subprojects creates jars)

#more about Maven and Vaadin:
https://vaadin.com/wiki/-/wiki/Main/Creating+a+Maven+project

Goals can also be combined, e.g. <code>clean package install</code>

Note: if you run goal <code>clean package install</code> with the *skip tests* checked,
you must run the <code>vaadin:compile</code> goal before you can run any tests again.

If you try to run some goal and get errors that are related to javadoc, check the stacktrace for what version of maven-javadoc-plugin you are using.
If your version is 2.10, add ``<version>2.9.1</version>`` after ``<artifactId>maven-javadoc-plugin</artifactId>`` in pom.xml.
The problem should also be fixed in the next version after 2.10 once it's released.


Running test UIs
=====
1. build widgetset, either by running goal <code>clean install</code> for the parent project or <code>clean vaadin:compile install</code> for project *vaadin-charts-integration-tests*
1. run goal <code>jetty:run</code> for project *vaadin-charts-integration-tests*
1. go to http://localhost:9998/ for a list of all tests (click opens the selected test to another tab) or directly to http://localhost:9998/other/AreaRange or http://localhost:9998/area/AreaSpline etc.
1. if you want to use superdevmode, make sure your widgetset is up to date, run goal <code>vaadin:run-codeserver</code> for project *vaadin-charts-integration-tests* and then add ?superdevmode to the url
1. you can restart regular server and superdevmode server independently of each other, but they both must be running at the same time for superdevmode to work


Contributing
=====
https://vaadin.com/wiki/-/wiki/Main/Contributing+Code

Before making any changes
-----
Ensure your Eclipse is set up according to http://dev.vaadin.com/wiki/CodingConventions (except Java6 for Vaadin 7 projects) and https://github.com/vaadin/vaadin#set-up-extra-workspace-preferences

Commiting changes
-----
1. make sure your project is up to date:
    *-* right-click project > *Team* > *Pull*
1. create a new branch for your change:
    *-* right-click project > *Team* > *Switch To* > *New Branch...*
    *-* give the branch some suitable name and make sure it's set to checkout the new branch
1. right-click project > *Team* > *Commit...*
1. make sure you have *Compute Change-Id For Gerrit Code Review* selected
    *-* you may automate this by adding <code>gerrit.createchangeid = true</code> to your repository configurations
1. make sure you included the ticket number to the end of the first line of comment ``(#<number>)``
1. make sure there is an empty line before the rest of the comment
1. if you need to make further changes, click *Amend Previous Commit* instead of making a new commit

Pushing changes
-----
1. make sure you are in the new branch
1. make sure Gerrit has your public key (Contributing Code link above)

command line:

1. navigate to cloned repository
1. <code>git push ssh://<i>yourusername</i>@dev.vaadin.com:29418/charts.git head:refs/for/master </code>

EGit:

1. make sure eclipse has your private key:
    *-* *Window* > *Preferences* > search 'ssh' > *SSH2* (Windows)
    *-* *Eclipse* > *Preferences* > search 'ssh' > *SSH2* (Mac)
    *-* check the path and add the key to the list if necessary
1. right-click project > *Team* > *Remote* > *Configure Push To Upstream...*
    *-* Push URIs: <code> ssh://<i>yourusername</i>@dev.vaadin.com:29418/charts.git </code>
    *-* Ref mappings: <code> HEAD:refs/for/master </code>
1. right-click project > *Team* > *Push to Upstream*