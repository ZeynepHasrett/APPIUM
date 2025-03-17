package tests.day01;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HesapMakinesi {

    AndroidDriver<AndroidElement> driver;
    // Android Driver, Android cihazlar icin uretilmis ve ona gore ozellikleri eklenmis driver
    // AppiumDriver<MobileElement> appiumDriver;
    // Onceden tek bir driver vardi. O da appiumDriver idi. Zamanla Appium kendini gelistirdi.
    // Android icin ayri, ios icin ayri ozellikleri bulunan driverlar uretti.
    // IOSDriver<IOSElement> iosDriver;

    @Test
    public void hesapMakinesi() throws MalformedURLException {

        // kullanici gerekli kurulumlari yapar
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 4");
        //capabilities.setCapability("deviceName", "Pixel 4");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\zeyne\\IdeaProjects\\Appium\\apps\\Calculator_8.4 (503542421)_Apkpure (3).apk");
        //capabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\zeyne\\IdeaProjects\\Appium\\apps\\Apk Bilgisi_2.3.4_apkcombo.com.apk");

        // APP capability bir uygulama eger yuklu degilse uygulamayi cihaza yuklemek icin kullanilir.
        // Eger uygulama yukluyse ve tekrardan test calistirilirsa App capability uygulama yuklu mu diye kontrol eder.
        // Eger yuklu degilse uygulamayi yukler, eger yukluyse uygulamayi acar.

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // uygulamanin yuklendigini dogrular(isInstalled)
        Assert.assertTrue(driver.isAppInstalled("com.google.android.calculator"));

        // uygulamanin acildigini dogrular
        Assert.assertTrue(driver.findElementById("com.google.android.calculator:id/clr").isDisplayed());

        // 400 un 3 katinin 1200 oldugunu hesap makinasindan dogrular
        driver.findElementByAccessibilityId("4").click();
        driver.findElementByAccessibilityId("0").click();
        driver.findElementByAccessibilityId("0").click();
        driver.findElementByAccessibilityId("multiply").click();
        driver.findElementByAccessibilityId("3").click();

        String actualIslemSonucu = driver.findElementById("com.google.android.calculator:id/result_preview").getText();

        int expectedIslemSonucu = 1200;

        Assert.assertEquals(Integer.parseInt(actualIslemSonucu), expectedIslemSonucu);

    }

}
