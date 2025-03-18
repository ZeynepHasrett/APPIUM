package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class KiwiPage {

    public KiwiPage(){

        PageFactory.initElements((WebDriver) Driver.getAndroidDriver(), this);
    }

    @FindBy(xpath = "(//*[@class='android.widget.Button'])[4]")
    public WebElement continueAsAGuestButton;

    @FindBy(xpath = "//*[@text='Return']")
    public WebElement returnButton;

    @FindBy(xpath = "//*[@text='One way']")
    public WebElement oneWayButton;

    @FindBy(xpath = "//*[@text='From:']")
    public WebElement fromButton;

    @FindBy(xpath = "//*[@content-desc='Clear All']")
    public WebElement deleteButton;

    @FindBy(xpath = "//*[@class='android.widget.EditText']")
    public WebElement departureCountryTextbox;

    @FindBy(xpath = "(//*[@content-desc='Add destination'])[1]")
    public WebElement addButton;

    @FindBy(xpath = "//*[@text='Choose']")
    public WebElement chooseButton;

    @FindBy(xpath = "//*[@text='To:']")
    public WebElement toButton;

    @FindBy(xpath = "//*[@class='android.widget.EditText']")
    public WebElement arrivalCountryTextbox;

    @FindBy(xpath = "//*[@text='Departure:']")
    public WebElement dateButton;

    @FindBy(xpath = "//*[@text='Set date']")
    public WebElement setDateButton;

    @FindBy(xpath = "(//*[@text='Search'])[1]")
    public WebElement searchButton;

    @FindBy(xpath = "//*[@text='Popular']")
    public WebElement popularButton;

    @FindBy(xpath = "//*[@text='Cheapest']")
    public WebElement cheapestButton;

    @FindBy(xpath = "//*[@text='Stops']")
    public WebElement stopsButton;

    @FindBy(xpath = "//*[@text='Nonstop']")
    public WebElement nonstopButton;

    @FindBy(xpath = "//*[@resource-id='com.skypicker.main:id/gradient']")
    public WebElement firstTicketButton;

    @FindBy(xpath = "(//*[@class='android.widget.TextView'])[12]")
    public WebElement price;
}
