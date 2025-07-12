package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertTrue;

public class WebsiteSteps {
    private WebDriver driver;

    @Given("user opens browser")
    public void userOpensBrowser() {
        driver = new HtmlUnitDriver();
    }

    @When("user navigates to {string}")
    public void userNavigatesTo(String url) {
        driver.get(url);
    }

    @Then("title should contain {string}")
    public void titleShouldContain(String expected) {
        String title = driver.getTitle();
        assertTrue("Page title does not contain expected text", title.contains(expected));
        driver.quit();
    }
}
