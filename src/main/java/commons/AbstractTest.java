
package commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AbstractTest {
    protected WebDriver driver;
    protected final Log log;

    protected AbstractTest() {

        log = LogFactory.getLog(getClass());
    }

    public WebDriver getDriver() {

        return driver;
    }
    protected WebDriver openMultiBrowser(String browserName) {

        if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else if (browserName.equalsIgnoreCase("chromeheadless")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size="+Constants.HEADLESS_RESOLUTION);
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        }else {
            System.out.println("Please choose your browser name in TestNG xml");
        }
        driver.get(Constants.STAGING);
        driver.manage().timeouts().implicitlyWait(Constants.LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
    private boolean checkPassed(boolean condition) {
        boolean pass = true;
        try {
            if (condition == true)
                log.info("===PASSED==");
            else
                log.info("===FAILED==");
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;

            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyTrue(boolean condition) {
        return checkPassed(condition);
    }

    private boolean checkFailed(boolean condition) {
        boolean pass = true;
        try {
            if (condition == false)
                log.info("===PASSED===");
            else
                log.info("===FAILED===");
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        return checkFailed(condition);
    }

    private boolean checkEquals(Object actual, Object expected) {
        boolean pass = true;
        boolean status;
        try {
            if (actual instanceof String && expected instanceof String) {
                actual = actual.toString().trim();
                log.info("Actual = " + actual);
                expected = expected.toString().trim();
                log.info("Expected = " + expected);
                status = (actual.equals(expected));
            } else {
                status = (actual == expected);
            }

            log.info("Compare value = " + status);
            if (status) {
                log.info("===PASSED===");
            } else {
                log.info("===FAILED===");
            }
            Assert.assertEquals(actual, expected, "Value is not matching!");
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        return checkEquals(actual, expected);
    }


    protected void closeBrowserAndDriver(WebDriver driver) {
        try {
            // get ra tên của OS và convert qua chữ thường
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            // Khai báo 1 biến command line để thực thi
            String cmd = "";
            if (driver != null) {
                driver.quit();
            }

            if (driver.toString().toLowerCase().contains("chrome")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill chromedriver";
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                }
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            }

            if (driver.toString().toLowerCase().contains("internetexplorer")) {
                if (osName.toLowerCase().contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
                    Process process = Runtime.getRuntime().exec(cmd);
                    process.waitFor();
                }
            }
            if (driver.toString().toLowerCase().contains("gecko")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill geckodriver";
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                }
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            }
            if (driver.toString().toLowerCase().contains("safari")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill safaridriver";
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq safaridriver*\"";
                }
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            }
            log.info("---------- QUIT BROWSER SUCCESS ----------");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
    @BeforeSuite
    public void deleteAllFilesInReportNGScreenshot(){
        System.out.println("--------------START delete file in folder-----");
        deleteAllFileInFolder();
        System.out.println("--------------END delete file in folder-----");
    }//
    public void deleteAllFileInFolder(){
        try{
            //String pathFolderDownload = workingDir + "//ReportNGScreenShots";
            String pathFolderDownload = System.getProperty("user.dir") + "//ReportNGScreenShots";
            File file= new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            for(int i=0; i< listOfFiles.length;i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println(listOfFiles[i].getName());
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        }catch (Exception e){
            System.out.print(e.getMessage());
        }
    }

    public int randomDataTest() {
        Random random = new Random();
        return random.nextInt(99999);
    }
    public String replaceFirstData(String value,String oldData, String newData){
        return value.replaceFirst(oldData,newData);
    }
    protected static String getMinuteCurrent(){
        DateTime nowUTC = new DateTime(DateTimeZone.UTC);
        return nowUTC.getMinuteOfHour()+"";
    }
    protected static String getHourCurrent(){
        DateTime nowUTC = new DateTime(DateTimeZone.UTC);
        int hour = 7+ nowUTC.getHourOfDay();
        return hour+"";
        //	return nowUTC.getHourOfDay()+"";
    }
    protected static String getCurrentDay() {
        DateTime nowUTC = new DateTime(DateTimeZone.UTC);
        int day = nowUTC.getDayOfMonth();
        if (day < 10) {
            String dayValue = "0" + day;
            return dayValue;
        }
        return day + "";
    }

    protected static String getCurrentMonth() {
        DateTime now = new DateTime(DateTimeZone.UTC);
        int month = now.getMonthOfYear();
        if (month < 10) {
            String monthValue = "0" + month;
            return monthValue;
        }
        return month + "";
    }

    protected static String getCurrentYear() {
        DateTime now = new DateTime(DateTimeZone.UTC);
        return now.getYear() + "";
    }

    protected static String getToday() {
        //return getCurrentDay() + "/" + getCurrentMonth() + "/" + getCurrentYear();
        return getCurrentMonth() + "/" + getCurrentDay() + "/" + getCurrentYear();
    }
    protected static String getTimeCurrent(){
        return getHourCurrent() + ":" +getMinuteCurrent();
    }

}