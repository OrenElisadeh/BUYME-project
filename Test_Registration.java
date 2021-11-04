package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.HomePage;
import pages.RegistrationPage;
import pages.LoginPage;
import utils.Utils;
import java.io.IOException;


public class Test_Registration {

    //Initialize driver
    public static String browser;
    public static String baseUrl;
    public static WebDriver driver;

    //Initialize Pages
    public BasePage basepage;
    public HomePage homepage;
    public LoginPage loginpage;
    public RegistrationPage registrationpage;

    //Initialize logger
    private static final Logger logger = LogManager.getLogger(Test_Registration.class);


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
    }

    /*
    Given: getting to base URL
    When: access to the registration page
    Then: all fields exists and enable
     */

    @Test
    @Order(1)
    @DisplayName("Testing the registration process")
    public void test_VerifyRegistration() throws Exception {
        //Home Page
        logger.info("Begin registration test");
        logger.info("Taking homePage snap Shot");
        Thread.sleep(500);
        Utils.takeSnapShot(driver, System.getProperty("user.dir")
                + Utils.getFullPath("homePage"));

        //Login Page
        logger.info("Getting into login page");
        homepage.goToLoginPage();
        loginpage.assertLoginPage();
        logger.info("Taking loginPage snap Shot");
        Thread.sleep(500);
        Utils.takeSnapShot(driver, System.getProperty("user.dir")
                + Utils.getFullPath("loginPage"));

        //Registration Page
        logger.info("Getting into registration page");
        loginpage.goToRegistrationPage();
        registrationpage.assertRegistrationPage();
        logger.info("Insert data from properties to registration fields");
        registrationpage.fillDataInFields();
        logger.info("Taking snap shot of the fields with the data for assertion");
        Utils.takeSnapShot(driver, System.getProperty("user.dir")
                       + Utils.getFullPath("registrationPage"));
    }

    @AfterAll
    public static void tearDown(){
        logger.info("Test finish");
        driver.quit();
    }

}
