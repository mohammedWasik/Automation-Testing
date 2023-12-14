package seleniumFinalTask;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public class BaseTestFinalSelenium {

    protected WebDriver driver;
    protected final String URL = "https://www.wikipedia.org/";

    String Download_Dir = System.getProperty("user.dir") + "/src/test/resources";


    @BeforeMethod
    public void setup() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", Download_Dir);
        prefs.put("savefile.default_directory", Download_Dir);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
