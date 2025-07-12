Feature: Visit Website

  Scenario: Reach Example website
    Given user opens browser
    When user navigates to "https://example.com"
    Then title should contain "Example Domain"
