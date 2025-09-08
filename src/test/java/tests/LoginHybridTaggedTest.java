package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.DataProviderFactory;

public class LoginHybridTaggedTest extends BaseTest {
    @Test(dataProvider = "loginData", dataProviderClass = DataProviderFactory.class, groups = {"ui"})
    public void testLogin(String username, String password, boolean isValid, String source) {
        getDriver().get("https://rahulshettyacademy.com/loginpagePractise/");
        getDriver().findElement(By.id("username")).sendKeys(username);
        getDriver().findElement(By.id("password")).sendKeys(password);
        getDriver().findElement(By.cssSelector("input[value='user']")).click();
        getDriver().findElement(By.id("signInBtn")).click();

        if (isValid) {
            Assert.assertTrue(getDriver().getPageSource().contains("Shop"));
        } else {
            Assert.assertTrue(getDriver().getPageSource().contains("Incorrect"));
        }
    }
}