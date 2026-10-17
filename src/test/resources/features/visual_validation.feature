@visual
Feature: Advanced Visual Defect Detection

  # ===============================
  # 1. FULL-PAGE VALIDATION
  # ===============================

  @visual @layout @smoke
  Scenario: Validate full-page layout consistency
    Given a website URL is provided dynamically
    When the website is opened
    Then the visual appearance should match the baseline
    And the visual test result should be passing

  @visual @strict
  Scenario: Strict pixel-level validation
    Given a website URL is provided dynamically
    When the website is opened
    Then the visual appearance should match the baseline
    And the visual test result should be passing

  @visual @ignorecolors
  Scenario: Validate layout ignoring color differences
    Given a website URL is provided dynamically
    When the website is opened
    Then the visual appearance should match the baseline
    And the visual test result should be passing

  @visual @exact
  Scenario: Pixel-perfect exact comparison
    Given a website URL is provided dynamically
    When the website is opened
    Then the visual appearance should match the baseline
    And the visual test result should be passing

  # ===============================
  # 2. REGION-BASED VALIDATION
  # ===============================

  @visual @region @layout
  Scenario: Validate header region only
    Given a website URL is provided dynamically
    When the website is opened
    Then the "header" region should match the baseline
    And the visual test result should be passing

  @visual @region @layout
  Scenario: Validate footer region only
    Given a website URL is provided dynamically
    When the website is opened
    Then the "footer" region should match the baseline
    And the visual test result should be passing

  @visual @region @strict
  Scenario: Validate navigation region with strict comparison
    Given a website URL is provided dynamically
    When the website is opened
    Then the "nav" region should match the baseline
    And the visual test result should be passing

  # ===============================
  # 3. RESPONSIVE TESTING
  # ===============================

  @visual @responsive
  Scenario: Validate visual appearance across all responsive breakpoints
    Given a website URL is provided dynamically
    When the website is opened
    Then the visual appearance should be validated across all viewports
    And the visual test result should be passing

  # ===============================
  # 4. NEGATIVE TESTING
  # ===============================

  @visual @negative @strict
  Scenario: Detect background color regression
    Given a website URL is provided dynamically
    When the website is opened
    And I intentionally modify the UI for negative testing
    Then the visual appearance should match the baseline
    And the visual test result should be passing

  @visual @negative @strict
  Scenario: Detect unexpected overlay on page
    Given a website URL is provided dynamically
    When the website is opened
    And an unexpected overlay appears on the page
    Then the visual appearance should match the baseline
    And the visual test result should be passing

  @visual @missing @layout
  Scenario: Detect missing header section
    Given a website URL is provided dynamically
    When the website is opened
    And a critical UI section is removed from the page
    Then the visual appearance should match the baseline
    And the visual test result should be passing

  @visual @missing @layout
  Scenario: Detect missing navigation section
    Given a website URL is provided dynamically
    When the website is opened
    And the "nav" section is removed from the page
    Then the visual appearance should match the baseline
    And the visual test result should be passing

  @visual @overflow @layout
  Scenario: Detect content overflow issue
    Given a website URL is provided dynamically
    When the website is opened
    And content overflow is introduced to the page
    Then the page should have no overflow issues
    And the visual test result should be passing

  @visual @negative @strict
  Scenario: Detect text content change regression
    Given a website URL is provided dynamically
    When the website is opened
    And the "h1" element text is changed to "BROKEN HEADING DEFECT INJECTED"
    Then the visual appearance should match the baseline
    And the visual test result should be passing