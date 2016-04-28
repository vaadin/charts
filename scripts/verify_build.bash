#!/bin/bash

#DESCRIPTION
#---------------------------------------------------------------------
# 1. This script unzips the TC build archive for charts.
# 2. Extracts jars and poms, copies them into local maven and run mvn install:install-file to register vaadin-model.jar and vaadin-charts.jar.
# 3. Change version of charts used in charts/demo/pom.xml and charts/examples/pom.xml
# 4. Build demo and examples submodules.
# 5. Run jetty-run to start a demo.
#---------------------------------------------------------------------

#REQUIREMENTS
----------------------------------------------------------------------
# 1. maven3 installed, to check mvn --version
# 2. unzip installed, to check --version
# 3. sed installed, to check --version
----------------------------------------------------------------------

#HOW TO RUN
---------------------------------------------------------------------
#Run with following parameters:
# $1 - archive name
# $2 - version name
# $3 - charts repo
# For example ./verify.bash TCBuild.zip 3.1.0-beta1 ~/sources/charts
# Then got to localhost:8080 to see that demo is working.
---------------------------------------------------------------------

productVersion=$2
repo=$3
#PARENT_PATH=/~/.m2/repository/com/vaadin/addon/vaadin-charts-parent/$productVersion
#EXTRACT_FOLDER="./extr"
EXAMPLES_POM_PATH=$3/examples/pom.xml
DEMO_POM_PATH=$3/demo/pom.xml
#
echo $productVersion
unzip $1 -d ./$EXTRACT_FOLDER
echo "Copying resources for charts model"
#
unzip ./$EXTRACT_FOLDER/addon/lib/vaadin-charts-model-"$productVersion".jar -d ./tmp
cp ./$EXTRACT_FOLDER/addon/lib/vaadin-charts-model-"$productVersion".jar ./tmp
cp ./tmp/META-INF/maven/com.vaadin.addon/vaadin-charts-model/pom.xml ./tmp
#
echo "Install charts model to maven"
mvn install:install-file -Dfile=./tmp/vaadin-charts-model-"$productVersion".jar -DpomFile=./tmp/pom.xml
rm -rf ./tmp
#
echo "Installing vaadin-charts.jar"
unzip ./$EXTRACT_FOLDER/addon/vaadin-charts-"$productVersion".jar -d ./tmp
cp ./$EXTRACT_FOLDER/addon/vaadin-charts-"$productVersion".jar ./tmp
cp ./tmp/META-INF/maven/com.vaadin.addon/vaadin-charts/pom.xml ./tmp
#
#
mvn install:install-file -Dfile=./tmp/vaadin-charts-"$productVersion".jar -DpomFile=./tmp/pom.xml
rm -rf ./tmp
rm -rf ./$EXTRACT_FOLDER
echo $EXAMPLES_POM_PATH

#change project.version variable in pom to tested version for examples module
sed -i "s/\${project.version}/"$productVersion"/" $EXAMPLES_POM_PATH

#change the first project.version variable in pom to tested version for examples module
sed -i -e  "0,/\${project.version}/ s/\${project.version}/"$productVersion"/" $DEMO_POM_PATH
#build everything
mvn -f $3 clean install -pl addon -DskipTests=true
mvn -f $3 clean install -pl examples -DskipTests=true
mvn -f $3 clean install -pl demo -DskipTests=true

mvn -f $3/demo jetty:run
