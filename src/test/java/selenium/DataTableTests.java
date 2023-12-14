package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;


public class DataTableTests extends BaseTest {
    private final By SORTABLE_DATA_TABLES = By.xpath(String.format(PRECISE_TEXT_XPATH, "Sortable Data Tables"));
    private final By DUE_PATH = By.xpath("//*[@id=\"table1\"]/tbody/tr/td[4]");
    private final Double EXPECTED_VALUE = 251.0;


    @Test
    public void dataTableTest() {
        driver.findElement(SORTABLE_DATA_TABLES).click();
        List<WebElement> all_dues = driver.findElements(DUE_PATH);
        Double Actual_Result = 0.0;
        for (WebElement element:all_dues){
            Actual_Result+= Double.parseDouble(element.getText().trim().replaceAll("\\$",""));
        }
        Assert.assertEquals(Actual_Result,EXPECTED_VALUE,"There is a defect");
        System.out.println("test passed");
    }
}
