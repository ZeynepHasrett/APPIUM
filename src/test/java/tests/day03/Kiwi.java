package tests.day03;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.KiwiPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class Kiwi {

    AndroidDriver<AndroidElement> driver = Driver.getAndroidDriver();

    KiwiPage kiwiPage = new KiwiPage();

    @Test
    public void kiwi01() throws InterruptedException {

        // uygulamanin yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled(ConfigReader.getProperty("kiwiAppPackage")));

        // uygulamanin basariyla acildigi dogrulanir
        Assert.assertTrue(kiwiPage.continueAsAGuestButton.isDisplayed());

        // misafir olarak devam et'e tiklanir
        kiwiPage.continueAsAGuestButton.click();

        // ardindan gelecek olan 3 adimda da yesil butona basilarak devam edilir

        /*
        TouchAction action = new TouchAction<>(driver);

        for (int i = 0; i < 3; i++) {
            action.press(PointOption.point(550, 2060)).release().perform();
        */

        for (int i = 0; i < 3; i++) {
            ReusableMethods.koordinatTiklamaMethodu(550, 2060, 300);
        }

        // Trip type,one way olarak secilir
        kiwiPage.returnButton.click();
        kiwiPage.oneWayButton.click();

        // kalkis ulkesi secenegine tiklanir ve default olan ulke kaldirilir
        kiwiPage.fromButton.click();
        kiwiPage.deleteButton.click();

        // kalkis yapilacak ulke/sehir girilir ve sec'e tiklanir
        kiwiPage.departureCountryTextbox.click();

        if (driver.isKeyboardShown()){
            driver.getKeyboard().pressKey("Istanbul");
        }else {
            kiwiPage.departureCountryTextbox.sendKeys("Istanbul");
        }

        kiwiPage.addButton.click();
        kiwiPage.chooseButton.click();

        // varis ulkesi secenegine tiklanir ve gidilecek ulke girilir
        kiwiPage.toButton.click();
        kiwiPage.arrivalCountryTextbox.click();

        if (driver.isKeyboardShown()){
            driver.getKeyboard().pressKey("Filipinler");
        }else {
            kiwiPage.arrivalCountryTextbox.sendKeys("Filipinler");
        }

        kiwiPage.addButton.click();
        kiwiPage.chooseButton.click();

        // gidis tarihi 28 Mart olarak secilir ve set date e tiklanir
        kiwiPage.dateButton.click();
        Thread.sleep(2000);

        ReusableMethods.koordinatTiklamaMethodu(810, 1410, 300);

        /*
        TouchAction action = new TouchAction<>(driver);

        action.press(PointOption.point(560, 1490))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(600)))
                .moveTo(PointOption.point(800, 400))
                .release()
                .perform();

        ReusableMethods.koordinatTiklamaMethodu(803, 1400, 500);

        NOT: Ekranda olmayan bir tarih kaydirma yaparak secildi ve 2 Nisan tarihini secti
        */

        Thread.sleep(2000);
        kiwiPage.setDateButton.click();

        // search butonuna tiklanir
        kiwiPage.searchButton.click();

        // en  ucuz ve aktarmasiz filtrelemeleri yapilir
        kiwiPage.popularButton.click();
        kiwiPage.cheapestButton.click();
        kiwiPage.stopsButton.click();
        kiwiPage.nonstopButton.click();

        // gelen bilet fiyati kaydedilir ve kullanicin telefonuna sms olarak gonderilir
        kiwiPage.firstTicketButton.click();

        String price = kiwiPage.price.getText();

        driver.sendSMS("11111222", "Sectiginiz biletin fiyat bilgisi: " + price);
    }

}
