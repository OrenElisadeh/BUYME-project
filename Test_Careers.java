package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.CareersPage;
import pages.HomePage;
import utils.Utils;

import java.io.IOException;


public class Test_Careers {

    //Initialize driver
    public static String browser;
    public static String baseUrl;
    public static WebDriver driver;

    //Initialize Pages
    public BasePage basepage;
    public HomePage homepage;
    public CareersPage careerspage;

    //Initialize logger
    private static final Logger logger = LogManager.getLogger(Test_Careers.class);


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
        careerspage = new CareersPage(driver);
    }

    /*
    Given: getting to base URL
    When: access to the careers page
    Then: at list one job exists
     */

    @Test
    @Order(1)
    @DisplayName("Verify that at list one job exists in careers page")
    public void test_VerifyCareersPage() throws Exception {
        //Home Page
        logger.info("Begin careers test");
        logger.info("Taking homePage snap Shot");
        Thread.sleep(500);
        Utils.takeSnapShot(driver, System.getProperty("user.dir")
                + Utils.getFullPath("homePage"));

        //Careers Page
        logger.info("Getting into careers page");
        homepage.goToCareersPage();
        careerspage.assertCareersPage();
        logger.info("Taking careersPage snap Shot");
        Thread.sleep(500);
        Utils.takeSnapShot(driver, System.getProperty("user.dir")
                + Utils.getFullPath("careersPage"));
        logger.info("Counting number of careers in page");
        int numberOfCareers = careerspage.countCareers();
        Assertions.assertTrue(numberOfCareers > 0, "No careers found");
    }

    @AfterAll
    public static void tearDown(){
        logger.info("Test finish");
        driver.quit();
    }

}
