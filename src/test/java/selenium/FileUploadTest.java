package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;


public class FileUploadTest extends BaseTest {
    private final By FILE_UPLOAD = By.xpath(String.format(PRECISE_TEXT_XPATH, "File Upload"));


    @Test
    public void fileUploadTest() {
        driver.findElement(FILE_UPLOAD).click();
        String FILE_NAME = "fileToUpload.txt";
        String fileToUploadPath = "/home/zer0/Downloads/" + FILE_NAME;
        File fileToUpload = new File(fileToUploadPath);

        WebElement chooseFile = driver.findElement(By.id("file-upload"));
        WebElement uploadBtn = driver.findElement(By.id("file-submit"));

        // todo: upload the file
        chooseFile.sendKeys(fileToUpload.getAbsolutePath());
        uploadBtn.click();

        // todo: assert file is uploaded
        WebElement uploadedFileName = driver.findElement(By.id("uploaded-files"));
        String actualResult = uploadedFileName.getText();

        Assert.assertEquals(actualResult,FILE_NAME,"FIle is not uploaded");


    }
}
