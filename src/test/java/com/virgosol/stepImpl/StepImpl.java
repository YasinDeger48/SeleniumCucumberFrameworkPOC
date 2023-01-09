package com.virgosol.stepImpl;

import com.virgosol.util.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import static org.junit.Assert.assertTrue;

public class StepImpl {

    @Given("{string} sitesini aç.")
    public void sitesini_aç(String website) {
        Driver.getDriver().get(website);
    }
    @When("Login linkine tıkla.")
    public void login_linkine_tıkla() {
        Driver.getDriver().findElement(By.xpath("//span[contains(@class,'login')]//a")).click();
    }
    @Then("Giriş sayfasında olduğunu doğrula.")
    public void giriş_sayfasında_olduğunu_doğrula() {
        Driver.getDriver().findElement(By.cssSelector("#loginbtn")).isDisplayed();
    }
    @Then("{string} username gir.")
    public void username_gir(String username) {
        Driver.getDriver().findElement(By.xpath("//input[@id='username']")).sendKeys(username);
    }
    @Then("{string} password gir.")
    public void password_gir(String password) {
        Driver.getDriver().findElement(By.xpath("//input[@id='password']")).sendKeys(password);
    }
    @Then("Login butonuna tıkla.")
    public void login_butonuna_tıkla() {
        Driver.getDriver().findElement(By.xpath("//button[@id='loginbtn']")).click();
    }
    @Then("{string} title'ın görüldüğünü doğrula.")
    public void title_ın_görüldüğünü_doğrula(String title) {
        assertTrue(Driver.getDriver().findElement(By.xpath("(//h2)[1]")).getText().contains(title));
    }


    //------------------//
    @When("Login syfasında olduğunu doğrula.")
    public void login_syfasında_olduğunu_doğrula() {
        assertTrue(Driver.getDriver().findElement(By.xpath("//h5[.='Login']")).isDisplayed());
    }
    @When("Enter username as {string}")
    public void enter_username_as(String username) {
        Driver.getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys(username);
    }
    @When("Enter password as {string}")
    public void enter_password_as(String password) {
        Driver.getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    }
    @When("Click Login button")
    public void click_login_button() {
        Driver.getDriver().findElement(By.cssSelector("button[type='submit']")).click();
    }
    @Then("Verify on login page")
    public void verify_on_login_page() {
        String title = Driver.getDriver().getCurrentUrl();
        System.out.println("title = " + title);
        assertTrue(title.contains("dashboard"));
    }

}
