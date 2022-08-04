package pageFunctions;

import pageObjects.PlayersPage;
import util.WebActions;

public class PlayerPageFunctions extends WebActions {

    PlayersPage playersPageElements;

    public PlayerPageFunctions() {
        playersPageElements = new PlayersPage();
    }

    public boolean verifyStickyPlayerIsDisplayed() {
       return isDisplayed(playersPageElements.getStickyPlayer());
    }

    public boolean verifyClassicPlayerIsDisplayed(){
        return isDisplayed(playersPageElements.getClassicPlayer());
    }

    public int getStickyPlayerHeight() {
        return getElementHeight(playersPageElements.getStickyPlayer());
    }

    public int getStickyPlayerWidth() {
        return getElementWidth(playersPageElements.getStickyPlayer());
    }

    public int getClassicPlayerHeight() {
        return getElementHeight(playersPageElements.getClassicPlayer());
    }
    public int getClassicPlayerWidth(){
        return getElementWidth(playersPageElements.getClassicPlayer());
    }

    public boolean verifyPlayerIsStickToThePage() {
        return isDisplayed(playersPageElements.getStickedPlayer());
    }

    public boolean waitForSkipButton() {
        return isDisplayed(playersPageElements.getStickyPlayerSkipBtn());
    }

    public void skipTheAd(){
        playersPageElements.getStickyPlayerSkipBtn().click();
    }

    public void closeTheStickyPlayer() {
        playersPageElements.getStickyPlayerCloseBtn().click();
    }

}
