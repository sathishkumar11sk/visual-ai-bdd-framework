# Visual AI BDD Framework

A configuration-driven visual test automation framework built using **Java, Selenium WebDriver, Cucumber BDD, and Applitools Visual AI**. The framework is designed to validate the visual appearance of websites using reusable automation components and behavior-driven scenarios.

## 🚀 Project Overview

The Visual AI BDD Framework helps automate visual validation of web pages. It uses Behavior-Driven Development (BDD) to define test scenarios in readable Gherkin syntax and Applitools Visual AI to compare visual checkpoints.

The framework is designed to support different website URLs through runtime input and centralized configuration.

## ✨ Key Features

- **Visual Testing:** Validate website appearance using Applitools Visual AI.
- **BDD with Cucumber:** Define test scenarios using readable Gherkin feature files.
- **Selenium WebDriver:** Automate browser interactions and page loading.
- **Runtime Website Input:** Resolve the website URL dynamically rather than relying on a fixed target.
- **Centralized Configuration:** Configure browser settings, viewport dimensions, visual match level, and timeouts.
- **Reusable Architecture:** Separate configuration, driver management, step definitions, page loading, and visual test management.
- **Test Reporting:** Integrate reporting support through Allure and Cucumber configuration.

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Maven | Dependency and build management |
| Selenium WebDriver | Browser automation |
| Cucumber BDD | Gherkin-based test scenarios |
| Applitools Eyes | Visual AI validation |
| JUnit | Test execution |
| Allure | Test reporting support |

## 📁 Project Structure

```text
visual-ai-bdd-framework/
├── pom.xml
├── .gitignore
└── src/
    ├── main/
    │   ├── java/com/visualai/
    │   │   ├── config/
    │   │   ├── driver/
    │   │   ├── stepdefinitions/
    │   │   ├── utils/
    │   │   └── visual/
    │   └── resources/
    │       ├── framework.properties
    │       └── logback.xml
    └── test/
        ├── java/com/visualai/runner/
        └── resources/
            ├── allure.properties
            └── features/
                └── visual_validation.feature
```

## ⚙️ Prerequisites

Install the following before running the framework:

- Java JDK compatible with the Maven compiler configuration
- Apache Maven
- Google Chrome or another browser supported by your driver configuration
- An Applitools account and API key

## 🔧 Configuration

Update `src/main/resources/framework.properties` with your required settings.

Example:

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
```

**Security:** Keep API keys private. Do not commit real credentials to a public repository. Use environment variables or another secure configuration mechanism for secrets.

## ▶️ Running the Project

Clone the repository:

```bash
git clone https://github.com/sathishkumar11sk/visual-ai-bdd-framework.git
cd visual-ai-bdd-framework
```

Build the project:

```bash
mvn clean install
```

Run the tests:

```bash
mvn test
```

The exact runtime URL-input and Maven property options depend on the implementation and configuration in the project.

## 📊 Reporting

The project includes Allure and Cucumber reporting configuration. Report generation and viewing commands depend on the configured Maven plugins and test-runner settings.

## 🎯 Project Objective

The objective is to build a reusable, configuration-driven visual test automation framework that combines Selenium browser automation, Applitools Visual AI, and Cucumber BDD to support structured visual validation of websites.

## 👨‍💻 Author

**Sathishkumar S**

GitHub: [sathishkumar11sk](https://github.com/sathishkumar11sk)

---

*Visual Test Automation Framework using Visual AI and Behavior-Driven Development.*
