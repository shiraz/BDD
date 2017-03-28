Feature: Movies Box Office
  In order to see how the movie business is faring, I need to get a yearly report of box office earnings.

  Background:
    Given I am on the 2017 Yearly Box Office page
    When I have a structure to store the 2017 box office data
    Given I am on the 2016 Yearly Box Office page
    When I have a structure to store the 2016 box office data

  Scenario: 2017 Universal Movies Domestic Earnings
    Then I should store all 2017 box office data pertaining to the Universal brand
    Then I should verify that all the 2017 Universal movies have domestic earnings of over $45.0 million each

  Scenario: 2016 Universal Movies Worldwide Earnings
    Then I should store all 2016 box office data pertaining to the Universal brand
    Then I should verify that all the 2016 Universal movies have worldwide earnings of over $100.0 million each