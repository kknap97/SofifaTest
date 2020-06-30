package helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalDateTime;

public class SeleniumHelper {
    private WebDriver driver;
    public SeleniumHelper (WebDriver driver){
        this.driver = driver;
    }

    public void waitForElementToBeDisplayed(WebElement element){
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static String takeScreenshoot(WebDriver driver) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("src/test/resources/screenshots/" + LocalDateTime.now().getNano() + ".png");
        Files.copy(screenshotFile.toPath(), destinationFile.toPath());
        return destinationFile.getAbsolutePath();
    }
}
