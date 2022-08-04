package stepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.CaptureRequestsSent;
import managers.PropertyReader;
import managers.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;
import org.testng.TestRunner;
import pageFunctions.PlayerPageFunctions;
import util.WebActions;

import java.time.temporal.Temporal;
import java.util.Map;

public class E2E {
    PropertyReader reader;
    WebActions webActions;
    PlayerPageFunctions playerPageFunctions;

    public E2E() {
        reader = new PropertyReader();
        webActions = new WebActions();
        playerPageFunctions = new PlayerPageFunctions();
    }

    @Given("As a user, I launch the application")
    public void launchTheApp() {
        String url = reader.getPropertyValue("url");
        webActions.launchApp(url);
    }

    @When("I view the page")
    public void iViewThePage() {

    }

    @Then("I am able to see one of the player \\(classic or sticky)")
    public void iAmAbleToSeeTheClassicStickyPlayer() {
        boolean isPlayerDisplayed = playerPageFunctions.verifyStickyPlayerIsDisplayed();
        if (!isPlayerDisplayed) {
            Assert.assertTrue(playerPageFunctions.verifyClassicPlayerIsDisplayed());
        }
    }

    @And("The {string} player has {int} height and {int} width")
    public void theClassicPlayerHasHeightAndWidth(String playerType, int height, int width) {
        if (playerType.equalsIgnoreCase("classic")) {
            Assert.assertEquals(height, playerPageFunctions.getClassicPlayerHeight());
            Assert.assertEquals(width, playerPageFunctions.getClassicPlayerWidth());
        } else {
            Assert.assertEquals(height, playerPageFunctions.getStickyPlayerHeight());
            Assert.assertEquals(width, playerPageFunctions.getStickyPlayerWidth());
        }
    }

    @And("I scroll down to the bottom of the page")
    public void iScrollDownToTheBottomOfThePage() {
        webActions.scrollDown();
    }

    @Then("The sticky player is displayed at the bottom right corner of the page")
    public void theStickyPlayerIsDisplayedAtTheBottomRightCornerOfThePage() {
        Assert.assertTrue(playerPageFunctions.verifyPlayerIsStickToThePage());
    }

    @And("The stickychange event is triggered")
    public void theStickychangeEventIsTriggered() {
        CaptureRequestsSent requestsSent = new CaptureRequestsSent();
        CaptureRequestsSent.data = requestsSent.captureHttpRequests(WebDriverManager.getDriver());
        for (Map.Entry<String, Map<String, String>> entry : CaptureRequestsSent.data.entrySet()) {
            if (entry.getKey().trim().equalsIgnoreCase(reader.getPropertyValue("events_url"))) {
                Map<String, String> apiData = entry.getValue();
                for (Map.Entry<String, String> e : apiData.entrySet()) {
                    //write code to fetch the event

                }
            } else {
                Assert.fail("The event stickychange is not triggered");
            }
        }
    }

    @When("The skip button is enabled")
    public void theSkipButtonIsEnabled() {
        Assert.assertTrue(playerPageFunctions.waitForSkipButton());
    }

    @Then("I clicked on skip button")
    public void iClickedOnSkipButton() {
        playerPageFunctions.skipTheAd();
    }

    @And("The response code {int} received for the sr.bladex.js request")
    public void the_response_code_received_for_the_sr_bladex_js_request(int expectedStatusCode) {
        for (Map.Entry<String, Map<String, String>> entry : CaptureRequestsSent.data.entrySet()) {
            if (entry.getKey().trim().equalsIgnoreCase(reader.getPropertyValue("blade_url"))) {
                Map<String, String> apiData = entry.getValue();
                System.out.println("The status is :" + apiData.get("status"));
                Assert.assertEquals(Integer.parseInt(apiData.get("status")), expectedStatusCode);
            }
        }
    }

    @And("I closed the sticky player by clicking on player close button")
    public void theIClosedTheStickyPlayerByClickingOnPlayerCloseButton() throws InterruptedException {
        playerPageFunctions.closeTheStickyPlayer();
    }

    @And("The a=(.*) event is fired from player")
    public void theAXbcEventIsFiredFromPlayer(String impression) throws InterruptedException {
        int retry = 10;
        System.out.println("executing test");
        CaptureRequestsSent requestsSent = new CaptureRequestsSent();
        int count = 0;
        for (; count <= retry; count++) {
            System.out.println("count is : " + count);
            for (Map.Entry<String, Map<String, String>> entry : CaptureRequestsSent.data.entrySet()) {
                if (entry.getKey().trim().equalsIgnoreCase(reader.getPropertyValue("player_events_url"))) {
                    System.out.println("Key: " + entry.getKey());
                    Map<String, String> apiData = entry.getValue();
                    if (apiData.get("body") != null) {
                        System.out.println(apiData.get("body"));
                        if (apiData.get("body").contains(impression)) {
                            break;
                        }
                    }
                }
            }
            requestsSent.captureHttpRequests(WebDriverManager.getDriver());
        }
        if (count >= retry) {
            Assert.fail("the "+impression+" event is not triggered");
        }
    }

}
