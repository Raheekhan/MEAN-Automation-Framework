package pages.user.dashboard;

import base.BaseUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Messages {

    private WebDriver driver;
    private WebDriverWait wait;

    public Messages(WebDriver driver) {
        wait = new WebDriverWait(driver, 5);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "")
    WebElement a;

    @FindBy(id = "")
    WebElement b;

    @FindBy(id = "")
    WebElement c;


}
