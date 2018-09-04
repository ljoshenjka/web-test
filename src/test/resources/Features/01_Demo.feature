@e2e

Feature: Configure cinema ticket
  In order to buy cinema ticket
  Customer should be able to
  Make all necessary ticket configurations

  Scenario: Configure 2 tickets with random seats
    Given user opens app link
    And user logs in into app with credentials
      | email    | neotech@mailinator.com |
      | password | mysterious-lips-class  |
    When user opens "My Personal Data" from user menu
    And submit personal name
      | name    | Neo  |
      | surname | Tech |
    Then personal data is updated
    When user navigates to "Movies" section
    And select status "In Theatre" from movie filter
    Then movie list header is "In Theatre"
    When user select random movie from movie list
    And set random available date
    And user start buying tickets
    Then seat plan page is shown
    When user select 2 random seats from rows
      | 5 |
      | 6 |
    Then 2 people are going
    And full ticket price is shown
    When user enters voucher "$RANDOM"
    Then voucher error message is shown
    When user submit seat selection
    Then ordering page is shown
    And correct ticket price is shown
    And correct number of tickets is shown
    When user selects to change order
    Then seat plan page is shown
    When user deselect all seats
    And user select 1 random seats from rows
      | 5 |
      | 6 |
    Then 1 people are going
    And full ticket price is shown
    When user submit seat selection
    Then ordering page is shown
    And correct ticket price is shown
    And correct number of tickets is shown
    And correct payment methods are shown
      | swedbank    |
      | luminor     |
      | seb         |
      | citadele    |
      | credit card |