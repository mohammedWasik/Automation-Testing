package seleniumFinalTask;

import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import java.io.File;
import java.util.concurrent.TimeUnit;

public class WikipediaTestFinalSelenium extends BaseTestFinalSelenium {
    private final String Search_Input_Field_ID = "searchInput";
    private final String Tools_button_id = "vector-page-tools-dropdown-checkbox";
    private final String Download_button_id = "coll-download-as-rl";
    private final String Input_Text = "Albert Einstein";
    private final By Heading_xpath = By.xpath("//*[contains(@class, 'mw-page-title-main') and text()='" + Input_Text + "']");
    private final By Download_heading_xpath = By.xpath("//*[contains(@id,\"firstHeading\") and text()=\"Download as PDF\"]");
    private final By PDF_name_xpath = By.xpath("//div[@class='mw-electronpdfservice-selection-label-desc']");
    private  final By Download_PDF_xpath = By.xpath("//button[@type='submit']");
    File downloaded_file;

    private  final By Home_page_xpath = By.xpath("//*[@id='www-wikipedia-org']");

    @Test (invocationCount = 1)
    public void wikipediaTest() {
        //1.main page is displayed check
        Assert.assertTrue(driver.findElement(Home_page_xpath).isDisplayed(), "Home Page Not Found");

        //2.typing and then clicking enter from the search field
        driver.findElement(By.id(Search_Input_Field_ID)).sendKeys(Input_Text, Keys.RETURN);

        Assert.assertTrue(driver.findElement(Heading_xpath).isDisplayed(), "Search Page Not Found");

        //3. tool and download as pdf
        driver.findElement(By.id(Tools_button_id)).click();
        driver.findElement(By.id(Download_button_id)).click();

        Assert.assertTrue(driver.findElement(Download_heading_xpath).isDisplayed(), "Download Page Not Found");

        String pdf_name = driver.findElement(PDF_name_xpath).getText();

        //4.download pdf
        driver.findElement(Download_PDF_xpath).click();

        //5.check if file is downloaded
        downloaded_file = new File(Download_Dir+"/"+pdf_name);
        Assert.assertTrue(File_Is_Downloaded(downloaded_file),"File Not Downloaded");
    }

    private boolean File_Is_Downloaded(File file) {
        try {
            Awaitility.await().atMost(15, TimeUnit.SECONDS).until(file::exists);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

//    delete in case of multiple run to not have no download issue
    @AfterMethod
    private void DeleteFile() {
        if(downloaded_file.exists()){
            downloaded_file.delete();
        }

    }
}
