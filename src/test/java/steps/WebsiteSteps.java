package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.WordDocumentUtil;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class WebsiteSteps {
    private WebDriver driver;
    private WordDocumentUtil wordDocUtil;

    public WebsiteSteps() {
        wordDocUtil = new WordDocumentUtil("target/test-screenshots.docx");
    }

    @Given("user opens browser")
    public void userOpensBrowser() {
        System.setProperty("webdriver.chrome.driver", "C://try//BookingAppointement//path//to//chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    @When("user navigates to {string}")
    public void userNavigatesTo(String url) {
        driver.get(url);
    }

    @Then("title should contain {string}")
    public void titleShouldContain(String expectedTitlePart) {
        String actualTitle = driver.getTitle();
        assertTrue("Title mismatch!\nExpected to contain: \"" + expectedTitlePart + "\"\nBut actual title was: \"" + actualTitle + "\"",
                actualTitle.contains(expectedTitlePart));
    }

    @AfterStep
    public void afterEachStep(Scenario scenario) {
        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            wordDocUtil.addStepWithScreenshot(scenario.getName(), screenshot);
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        wordDocUtil.saveAndClose();
    }
}
