package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Helper {

    private WebDriver driver;

    public Helper(WebDriver driver) {
        this.driver = driver;
    }

    public void getTableContent(List<WebElement> table, String filename) {
        File file = new File(System.getProperty("user.dir") + "/" + filename + ".txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

            if (!file.exists()) {
                file.createNewFile();
            }

            for (int i = 0; i < table.size(); i++) {
                WebElement element = table.get(i);
                bw.write(element.getText());
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
