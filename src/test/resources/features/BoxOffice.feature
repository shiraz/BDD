Feature: Movies Box Office
  In order to see how the movie business is faring, I need to get a yearly report of box office earnings.

  Scenario Outline: <year> Universal Movies <earning_name> Earnings
    Given I am on the <year> Yearly Box Office page
    And I have a structure to store the <year> box office data
    Then I should store all <year> box office data pertaining to the Universal brand
    And I should verify that all the <year> Universal movies have <earning_name> earnings of over $<earnings> million each

    Examples:
      | year | earning_name | earnings |
      | 2016 | domestic     | 45.0     |
      | 2017 | worldwide    | 100.0    |