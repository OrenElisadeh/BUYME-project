package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Utils;

import java.io.IOException;


public class Test_PageBottom {

    //Initialize driver
    public static String browser;
    public static String baseUrl;
    public static WebDriver driver;

    //Initialize Pages
    public BasePage basepage;
    public HomePage homepage;
    public LoginPage loginpage;
    public RegistrationPage registrationpage;
    public SearchResultPage searchresultpage;
    public SupplierPage supplierpage;
    public SendToWhoPage sendtowhopage;
    public HowToSendPage howtosendpage;

    //Initialize logger
    private static final Logger logger = LogManager.getLogger(Test_PageBottom.class);


    @BeforeAll
    public static void setup() throws IOException {
        browser = Utils.getProperty("browser");
        baseUrl = Utils.getProperty("baseurl");
        driver = Utils.getDriver(browser, baseUrl);
        PropertyConfigurator.configure(System.getProperty("user.dir") + "/log4j.properties");
    }

    @BeforeEach
    public void testSetup(){
        logger.info("Setting up the test");
        basepage = new BasePage(driver);
        homepage = new HomePage(driver);
        loginpage = new LoginPage(driver);
        registrationpage = new RegistrationPage(driver);
        searchresultpage = new SearchResultPage(driver);
        supplierpage = new SupplierPage(driver);
        sendtowhopage = new SendToWhoPage(driver);
        howtosendpage = new HowToSendPage(driver);
    }

    /*
    Given: getting to base URL
    When: access to the supplier page and scroll to the bottom of the page
    Then: taking snap shot successfully
     */

    @Test
    @Order(1)
    @DisplayName("Taking bottom of the supplier page snap shot")
    public void test_SendingGift() throws Exception {
        //Home Page
        logger.info("Begin send gift test");
        String currentURL = driver.getCurrentUrl();
        logger.info("Taking homePage snap Shot");
        Utils.takeSnapShot(driver, System.getProperty("user.dir")
                + Utils.getFullPath("homePage"));
        logger.info("Login to the system");
        homepage.goToLoginPage();
        loginpage.loginToSystem();
        logger.info("Asserting login");
        loginpage.assertLogin();
        logger.info("Choosing search filters");
        homepage.selectSearchFilters();

        //Search Result Page
        basepage.assertingPagesByURL("searchResultPage", currentURL);
        currentURL = driver.getCurrentUrl();
        logger.info("Choosing gift from results");
        searchresultpage.choosingGift();

        //Supplier Page
        basepage.assertingPagesByURL("supplierPage", currentURL);
        logger.info("Taking supplier page bottom snap shot and asserting page bottom");
        supplierpage.assertBottomPage();
    }

    @AfterAll
    public static void tearDown(){
        logger.info("Test finish");
        driver.quit();
    }

}
