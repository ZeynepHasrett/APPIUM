package tests.day04;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AllCurrencyConverterPage;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.File;
import java.io.IOException;

public class AllCurrencyConverter {

    AndroidDriver<AndroidElement> driver = Driver.getAndroidDriver();

    AllCurrencyConverterPage allCurrencyConverterPage = new AllCurrencyConverterPage();

    @Test
    public void allCurrencyConverter() throws InterruptedException, IOException {

        // all currency uygulamasinin yuklendigi dogulanir
        Assert.assertTrue(driver.isAppInstalled("com.smartwho.SmartAllCurrencyConverter"));

        // uygulamanin acildigi dogrulanir
        allCurrencyConverterPage.acilisSayfasiYazisi.isDisplayed();

        // cevirmek istedigimiz para birimi Türk Lirasi olarak secilir
        ReusableMethods.koordinatTiklamaMethodu(350, 450, 500);
        Thread.sleep(2000);
        ReusableMethods.scrollWithUiScrollableAndClick("TRY");
        Thread.sleep(2000);

        // cevrilecek tutar tuslanir
        allCurrencyConverterPage.birTusu.click();
        allCurrencyConverterPage.ucSifirTusu.click();

        // cevrilecek olan para birimi Bangladesh para birimi olarak secilir
        ReusableMethods.koordinatTiklamaMethodu(350, 650, 500);
        Thread.sleep(2000);
        ReusableMethods.scrollWithUiScrollableAndClick("BDT");
        Thread.sleep(2000);

        // cevrilen tutar screenShot olarak kaydedilir
        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("screenshot.jpg"));

        ReusableMethods.getScreenshot("Screenshot");

        // Ardindan Türk lirasinin Bangladesh Takasi karsiligi olan degeri kaydedilir
        String sonuc = allCurrencyConverterPage.sonucYazisi.getText();

        // kullaniciya sms olarak bildirilir
        driver.sendSMS("11111222", "1000 Turk lirasinin Banglades takasi karsiligi: " + sonuc);
    }

}
