package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.WordDocumentUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class WebsiteSteps {
    private WebDriver driver;
    private WordDocumentUtil wordDocUtil;
    private String currentStepText = "";

    public WebsiteSteps() {
        // ✅ Generate filename with timestamp
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String filePath = "target/test-screenshots_" + timestamp + ".docx";
        wordDocUtil = new WordDocumentUtil(filePath);
    }

    @Given("user opens browser")
    public void userOpensBrowser() {
        currentStepText = "Given user opens browser";
        System.setProperty("webdriver.chrome.driver", "C://try//path//to//chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @When("user navigates to {string}")
    public void userNavigatesTo(String url) {
        currentStepText = "When user navigates to \"" + url + "\"";
        driver.get(url);
    }

    @Then("title should contain {string}")
    public void titleShouldContain(String expectedTitlePart) {
        currentStepText = "Then title should contain \"" + expectedTitlePart + "\"";
        String actualTitle = driver.getTitle();
        assertTrue("Title mismatch!\nExpected to contain: \"" + expectedTitlePart + "\"\nBut actual title was: \"" + actualTitle + "\"",
                actualTitle.contains(expectedTitlePart));
    }

    @AfterStep
    public void afterEachStep(Scenario scenario) {
        if (driver != null && currentStepText != null && !currentStepText.isEmpty()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            wordDocUtil.addStepWithScreenshot(currentStepText, screenshot);
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
