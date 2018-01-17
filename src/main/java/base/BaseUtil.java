package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseUtil {

    private String url = "http://localhost:4200/";

    public WebDriver driver;

    @BeforeMethod
    @Parameters({"useHeadless", "useLocalEnv", "useGridEnv", "browserName", "nodeURL"})
    public void setUp(@Optional boolean useHeadless, @Optional boolean useLocalEnv, @Optional boolean useGridEnv,
                      @Optional String browserName, @Optional String nodeURL) {

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
            captureScreenshot(result.getName());
        }

        if (driver != null) {
            driver.quit();
        }
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
