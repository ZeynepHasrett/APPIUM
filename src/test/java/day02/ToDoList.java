package day02;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ToDoList {

    AndroidDriver<AndroidElement> driver;

    @BeforeTest
    public void apkYukleme() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 4");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability("appPackage", "todolist.scheduleplanner.dailyplanner.todo.reminders");
        capabilities.setCapability("appActivity", "app.todolist.activity.SplashActivity");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4731/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void test01(){

        // uygulamanin basarili bir sekilde yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled("todolist.scheduleplanner.dailyplanner.todo.reminders"));

        // uygulamanin basarili bir sekilde acildigi dogrulanir
        Assert.assertTrue(driver.findElementByXPath("//*[@text='Welcome to To-do List']").isDisplayed());

        // Ileri butonlarina tiklanir ve pop-up lar kapatilir
        // görev ekleme adimina gecilir
        // görev adi girilir
        // görev kaydedilir
        // görev silinir
        // Görev olusturma sayfasina geri dönüldügü dogrulanir

    }

}
