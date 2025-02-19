package org.opencart.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.opencart.pages.LoginPage;

public class LoginPageStepDef
{
    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setup() {
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if(driver!=null){
            driver.quit();
        }
    }

   @Given("I am on the opencart login page")
   public void i_am_on_the_open_cart_login_page() {
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
        loginPage = new LoginPage(driver);
    }

   @Given("I have entered a valid username and password")
   public void i_have_entered_a_valid_username_and_password() {
            loginPage.enterEmail("qatest@gmail.com");
            loginPage.enterPassword("Test@987456");
        }

   @Given("I have entered invalid {string} and {string}")
   public void i_have_entered_invalid_and(String username, String password) {
                loginPage.enterEmail(username);
                loginPage.enterPassword(password);
            }

   @When("I click on the login button")
   public void i_click_on_the_login_button() {
        loginPage.clickLoginButton();
        }

   @Then("I should be logged in successfully")
   public void i_should_be_logged_in_successfully() {
        Assert.assertEquals(loginPage.checkLogoutLink(), true);
    }

   @Then("I should see an error message indicating {string}")
   public void i_should_see_an_error_message_indicating(String errorMessage) {
        Assert.assertEquals(driver.findElement(By.cssSelector(".alert-danger")).isDisplayed(), true);
    }

   @When("I click on the \"Forgotten Password\" link")
   public void i_click_on_the_forgotten_password_link() {
        loginPage.clickForgottenPasswordLink();
    }

   @Then("I should be redirected to the password reset page")
   public void i_should_be_redirected_to_the_password_reset_page() {
        Assert.assertTrue(loginPage.getForgotPwdPageUrl().contains("account/forgotten"));
    }

}
