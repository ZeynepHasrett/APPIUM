package tests.day02;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
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

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void test01() throws InterruptedException {

        // uygulamanin basarili bir sekilde yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled("todolist.scheduleplanner.dailyplanner.todo.reminders"));

        // uygulamanin basarili bir sekilde acildigi dogrulanir
        Assert.assertTrue(driver.findElementByXPath("//*[@text='Welcome to To-do List']").isDisplayed());

        // Ileri butonlarina tiklanir ve pop-up lar kapatilir
        driver.findElementById("todolist.scheduleplanner.dailyplanner.todo.reminders:id/honor_continue").click();
        driver.findElementById("todolist.scheduleplanner.dailyplanner.todo.reminders:id/welcome_start").click();
        driver.findElementById("todolist.scheduleplanner.dailyplanner.todo.reminders:id/toolbar_back").click();
        driver.findElementById("todolist.scheduleplanner.dailyplanner.todo.reminders:id/dialog_pro_first_close").click();

        // görev ekleme adimina gecilir
        driver.findElementById("todolist.scheduleplanner.dailyplanner.todo.reminders:id/iv_task_add").click();

        // görev adi girilir
        Thread.sleep(2000);
        driver.findElementById("todolist.scheduleplanner.dailyplanner.todo.reminders:id/task_create_input")
                    .sendKeys("Do Exercise");

        // görev kaydedilir
        driver.findElementById("todolist.scheduleplanner.dailyplanner.todo.reminders:id/task_create_btn").click();

        // görev silinir
        TouchAction action = new TouchAction<>(driver);
        Thread.sleep(2000);

        for (int i = 0; i < 3; i++) {
            action.press(PointOption.point(850, 1200))
                    .release()
                    .perform();
        }

        driver.findElementById("todolist.scheduleplanner.dailyplanner.todo.reminders:id/task_check").click();

        // Görev olusturma sayfasina geri dönüldügü dogrulanir
        Assert.assertTrue(driver.findElementById("todolist.scheduleplanner.dailyplanner.todo.reminders:id/iv_task_add").isDisplayed());
    }

}
