package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

public class WebsiteSteps {
    private WebDriver driver;

    @Given("user opens browser")
    public void userOpensBrowser() {
        System.setProperty("webdriver.chrome.driver", "C://try//BookingAppointement//path//to//chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless"); // removed to see browser UI
        driver = new ChromeDriver(options);
    }

    @When("user navigates to {string}")
    public void userNavigatesTo(String url) {
        driver.get(url);
    }

    @Then("title should contain {string}")
    public void titleShouldContain(String expectedTitlePart) {
        String actualTitle = driver.getTitle();
        assertTrue(
                "Title mismatch!\nExpected to contain: \"" + expectedTitlePart + "\"\nBut actual title was: \"" + actualTitle + "\"",
                actualTitle.contains(expectedTitlePart)
        );
    }

    // This method runs after every scenario, closing browser regardless of pass/fail
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
