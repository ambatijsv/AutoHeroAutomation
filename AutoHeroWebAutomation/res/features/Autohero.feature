Feature: Auto Hero Application Search Functionality

  @AutoHero
  Scenario Outline: Verify Auto Hero Application Search with filters
    Given User launches the autohero application
    When User land on the search page of the application
    Then user apply filters for registration statring from <registrationStartYear> in the dropdown
    And User apply sort by price in descending orrder with input as <descendingOrdervalue>
    And Verify all cars are filtered by first registration year from <registrationStartYear> in the search results
    And Verify all cars are sorted by price descending

    Examples: 
      | registrationStartYear | descendingOrdervalue |
      |                  2015 | Höchster Preis       |
