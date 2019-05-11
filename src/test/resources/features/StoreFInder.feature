@web
  @sample
  Feature: Store Finder
  AS a user
  I WANT to find stores using store finder by post code, city
  SO THAT I can find nearest stores

  Scenario Outline: Find nearest store using post code or city

    Given I navigate to store finder
    When I search for store by entering "<location>"
    Then I should get results "<result>" related to search

    Examples:
      | location | result  |
      | LEEDS    | LEEDS   |
      | LS1 3EX  | LS1 3EX |

  Scenario: Find nearest store using locate me
    Given I navigate to store finder
    When I search for stores by locate me
    Then I should get list of nearest stores