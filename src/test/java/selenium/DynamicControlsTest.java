
package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.UUID;

public class DynamicControlsTest extends BaseTest {

    private final By DYNAMIC_CONTROL = By.xpath(String.format(PRECISE_TEXT_XPATH, "Dynamic Controls"));
    private final By ENABLE = By.xpath(String.format(PRECISE_TEXT_XPATH, "Enable"));

    String randomText = UUID.randomUUID().toString();
    WebDriverWait wait;



    @Test
    public void dynamicControlsTest() {
        driver.findElement(DYNAMIC_CONTROL).click();
        driver.findElement(ENABLE).click();


        // todo: assert input is enabled
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Input_Field = driver.findElement(By.xpath("//*[@id=\"input-example\"]/input"));
        Assert.assertTrue(isClickable(Input_Field), "Text Field Not clickable");

        // todo: input random generated text
        Input_Field.sendKeys(randomText);

        // todo: assert inputted text
        String Actual_Text = Input_Field.getAttribute("value");
        Assert.assertEquals(Actual_Text,randomText,"No text inputted");

    }

    private boolean isClickable(WebElement Input_Field) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(Input_Field));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
