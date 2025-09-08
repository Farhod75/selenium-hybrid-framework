package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

public class LoginUITest extends BaseTest {
    @Test(groups = {"ui"})
    public void verifyLoginPageLoads() {
        getDriver().get("https://rahulshettyacademy.com/loginpagePractise/");
        Assert.assertTrue(getDriver().findElement(By.id("username")).isDisplayed());
    }
}