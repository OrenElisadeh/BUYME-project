package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.CareersPage;
import pages.HomePage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;


public class Test_LoginErrors {

    //Initialize driver
    public static String browser;
    public static String baseUrl;
    public static WebDriver driver;

    //Initialize Pages
    public BasePage basepage;
    public HomePage homepage;
    public CareersPage careerspage;
    public LoginPage loginpage;

    //Initialize logger
    private static final Logger logger = LogManager.getLogger(Test_LoginErrors.class);


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
        loginpage = new LoginPage(driver);
        homepage = new HomePage(driver);
        careerspage = new CareersPage(driver);
    }

    /*
    Given: getting to base URL
    When: trying to login without filling fields
    Then: error massages will appear in all fields
     */

    @Test
    @Order(1)
    @DisplayName("Verify the login fields error massages")
    public void test_VerifyCareersPage() throws Exception {
        //Home Page
        logger.info("Begin login fields error massages test");
        String currentURL = driver.getCurrentUrl();
        logger.info("Taking homePage snap Shot");
        Thread.sleep(500);
        Utils.takeSnapShot(driver, System.getProperty("user.dir")
                + Utils.getFullPath("homePage"));

        //Login Page
        logger.info("Getting into login page");
        homepage.goToLoginPage();
        basepage.assertingPagesByURL("loginPage", currentURL);
        logger.info("Verify the login fields error massages");
        loginpage.clickLoginButton();
        loginpage.assertErrorMassages();
    }

    @AfterAll
    public static void tearDown(){
        logger.info("Test finish");
        driver.quit();
    }

}
