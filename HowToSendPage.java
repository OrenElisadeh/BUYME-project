package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.io.IOException;


public class HowToSendPage extends BasePage {

    //Initialize WebDriver in Constructor
    public HowToSendPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    /*
    All elements which will be used in page FindBy
    */

    @FindBy(xpath="//form/div[1]//div/div/div/div/div[1]")
    WebElement nowButton;

    @FindBy(xpath="//form/div[2]/div[3]/div/div[1]")
    WebElement SMSButton;

    @FindBy(id="sms")
    WebElement phoneNumberField;

    @FindBy(xpath="//div[2]/label/input")
    WebElement giftSenderNameField;

    @FindBy(xpath="//div[3]/label/input")
    WebElement giftSenderPhoneField;

    @FindBy(xpath="//div[2]/button")
    WebElement goToPaymentButton;


    //Filling data in all fields on HowToSend page
    public void fillHowToSendFields() throws IOException {
        clickElement(nowButton);
        clickElement(SMSButton);
        typeText(phoneNumberField, Utils.getProperty("giftReceivingPhone"));
        typeText(giftSenderNameField, Utils.getProperty("giftSenderName"));
        typeText(giftSenderPhoneField, Utils.getProperty("giftSenderPhone"));
        clickElement(goToPaymentButton);
        js.executeScript("arguments[0].scrollIntoView(true);", nowButton);
    }

}
