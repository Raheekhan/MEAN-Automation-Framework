package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseUtil {

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    DateFormat dateformat = new SimpleDateFormat("MM-dd-yyyy HHmmss");
    Date date = new Date();

    private String url = "http://localhost:4200/";

    public WebDriver driver;

    @BeforeSuite
    public void startExtentReporting() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/ExtentReports/ExtentReportResults.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    @Parameters({"useHeadless", "useLocalEnv", "useGridEnv", "browserName", "nodeURL"})
    public void setUp(@Optional boolean useHeadless, @Optional boolean useLocalEnv, @Optional boolean useGridEnv,
                      @Optional String browserName, @Optional String nodeURL, @Optional Method method) {

        test = extent.createTest(method.getName());

        if (useLocalEnv) {
            getLocalDriver(browserName);
        } else if (useGridEnv) {
            getGridDriver(browserName, nodeURL);
        } else if (useHeadless) {
            getChromeHeadlessDriver();
        }

        driver.manage().window().maximize();
        driver.navigate().to(url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(MarkupHelper.createLabel(result.getName() + "Test Case Failed", ExtentColor.RED));
            test.fail(result.getThrowable());
            captureScreenshot(result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass(MarkupHelper.createLabel(result.getName() + "Test Case Passed", ExtentColor.GREEN));
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void endExtentReporting() {
        extent.flush();
    }

    public WebDriver getLocalDriver(String browserName) {
        if (browserName.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/driver/geckodriver");
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Cannot find driver!");
        }
        return driver;
    }

    public WebDriver getChromeHeadlessDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");
        driver = new ChromeDriver(options);
        return driver;
    }


    public WebDriver getGridDriver(String browserName, String nodeURL) {

        DesiredCapabilities cap;

        try {
            if (browserName.equalsIgnoreCase("Chrome")) {
                cap = DesiredCapabilities.chrome();
                cap.setBrowserName(browserName);
                cap.setPlatform(Platform.MAC);
                driver = new RemoteWebDriver(new URL(nodeURL), cap);
            } else if (browserName.equalsIgnoreCase("Firefox")) {
                cap = DesiredCapabilities.firefox();
                cap.setPlatform(Platform.MAC);
                cap.setBrowserName(browserName);
                driver = new RemoteWebDriver(new URL(nodeURL), cap);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private String captureScreenshot(String screenshotName) {
        DateFormat dateFormat = new SimpleDateFormat("(MM.dd.yyyy-HH;mma)");
        Date date = new Date();
        dateFormat.format(date);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File screenshotFile = new File(System.getProperty("user.dir") +
                "/screenshots/" + screenshotName + " " + dateFormat.format(date) + ".png");
        try {
            FileUtils.copyFile(file, screenshotFile);
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            e.printStackTrace();
        }
        return screenshotName;
    }
}
