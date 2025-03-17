package tests.day02;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Arabam {

    AndroidDriver<AndroidElement> driver;

    @BeforeTest
    public void setup() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 4");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability("appPackage", "com.dogan.arabam");
        capabilities.setCapability("appActivity", "com.dogan.arabam.presentation.feature.home.HomeActivity");

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void test01() throws InterruptedException {

        // uygulamanin basarili bir sekilde yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled("com.dogan.arabam"));

        // uygulamanin basarili bir sekilde acildigi dogrulanir
        Assert.assertTrue(driver.findElementById("com.dogan.arabam:id/ivArabamLogo").isDisplayed());

        // alt menuden ilan ara butonuna tiklanir
        driver.findElementByXPath("//*[@text='İlan Ara']").click();

        // kategori olarak otomobil secilir
        driver.findElementByXPath("//*[@text='Otomobil']").click();

        // arac olarak Volkswagen secilir
        Thread.sleep(3500);
        TouchAction touchAction = new TouchAction<>(driver);

        touchAction.press(PointOption.point(500, 1831))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(70)))
                    .moveTo(PointOption.point(500, 409))
                    .release()
                    .perform();
        // Baslangic noktasiyla bitis noktasi arasinda gecen sure (wait action)
        // Eger sure azalirsa gidilen yol mesafesi ARTAR.
        // Eger sure azalirsa gidilen yol mesafesi AZALIR.
        // Yani tamamen bir ters oranti vardir.
        // Ekranda daha fazla asagi ya da yukari gitmek istiyorsak birim zamanda sureyi azaltmaliyiz.

        driver.findElementByXPath("//*[@text='Volkswagen']").click();

        // arac modeli olarak Passat secilir
        driver.findElementByXPath("//*[@text='Passat']").click();

        // 1.4 TSI BlueMotion secilir
        driver.findElementByXPath("//*[@text='1.4 TSi BlueMotion']").click();

        // Paket secimi comfortline yapilir
        driver.findElementByXPath("//*[@text='Comfortline']").click();

        // Ucuzdan pahaliya siralama yapilir
        Thread.sleep(3500);
        touchAction.press(PointOption.point(400, 400))
                    .release()
                    .perform();

        driver.findElementByXPath("//*[@text='Fiyat - Ucuzdan Pahalıya']").click();

        // Gelen en ucuz aracin 500.000 TL' den buyuk oldugu dogrulanir
        String actualPrice = driver.findElementByXPath("(//*[@resource-id='com.dogan.arabam:id/tvPrice'])[1]").getText();
        actualPrice = actualPrice.replaceAll("\\D", "");

        int expectedPrice = 500000;

        Assert.assertTrue(Integer.parseInt(actualPrice) > expectedPrice);
    }

    @Test
    public void test02(){

        // alt menuden ilan ara butonuna tiklanir
        driver.findElementByXPath("//*[@text='İlan Ara']").click();

        // kategori olarak Kiralık Araçlar secilir
        driver.findElementByXPath("//*[@text='Kiralık Araçlar']").click();

        // arac olarak Minivan&Panelvan secilir
        driver.findElementByXPath("//*[@text='Minivan & Panelvan']").click();

        // arac markasi olarak Peugeot secilir
        driver.findElementByXPath("//*[@text='Peugeot']").click();

        // Bipper secilir
        driver.findElementById("com.dogan.arabam:id/textViewBrowseCategory").click();

        // listenin geldigi dogrulanir
        //Assert.assertTrue(driver.findElementByXPath("(//*[@resource-id='com.dogan.arabam:id/tvTitle'])[1]").isDisplayed());

        String ilanSayisi = driver.findElementById("com.dogan.arabam:id/texViewSubtitle").getText();
        ilanSayisi = ilanSayisi.replaceAll("\\D", "");

        Assert.assertTrue(Integer.parseInt(ilanSayisi) > 0);
    }

}
