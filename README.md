# Visual AI BDD Framework

**Visual Test Automation using Selenium WebDriver, Applitools Visual AI, and Behavior-Driven Development (BDD).**

This project provides a reusable framework for running visual-validation scenarios against a website supplied at runtime. It combines browser automation, Gherkin feature scenarios, centralized configuration, and visual comparison through Applitools.

## Table of Contents

- [Project Overview](#project-overview)
- [Key Features](#key-features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation and Setup](#installation-and-setup)
- [Configuration](#configuration)
- [Run the Framework](#run-the-framework)
- [Test Filtering](#test-filtering)
- [Test Reports](#test-reports)
- [Troubleshooting](#troubleshooting)
- [Detailed Setup Guide](#detailed-setup-guide)
- [Author](#author)

## Project Overview

Visual appearance is an important part of web application quality. This framework combines Selenium WebDriver with Applitools Visual AI to compare website visuals, while Cucumber BDD keeps scenarios readable and maintainable.

The website to validate can be supplied through the Maven `-Dwebsite` property. Browser behavior, viewport dimensions, visual match level, and timeouts are configured centrally.

## Key Features

- **Visual validation:** Uses Applitools Eyes for visual comparison.
- **BDD scenarios:** Uses Cucumber and Gherkin feature files to describe test behavior.
- **Browser automation:** Uses Selenium WebDriver.
- **Runtime website selection:** Accepts the target website through a Maven property.
- **Centralized settings:** Keeps browser, viewport, visual, timeout, and report-path settings in `framework.properties`.
- **Reusable components:** Separates configuration, driver setup, step definitions, utilities, page loading, and visual-test management.
- **Reporting support:** Includes Cucumber and Allure reporting configuration.

## Technology Stack

| Technology | Role in the project |
|---|---|
| Java | Main programming language |
| Maven | Dependency management and test execution |
| Selenium WebDriver | Browser automation |
| Cucumber BDD | Gherkin-based test scenarios |
| JUnit | Test runner integration |
| Applitools Eyes | Visual AI comparison |
| Allure | Test-reporting support |

## Project Structure

```text
visual-ai-bdd-framework/
├── .gitignore
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/visualai/
    │   │   ├── config/
    │   │   │   └── FrameworkConfig.java
    │   │   ├── driver/
    │   │   │   └── DriverFactory.java
    │   │   ├── stepdefinitions/
    │   │   │   └── VisualValidationSteps.java
    │   │   ├── utils/
    │   │   │   ├── ReportingUtils.java
    │   │   │   ├── TestContext.java
    │   │   │   └── WebsiteInputResolver.java
    │   │   └── visual/
    │   │       ├── PageLoader.java
    │   │       ├── VisualTestManager.java
    │   │       └── VisualTestResult.java
    │   └── resources/
    │       ├── framework.properties
    │       └── logback.xml
    └── test/
        ├── java/com/visualai/runner/
        │   └── TestRunner.java
        └── resources/
            ├── allure.properties
            └── features/
                └── visual_validation.feature
```

## Prerequisites

Install the following before running the framework:

- **Java JDK 11 or higher**, subject to the version configured in `pom.xml`.
- **Apache Maven 3.x**.
- **Google Chrome**, for the configured browser.
- **IntelliJ IDEA Community Edition** or another Java IDE.
- An **Applitools account and API key** for visual comparisons.

Confirm Java and Maven are available in your terminal:

```bash
java -version
mvn -version
```

Downloads:

- [Java JDK — Eclipse Temurin](https://adoptium.net/)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [Google Chrome](https://www.google.com/chrome/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

## Installation and Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/sathishkumar11sk/visual-ai-bdd-framework.git
   cd visual-ai-bdd-framework
   ```

2. Open the project directory in IntelliJ IDEA.
3. Import or reload the Maven project and allow Maven to download dependencies.
4. Ensure the Project SDK is set to a compatible JDK.
5. Configure the Applitools API key locally before running visual tests.

Run all commands from the project root—the directory containing `pom.xml`.

## Configuration

The main configuration file is:

`src/main/resources/framework.properties`

Example configuration:

```properties
applitools.api.key=YOUR_APPLITOOLS_API_KEY
applitools.app.name=VisualAI-BDD-Framework
applitools.match.level=LAYOUT

browser=chrome
browser.headless=false

viewport.width=1366
viewport.height=768

visual.full.page=true
page.load.timeout=30

report.cucumber.output=target/cucumber-reports
report.allure.results=target/allure-results
```

Replace `YOUR_APPLITOOLS_API_KEY` with your own key through a secure local configuration method.

**Security:** Never commit an actual API key, token, password, or other credential to a public repository. The example above uses a placeholder. Use an environment variable only if the current implementation supports it; verify the expected property or environment-variable name in the code.

### Configuration reference

| Property | Example value | Purpose |
|---|---|---|
| `applitools.app.name` | `VisualAI-BDD-Framework` | Name used for visual test sessions |
| `applitools.match.level` | `LAYOUT` | Visual comparison mode |
| `browser` | `chrome` | Browser selection |
| `browser.headless` | `false` | Whether the browser runs visibly |
| `viewport.width` | `1366` | Viewport width |
| `viewport.height` | `768` | Viewport height |
| `visual.full.page` | `true` | Full-page visual capture setting |
| `page.load.timeout` | `30` | Page-load timeout in seconds |
| `report.cucumber.output` | `target/cucumber-reports` | Cucumber report output directory |
| `report.allure.results` | `target/allure-results` | Allure results directory |

The setup guide lists `LAYOUT`, `STRICT`, and `EXACT` as match-level examples. Use only values supported by the project's current Applitools integration.

## Run the Framework

Run the tests against a website by passing the URL with `-Dwebsite`:

```bash
mvn clean test -Dwebsite=https://www.google.com
```

Replace the example URL with the website you want to validate and are authorized to test. For example:

```bash
mvn clean test -Dwebsite=https://www.wikipedia.org
```

The first run may take longer while Maven downloads dependencies. An internet connection is also needed for services and websites used by the test.

### Run in headless mode

Set the following property in `src/main/resources/framework.properties`:

```properties
browser.headless=true
```

Then run the Maven command again. Set it back to `false` when you want to see the browser window.

## Test Filtering

The setup guide provides examples for running selected Cucumber tags:

```bash
mvn clean test -Dwebsite=https://www.google.com -Dcucumber.filter.tags="@smoke"
```

```bash
mvn clean test -Dwebsite=https://www.google.com -Dcucumber.filter.tags="@region"
```

Use these commands only when the corresponding tags exist in `src/test/resources/features/visual_validation.feature` and are supported by the current Cucumber runner. Check the feature file for the actual tags before relying on a filtered run.

## Test Reports

Report output is configured under `target/`, which is excluded from version control.

### Cucumber report

The setup guide documents the Cucumber HTML report under:

```text
target/cucumber-reports/
```

The exact report filename depends on the reporting configuration. Open the generated HTML report in a browser after the test run.

### Allure report

Allure results are configured under:

```text
target/allure-results/
```

To view them, use an installed Allure command-line tool or the Maven reporting integration configured for the project.

The setup guide mentions a bundled Allure executable under `.allure/`. That directory is not included in this repository, so the bundled command will not work from a fresh clone unless Allure is installed or supplied separately.

### Applitools dashboard

Open [Applitools Eyes](https://eyes.applitools.com/) and sign in to the account associated with your configured API key to inspect visual results and comparisons.

## Troubleshooting

| Issue | What to check |
|---|---|
| `java` is not recognized | Install a JDK, configure `PATH` and/or `JAVA_HOME`, and restart the terminal. |
| `mvn` is not recognized | Install Maven and add its `bin` directory to `PATH`. |
| Browser or driver fails to start | Confirm Chrome is installed and review the browser and driver configuration. |
| Website URL is missing | Include `-Dwebsite=https://your-site.example` in the Maven command. |
| Tests fail immediately | Review the test output, website availability, network access, and browser logs. Some websites restrict automated browsers. |
| Maven downloads fail | Check internet connectivity, proxy settings, and firewall restrictions. |
| Applitools authentication fails | Check the locally configured API key and account access. Do not publish the key. |
| Report cannot be opened | Confirm the test run generated results and check the report output path. |

## Detailed Setup Guide

For the full step-by-step installation and execution instructions, add the cleaned PDF guide to the repository at:

`docs/VisualAI_BDD_Setup_Guide.pdf`

Then this link will work:

[Open the Visual AI BDD Setup & Execution Guide](docs/VisualAI_BDD_Setup_Guide.pdf)

**Before adding the PDF:** remove any real API key, token, password, or other sensitive information from it. Do not publish credentials in a public repository.

## Author

**Sathishkumar S**

GitHub: [@sathishkumar11sk](https://github.com/sathishkumar11sk)

---

*Visual Test Automation Framework using Visual AI and Behavior-Driven Development.*
