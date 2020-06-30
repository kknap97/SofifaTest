package pages;

import helpers.SeleniumHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlayerPage {

    private SeleniumHelper helper;
    private WebDriver driver;
    private Logger logger = Logger.getLogger(PlayerPage.class);

    @FindBy (xpath = "//*[@id=\"list\"]/div[2]/div/div/div[1]/div[1]/div[2]/div/div[4]/div/h5/a")
    private WebElement playerNationality;

    @FindBy (xpath = "//*[@id=\"list\"]/div[2]/div/div/div[1]/div[1]/div[2]/div/div[3]/div/h5/a")
    private WebElement currentClub;

    @FindBy (xpath = "//*[@id=\"list\"]/div[2]/div/div/div[1]/div[1]/div[1]/div/section/div/div[1]/div/span")
    private WebElement overallRating;

    @FindBy (xpath = "//*[@id=\"list\"]/div[2]/div/div/div[1]/div[1]/div[1]/div/section/div/div[3]/div")
    private WebElement valuePlayer;

    public PlayerPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.helper = new SeleniumHelper(driver);
    }

    public String getPlayerOverallRating(){
        helper.waitForElementToBeDisplayed(overallRating);
        logger.info(overallRating.getText());
        return overallRating.getText();
    }
    public String getPlayerNationality() {
        helper.waitForElementToBeDisplayed(playerNationality);
        logger.info(playerNationality.getText());
        return playerNationality.getText();
    }
    public String getPlayerCurrentClub(){
        helper.waitForElementToBeDisplayed(currentClub);
        logger.info(currentClub.getText());
        return currentClub.getText();
    }
}

