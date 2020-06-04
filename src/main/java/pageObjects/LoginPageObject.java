package pageObjects;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends AbstractPage {
    WebDriver driver;

    public LoginPageObject(WebDriver mappingDriver) {
        driver = mappingDriver;
    }

}
