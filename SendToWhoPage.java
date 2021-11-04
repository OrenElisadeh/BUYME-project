package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;
import java.io.IOException;


public class SendToWhoPage extends BasePage {

    //Initialize WebDriver in Constructor
    public SendToWhoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Initialize logger
    private static final Logger logger = LogManager.getLogger(SearchResultPage.class);

    //Initialize page variables
    String nameErrorMassage = "מי הזוכה המאושר? יש להשלים את שם המקבל/ת";
    String blessing = "סוף השנה זו הזדמנות להגיד לך תודה,\n" +
            "על ההשקעה, ההתמדה והאיכפתיות.\n" +
            "שתהיה חופשה מהנה, כבר מתגעגעים❤";

    /*
    All elements which will be used in page FindBy
    */

    @FindBy(xpath="//form/div[1]//div/div/div/div/div[1]")
    WebElement somebodyElseButton;

    @FindBy(xpath="//form/div[2]/div[1]/label/input")
    WebElement giftReceiverName;

    @FindBy(xpath="//label/div/div[1]")
    WebElement whatCelebrating;

    @FindBy(xpath="//div[2]/ul/li[5]")
    WebElement celebratingBirthday;

    @FindBy(xpath="//div[2]/ul/li[2]")
    WebElement celebratingEndYear;

    @FindBy(xpath="//div[2]/div[2]/div")
    WebElement uploadVideoButton;

    @FindBy(xpath="//div[2]/div/div[2]/button")
    WebElement birthdayVideo;

    @FindBy(xpath="//div[2]/button")
    WebElement nextButton;

    @FindBy(xpath="//label/ul/li")
    WebElement receivingNameError;

    @FindBy(xpath="//div[4]/label/textarea")
    WebElement blessingText;


    //Filling parameters in all page fields
    public void fillSendToWhoFields() throws IOException {
        clickElement(somebodyElseButton);
        typeText(giftReceiverName, Utils.getProperty("giftReceivingName"));
        clickElement(whatCelebrating);
        clickElement(celebratingBirthday);
        try{
            clickElement(uploadVideoButton);
            clickElement(birthdayVideo);
        }
        catch(Exception e){logger.info("Video already uploaded");}
        clickElement(nextButton);
    }


    //Popping gift receiving name Error massage
    public void nameErrorMassage(){
        clickElement(whatCelebrating);
        clickElement(celebratingEndYear);
        clickElement(nextButton);
    }

    //Assert error massage and blessing text
    public void assertTexts() throws Exception {
        Assertions.assertEquals(nameErrorMassage, receivingNameError.getText());
        Assertions.assertEquals(blessing, blessingText.getAttribute("value"));
        Utils.takeSnapShot(driver, System.getProperty("user.dir")
                + Utils.getFullPath("fieldsText"));
    }

    //Clear giftReceiverName field
    public void clearNameField(){
        giftReceiverName.clear();
    }

}
