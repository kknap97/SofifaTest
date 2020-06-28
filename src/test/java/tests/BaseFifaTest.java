package Tests;


import Helpers.SeleniumHelper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public abstract class BaseFifaTest {

    protected WebDriver driver;
    private Logger logger = Logger.getLogger(BaseFifaTest.class);

    protected ExtentReports reports;
    protected ExtentHtmlReporter reporter;

    @BeforeTest
    public void setUpReporter(){
        reporter = new ExtentHtmlReporter("src//test//resources//reports//index.html");
        reports = new ExtentReports();
        reports.attachReporter(reporter);
    }
    @BeforeMethod
    public void setUp(){
        logger.info("Before test");
        String driverPath = "D:\\NAUKA\\Projekty\\SofifaTest\\src\\test\\resources\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = getDriver();
    }

    @AfterMethod
    public void tearDown(){
        logger.info("After test");
        driver.quit();
        resetDriver();
    }

    private static WebDriver driverInstance;
    public static WebDriver getDriver(){

        if (driverInstance == null) {
            File chromeExe = new File ("src//test//resources//drivers//chromedriver.exe");
            ChromeDriverService chromeService = new ChromeDriverService.Builder().usingDriverExecutable(chromeExe).usingAnyFreePort().build();
            driverInstance = new ChromeDriver(chromeService);
            driverInstance.manage().window().maximize();
        }
        return driverInstance;
    }
    public static void resetDriver(){
        driverInstance = null;
    }

    @AfterTest
    public void tearDownReporter(){
        reporter.flush();
        reports.flush();
    }

    MediaEntityModelProvider getScreenshot() throws IOException {
        return MediaEntityBuilder.createScreenCaptureFromPath(SeleniumHelper.takeScreenshoot(driver)).build();
    }
}
