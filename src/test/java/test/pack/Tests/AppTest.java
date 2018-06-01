package test.pack.Tests;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
//
import org.openqa.selenium.chrome.ChromeDriver;
//
import java.io.IOException;
//
import test.pack.Elements.Elements;
import test.pack.Keywords.Keywords;


public class AppTest {
    @BeforeClass

    @Test
    public void searchTest() {
        Keywords keywords = new Keywords();
        Elements elem = new Elements();

        keywords.StartBrowser();
        String testName = "test " + new Object() {
        }.getClass().getEnclosingMethod().getName();


        System.out.println(testName);

        keywords.waitNinputText(elem.SEARCH_INPUT, "Selenium HQ");

        System.out.println("Ending test " + new Object() {
        }.getClass().getEnclosingMethod().getName());
    }


    @After
    public void closeBrowser() throws IOException {
        Keywords keywords = new Keywords();
        keywords.makeScreenshot();
        keywords.closeBrowser();
    }
}