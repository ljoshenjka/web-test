# Selenium+Java+Cucumber framework for web application test automation #

This framework purpose is the implementation and execution of acceptance tests for web application in various browsers. It consists of predefined dependencies and API's that will help to describe the expected behaviour of web app.

### Core dependencies ###

* Selenium - test automation framework for web applications
* Cucumber - provides the natural Gherkin language used to describe application features, a basic API for binding that natural language to step definitions written in Java
* PageObject/PageElements - design patterns which helps you to encapsulate the expected structure and mechanics of your application's UI.
* JUnit - for asserting expectations of scenario outcomes
* Apache Maven - build automation tool

## Getting Started ##

### Architecture ###

Framework is divided in such subsections:

#### Main ####
* src/main/java/base
* src/main/java/constants
* src/main/java/elements
* src/main/java/helpers
* src/main/resources

**base** - core of the testing framework like webdriver setup and special wrapped webdriver methods

**constants** - config fields

**elements** - page elements like buttons, labels, text fields etc.

**helpers** - helper functions for helping writing test cases and page objects

**resources** - run configurations stored here with environment settings 

#### Test ####
* src/test/java/hooks
* src/test/java/pages
* src/test/java/runners
* src/test/java/steps
* src/test/java/testHelpers
* src/test/resources/Features

**hooks** - "before" and "after" annotations with different setups either for driver or test cases

**pages** - web application page objects and components

**runners** - runners needed for execution

**steps** - cucumber step implementations mapped with gherkin

**testHelpers** - test helpers, for ex. some hardcoded string storage

**Features** - feature files written in Gherkin language

### Writing Test Cases ###

Acceptance TC are written in Gherkin language inside feature file which are stored in "Features" directory and then mapped to a step implementation in Java inside step classes.

### Running Test Cases ###

##### Precondition ####
Install JDK and Chrome browser

##### Execution ####
1. Before executing tests you need to modify config file (ex. default config prod.properties) with your system name (mac or win)
2. Check for the correct data in your config file. You can create your own config file and use it for execution with -Denv <config_name> (ex. -Denv dev)
3. Open project dir in terminal and run "mvn clean verify". Specify tags to run in TestRunner file or through terminal with -Dcucumber.options="--tags @e2e" - "mvn clean verify -Dcucumber.options="--tags @smoke""
3.1. Also you can execute test from your IDE with TestRunner class as it is JUnit executable class.
4. Results are stored in html format in "output/report.html" directory and cucumber json report is in "target/reports/cucumber.json"
