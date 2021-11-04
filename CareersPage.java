package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;


public class CareersPage extends BasePage {

    //Initialize WebDriver in Constructor
    public CareersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Initialize logger
    private static final Logger logger = LogManager.getLogger(CareersPage.class);

    /*
    All elements which will be used in page FindBy
    */

    @FindBy(css=".positionLink")
    List<WebElement> careersList;

    @FindBy(xpath="//div[3]/div/div[1]/div[1]")
    WebElement oneCareer;


    //Counting and returning the number of elements in careers list
    public int countCareers() {
        int numberOfCareers = careersList.size();
        logger.info("The number of careers found: " + numberOfCareers);
        closeAndSwitchToMainWindow();
        return numberOfCareers;
    }

    //Asserting careers page by title
    public void assertCareersPage() throws InterruptedException {
        switchToNewWindow();
        Assertions.assertEquals("Jobs at BUYME",
                                driver.getTitle(),
                                "Couldn't go to registration page");
        Thread.sleep(2000);
        js.executeScript("arguments[0].scrollIntoView(true);", oneCareer);
    }

}
