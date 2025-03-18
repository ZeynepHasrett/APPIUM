package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class AllCurrencyConverterPage {

    public AllCurrencyConverterPage() {

        PageFactory.initElements((WebDriver) Driver.getAndroidDriver(), this);
    }

    @FindBy(xpath = "//*[@text='CURRENCY CONVERTER']")
    public WebElement acilisSayfasiYazisi;

    @FindBy(xpath = "//*[@text='1']")
    public WebElement birTusu;

    @FindBy(xpath = "//*[@text='000']")
    public WebElement ucSifirTusu;

    @FindBy(xpath = "(//*[@class='android.widget.TextView'])[10]")
    public WebElement sonucYazisi;

}
