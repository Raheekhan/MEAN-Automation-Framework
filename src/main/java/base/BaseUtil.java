package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
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
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BaseUtil {

    public static ExtentReports extent;

    private String url = "http://localhost:3000/";

    public WebDriver driver;

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }

    @BeforeMethod
    @Parameters({"useHeadless", "useLocalEnv", "useGridEnv", "browserName", "nodeURL"})
    public void setUp(@Optional boolean useHeadless, @Optional boolean useLocalEnv, @Optional boolean useGridEnv,
                      @Optional String browserName, @Optional String nodeURL, @Optional Method method) {

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

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(result.getName());
        }
        driver.quit();
    }

    @AfterSuite
    public void generateReport() {
        extent.close();
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

    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
