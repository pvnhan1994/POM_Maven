package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class AbstractPage extends AbstractTest {

    public void openUrl(WebDriver driver, String urlValue) {
        driver.get(urlValue);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getCurrentPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void refreshToPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void fowardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    public void cancelAlert(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    public String getTextAlert(WebDriver driver) {
        return driver.switchTo().alert().getText();
    }

    public void sendkeyToAlert(WebDriver driver, String value) {
        driver.switchTo().alert().sendKeys(value);
    }

    // WebElement
    public void clickToElement(WebDriver driver, String locator) {
        highlightElement(driver, locator);
        element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public void clickToElement(WebDriver driver, String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        highlightElement(driver, locator);
        element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public void sendkeyElement(WebDriver driver, String locator, String value) {
        highlightElement(driver, locator);
        element = driver.findElement(By.xpath(locator));
        element.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);
    }

    public void sendkeyElements(WebDriver driver, String locator, String sendKeysValue, String... values) {
        locator = String.format(locator, (Object[]) values);
        highlightElement(driver, locator);
        element = driver.findElement(By.xpath(locator));
        element.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        element.sendKeys(Keys.DELETE);
        element.sendKeys(sendKeysValue);
    }


    public void selectItemInDropDown(WebDriver driver, String locator, String itemText) {
        element = driver.findElement(By.xpath(locator));
        select = new Select(element);
        select.selectByVisibleText(itemText);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String allItemXpath,
                                           String expectedValueItem) throws Exception {
        javascriptExecutor = (JavascriptExecutor) driver;
        waitExplicit = new WebDriverWait(driver, 30);

        element = driver.findElement(By.xpath(parentXpath));
        javascriptExecutor.executeScript("arguments[0].click();", element);
        Thread.sleep(1000);

        waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

        elements = driver.findElements(By.xpath(allItemXpath));

        for (WebElement childElement : elements) {
            if (childElement.getText().equals(expectedValueItem)) {
                if (childElement.isDisplayed()) {
                    childElement.click();
                } else {
                    javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
                    Thread.sleep(1000);
                    javascriptExecutor.executeScript("arguments[0].click();", childElement);
                }
                Thread.sleep(1000);
                break;
            }
        }
    }

    public void selectItemInCustomDropdownClickByWebdriver(WebDriver driver, String clickDropdown, String listItem, String expectedItem) throws Exception {

        WebElement parentDropdown = driver.findElement(By.xpath(clickDropdown));
        //javascriptExecutor.executeScript("arguments[0].click();", parentDropdown);
        parentDropdown.click();
        Thread.sleep(1000);

        waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(listItem)));

        List<WebElement> allItems = driver.findElements(By.xpath(listItem));
        //System.out.println("Get all value in dropdown =" + allItems.size());

        for (WebElement items : allItems) {
            //	System.out.println("Text moi lan get = " + items.getText());

            if (items.getText().equals(expectedItem)) {

                if (items.isDisplayed()) {
                    //		System.out.println("Click by Selenium = " + items.getText());
                    items.click();

                } else {
                    javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", items);
                    Thread.sleep(1000);
                    //	System.out.println("Click by JS = " + items.getText());
                    javascriptExecutor.executeScript("arguments[0].click();", items);
                }
                Thread.sleep(1000);
                break;

            }

        }

    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
        element = driver.findElement(By.xpath(locator));
        return element.getAttribute(attributeName);
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeName, String... values) {
        locator = String.format(locator, (Object[]) values);
        element = driver.findElement(By.xpath(locator));
        return element.getAttribute(attributeName);
    }

    public String getTextElement(WebDriver driver, String locator) {
        highlightElement(driver, locator);
        element = driver.findElement(By.xpath(locator));
        return element.getText();
    }

    public String getTextElement(WebDriver driver, String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        highlightElement(driver, locator);
        element = driver.findElement(By.xpath(locator));
        return element.getText();
    }

    public int countElementsNumber(WebDriver driver, String locator) {
        elements = driver.findElements(By.xpath(locator));
        return elements.size();
    }

    public void checkToTheCheckbox(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToTheCheckbox(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isControlDisplayed(WebDriver driver, String locator) {
        highlightElement(driver, locator);
        element = driver.findElement(By.xpath(locator));
        return element.isDisplayed();
    }

    public boolean isControlDisplayed(WebDriver driver, String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        highlightElement(driver, locator);
        element = driver.findElement(By.xpath(locator));
        return element.isDisplayed();
    }

    public boolean isControlUndisplayed(WebDriver driver, String locator) {
        Date date = new Date();
        System.out.println("Start time=" + date.toString());

        overrideGlobalTimeOut(driver, Constants.SHORT_TIMEOUT);

        List<WebElement> elements = driver.findElements(By.xpath(locator));

        if (elements.size() == 0) {
            System.out.println("Element not in DOM");
            System.out.println("End time = " + new Date().toString());
            overrideGlobalTimeOut(driver, Constants.LONG_TIMEOUT);
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            System.out.println("Element in DOM but not visible/ displayed");
            System.out.println("End time = " + new Date().toString());
            overrideGlobalTimeOut(driver, Constants.LONG_TIMEOUT);
            return true;
        } else {
            System.out.println("Element in DOM and visible");
            overrideGlobalTimeOut(driver, Constants.LONG_TIMEOUT);
            return false;
        }
    }

    public boolean isControlSelected(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.isSelected();
    }

    public boolean isControlEnable(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.isEnabled();
    }

    public boolean isControlEnables(WebDriver driver, String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        highlightElement(driver, locator);
        element = driver.findElement(By.xpath(locator));
        return element.isEnabled();
    }

    public void switchToChildWindowByID(WebDriver driver, String parent) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parent)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public boolean closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size() == 1)
            return true;
        else
            return false;
    }

    public void hoverMouseToElement(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        action = new Actions(driver);
        action.moveToElement(element).perform();

    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        action = new Actions(driver);
        action.doubleClick(element).perform();

    }

    public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
        element = driver.findElement(By.xpath(locator));
        action = new Actions(driver);
        action.sendKeys(element, key).perform();
    }


    public void executeForBrowser(WebDriver driver, String javaSript) {
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript(javaSript);
    }

    public boolean verifyTextInInnerText(WebDriver driver, String textExpected) {
        javascriptExecutor = (JavascriptExecutor) driver;
        String textActual = (String) javascriptExecutor
                .executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        System.out.println("Text actual = " + textActual);
        return textActual.equals(textExpected);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        highlightElement(driver, locator);
        element = driver.findElement(By.xpath(locator));
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        highlightElement(driver, locator);
        element = driver.findElement(By.xpath(locator));
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value, String... values) {
        locator = String.format(locator, (Object[]) values);
        highlightElement(driver, locator);
        element = driver.findElement(By.xpath(locator));
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }


    public void scrollToBottomPage(WebDriver driver) {
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        element = driver.findElement(By.xpath(locator));
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
    }

    public Object navigateToUrlByJS(WebDriver driver, String url) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("window.location = '" + url + "'");
    }

    public boolean checkAnyImageLoaded(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        javascriptExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) javascriptExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != \"underfinded\" && arguments[0].naturalWidth >0",
                element);
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    // HTML 5
    public String getHtml5ValidationMessage(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
    }

    // waitExplicit
    public void waitForElementPresence(WebDriver driver, String locator) {
        waitExplicit = new WebDriverWait(driver, longTimeOut);
        byLocator = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.presenceOfElementLocated(byLocator));
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        waitExplicit = new WebDriverWait(driver, longTimeOut);
        byLocator = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byLocator));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        waitExplicit = new WebDriverWait(driver, longTimeOut);
        byLocator = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byLocator));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        waitExplicit = new WebDriverWait(driver, longTimeOut);
        byLocator = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.elementToBeClickable(byLocator));

    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        Date date = new Date();
        waitExplicit = new WebDriverWait(driver, longTimeOut);
        byLocator = By.xpath(locator);

        overrideGlobalTimeOut(driver, Constants.SHORT_TIMEOUT);
        System.out.println("Start time for wait invisible = " + date.toString());
        waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
        overrideGlobalTimeOut(driver, Constants.SHORT_TIMEOUT);
        System.out.println("End time for wait invisible = " + new Date().toString());
        overrideGlobalTimeOut(driver, Constants.LONG_TIMEOUT);

    }

    public void overrideGlobalTimeOut(WebDriver driver, int timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    public void highlightElement(WebDriver driver, String locator) {
        javascriptExecutor = (JavascriptExecutor) driver;
        element = driver.findElement(By.xpath(locator));
        String originalStyle = element.getAttribute("style");
        javascriptExecutor.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style", "border:3px solid green; border-style:dashed;");
        try {
            Thread.sleep(Constants.TIME_HIGHLIGHT);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        javascriptExecutor.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style", originalStyle);
    }

    public int listSizeLocatorInElements(WebDriver driver, String listLocator) {
        waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(listLocator)));
        List<WebElement> allItems = driver.findElements(By.xpath(listLocator));
        int size = allItems.size();
        return size;
    }

    public void selectFolder(String listFolder, String NameFolder) throws Exception {
        List<WebElement> allItems = driver.findElements(By.xpath(listFolder));
//		System.out.println("Tat ca phan tu trong dropdown =" + allItems.size());

        for (WebElement folder : allItems) {
//			System.out.println("Text moi lan get = " + folder.getText());

            if (folder.getText().contains(NameFolder)) {

                if (folder.isDisplayed()) {
                    System.out.println("Click by Selenium = " + folder.getText());
                    folder.click();
                }
                Thread.sleep(1000);
                break;
            }

        }

    }
   
    // Sort Asc/Desc
    public boolean isDataSortedAsceding(WebDriver driver, String locator) {
        ArrayList<String> arrayList = new ArrayList<>();

        List<WebElement> elementList = driver.findElements(By.xpath(locator));

        for (WebElement element : elementList) {
            arrayList.add(element.getText());
        }

        ArrayList<String> sortedList = new ArrayList<>();
        for (String child : arrayList) {
            sortedList.add(child);
        }

        Collections.sort(arrayList);

        for (String name : arrayList) {
            System.out.println(name);
        }

        return sortedList.equals(arrayList);
    }

    public boolean isDataSortedDesceding(WebDriver driver, String locator) {
        ArrayList<String> arrayList = new ArrayList<>();

        List<WebElement> elementList = driver.findElements(By.xpath(locator));

        for (WebElement element : elementList) {
            arrayList.add(element.getText());
        }

        ArrayList<String> sortedList = new ArrayList<>();
        for (String child : arrayList) {
            sortedList.add(child);
        }

        Collections.sort(arrayList);
        Collections.reverse(arrayList);
        for (String name : arrayList) {
            System.out.println(name);
        }

        return sortedList.equals(arrayList);
    }
    public void inputIntoDynamicTextbox(WebDriver driver, String textboxNameID, String valueToSendKey){
        waitForElementVisible(driver, AbstractPageUIs.DYNAMIC_TEXTBOX, textboxNameID);
        sendkeyElements(driver, AbstractPageUIs.DYNAMIC_TEXTBOX, valueToSendKey, textboxNameID);
    }
    public void clickIntoDynamicButton(WebDriver driver, String buttonNameID){
        waitForElementVisible(driver, AbstractPageUIs.DYNAMIC_BUTTON, buttonNameID);
        clickToElement(driver, AbstractPageUIs.DYNAMIC_BUTTON, buttonNameID);
    }


    private WebElement element;
    private List<WebElement> elements;
    private Select select;
    private JavascriptExecutor javascriptExecutor;
    private WebDriverWait waitExplicit;
    private Actions action;
    private By byLocator;
    private int longTimeOut = Constants.LONG_TIMEOUT;

}