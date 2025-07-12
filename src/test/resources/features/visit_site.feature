Feature: Visit Website

  Scenario: Reach Example website
    Given user opens browser
    When user navigates to "https://the-internet.herokuapp.com/"
    Then title should contain "The Internet"
