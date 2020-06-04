package account;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.LoginPageObject;

public class Login extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = openMultiBrowser(browserName);
        loginPage = PageGeneratorManager.getLoginPage(driver);
    }

}
