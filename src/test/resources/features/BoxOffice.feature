Feature: Movies Box Office
  In order to see how the movie business is faring, I need to get a yearly report of box office earnings.

  Background:
    Given I am on the 2017 Yearly Box Office page
    When I have a structure to store the box office data

  Scenario: 2017 Universal Movie Domestic Earnings
    Then I should store all domestic box office data pertaining to the Universal brand.
    Then I should verify that all the Universal movies have domestic earnings of over $45 million each.


  #Scenario: 2017 Universal Movie Worldwide Earnings
    #Then I should store all worldwide box office data pertaining to the Universal brand.
    #Then I should verify that all the Universal movies have worldwide earnings of over $100 million each.