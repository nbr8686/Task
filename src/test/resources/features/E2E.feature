@E2E
  Feature: Verify the end to end flow

    Scenario: Verify that one of the player is visible on the page
      Given As a user, I launch the application
      When I view the page
      Then I am able to see one of the player (classic or sticky)

    Scenario: Verify the response code for sr.bladex.js request
      Given As a user, I launch the application
      When I view the page
      Then I am able to see one of the player (classic or sticky)
      And The response code 200 received for the sr.bladex.js request

    Scenario: Verify that the players height & width match with the required dimensions (380 X 250)
      Given As a user, I launch the application
      When I view the page
      Then I am able to see one of the player (classic or sticky)
      And The "classic" player has 250 height and 380 width
      And The "sticky" player has 250 height and 380 width

    Scenario: Verify sticky functionality - Verify sticky state change with the ‘stickychange’ event listener
      Given As a user, I launch the application
      When I view the page
      Then I am able to see one of the player (classic or sticky)
      And I scroll down to the bottom of the page
      Then The sticky player is displayed at the bottom right corner of the page
      And The stickychange event is triggered

    Scenario: Verify that the ad is skipped on clicking skip button in the player
      Given As a user, I launch the application
      When I view the page
      Then I am able to see one of the player (classic or sticky)
      When The skip button is enabled
      Then I clicked on skip button

      @123
    Scenario: Verify that the 'xbc' event is fired when the player is closed
      Given As a user, I launch the application
      When I view the page
      Then I am able to see one of the player (classic or sticky)
      And I scroll down to the bottom of the page
      Then The sticky player is displayed at the bottom right corner of the page
      And I closed the sticky player by clicking on player close button
      And The a=xbc event is fired from player

    Scenario: Verify that the specific impression (a=ai) event is fired when the ad is displayed
      Given As a user, I launch the application
      When I view the page
      Then I am able to see one of the player (classic or sticky)
      When The skip button is enabled
      Then I clicked on skip button
      And The a=ai event is fired from player
