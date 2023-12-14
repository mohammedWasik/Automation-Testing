package selenium;

import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class FileDownloadTest extends BaseTest {
    private final By FILE_DOWNLOAD = By.xpath(String.format(PRECISE_TEXT_XPATH, "File Download"));
    private final String FILE_NAME = "text.txt";
    private final By FILE_NAME_XPATH = By.xpath("//*[@id=\"content\"]/div/a[3]");
    private final String FilePath = RELATIVE_RESOURCE_PATH + FILE_NAME;
    private  final File DownloadedFile = new File("/home/zer0/Downloads/"+FILE_NAME);


    @Test
    public void fileUploadTest() {
        driver.findElement(FILE_DOWNLOAD).click();
        Assert.assertTrue(driver.findElement(FILE_NAME_XPATH).isDisplayed(), "File is not displayed");
        driver.findElement(FILE_NAME_XPATH).click();
        // todo: assert file is downloaded
        Assert.assertTrue(FileChecker(DownloadedFile),"FIle Not Downloaded");
    }

    private boolean FileChecker(File file){
        try {
            Awaitility.await().atMost(5, TimeUnit.SECONDS).until(file::exists);
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        return true;
    }

    // todo: delete file
    @AfterMethod
    private void DeleteFile() {
        if(DownloadedFile.exists()){
            DownloadedFile.delete();
        }

    }



}

