package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy (xpath = "//span[@class = 'bp3-icon bp3-icon-search']")
    private WebElement searchInputSpan;

    @FindBy (name = "keyword")
    private WebElement searchInputPlayer;

    @FindBy (xpath = "/html/body/div[1]/div/div/div[1]/table/tbody/tr/td[2]/a[1]")
    private WebElement playerModal;

    private WebDriver driver;

    private Logger log = Logger.getLogger(HomePage.class);

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setPlayerName(String playerName){
        log.info("Setting player name");
        searchInputSpan.click();
        searchInputPlayer.sendKeys(playerName);
        log.info("Player name is set");
        searchInputPlayer.sendKeys(Keys.ENTER);
    }
    public String getNamePlayer(){
        return playerModal.getText();
    }
    public PlayerPage openPlayerPage(){
        log.info("Open individual player page");
        playerModal.click();
        log.info("Individual player page is opened");
        return new PlayerPage(driver);
    }
}
