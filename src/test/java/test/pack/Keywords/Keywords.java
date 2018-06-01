package test.pack.Keywords;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Keywords {
    public static int timeout = 5;
    private String expandBtn;
    private String dDlist;
    private int optNumber;
    private String optName;

    static ChromeDriver driver;
    static WebDriverWait wait;
    public void StartBrowser() {
        System.setProperty("webdriver.chrome.driver", "/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, timeout);

    }


    public void openUrlAndWaitLoad(String URL, String expectedElement){
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(expectedElement))));
    }

    public void MouseOver(String overelem) {

        Actions action = new Actions(driver);
        WebElement we = driver.findElement(By.xpath(overelem));
        action.moveToElement(we).moveToElement(driver.findElement(By.xpath(overelem)));
    }

    public void waitAndClick(String elem) {

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elem)));
        driver.findElement(By.xpath(elem)).click();
    }

    public void selectFromDropdown(String expandBtn, String optName, int i, String pathToList) {

        WebDriverWait wait = new WebDriverWait(driver, timeout);
        // Expand dropdown
        driver.findElement(By.xpath(expandBtn)).click();
        // finding by name
        String optionToClickName = dDlist + pathToList +"/li[@contains(text(), '" + optName + "')]";
        driver.findElement(By.xpath(optionToClickName)).click();
    }

    public void waitNinputText(String textelem, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(textelem))));
        driver.findElement(By.xpath(textelem)).sendKeys(text);
    }

    public void waitNcheckText(String textelem, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(textelem))));
        Assert.assertEquals(String.valueOf(driver.findElement(By.xpath(textelem))), text, textelem + " not equals " + text);
    }

    public void waitForElementToLoad(String myelem) {
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(myelem))));
    }

    public void closeBrowser(){
        driver.quit();
    }
    public void makeScreenshot() {
        String timeStamp = new SimpleDateFormat("MM_dd_yyyy__HH_mm").format(new Date());
        String screenShotDir = "src\\results\\";
        String myScreenShotDir = screenShotDir + timeStamp + "\\";
        String scrnShotName = "test_run_" + timeStamp + ".png";
        File theDir = new File(myScreenShotDir);
        File scrFile = driver.getScreenshotAs(OutputType.FILE);
        // Save file to dir, creating it if nessesary
        if (!theDir.exists()) {
            boolean result = false;
            try {
                theDir.mkdir();
                result = true;
                System.out.println(myScreenShotDir + scrnShotName);
                FileUtils.copyFile(scrFile, new File(myScreenShotDir + scrnShotName));
            } catch (IOException se) {
                se.printStackTrace();
            }
        }
    }
}
