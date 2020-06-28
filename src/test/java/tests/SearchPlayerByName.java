package Tests;

import Helpers.ExcelHelper;
import Pages.HomePage;
import Pages.PlayerPage;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class SearchPlayerByName extends BaseFifaTest{

    @Test(dataProvider = "getData")
    public void searchPlayerByNameTest(String eName, String eOvr, String eNationality, String eClub) throws IOException {
        ExtentTest test = reports.createTest("Search Player By Name Test - " + eName);
        driver.get("https://sofifa.com/");
        HomePage homePage = new HomePage(driver);
        test.info("SoFifa homepage", getScreenshot());
        homePage.setPlayerName(eName);
        test.info("Setting player name", getScreenshot());
        PlayerPage playerPage = homePage.openPlayerPage();
        test.info("Show player page", getScreenshot());
        String overallRating = playerPage.getPlayerOverallRating();
        Assert.assertEquals(overallRating, eOvr);

        String playerNationality = playerPage.getPlayerNationality();
        Assert.assertEquals(playerNationality, eNationality);

        String currentClub = playerPage.getPlayerCurrentClub();
        Assert.assertEquals(currentClub, eClub);
    }
    @DataProvider
    public Object[][] getData() {
        Object [][] data = null;
        try {
            data = ExcelHelper.readExcelFile(new File("src\\test\\resources\\PlayersData.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
