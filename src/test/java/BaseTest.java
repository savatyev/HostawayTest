import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import pages.DashboardPage;
import pages.LoginPage;
import pages.UserManagementPage;
import pages.UserProfilePage;

/**
 * @author Alexander Savatyev
 */
public class BaseTest {
    protected WebDriver driver;
    protected String userDir;
    protected String resourcesDir;

    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected UserManagementPage userManagementPage;
    protected UserProfilePage userProfilePage;

    @BeforeClass
    public void initTestSuite() {
        userDir = System.getProperty("user.dir");
        resourcesDir = userDir + "\\src\\test\\resources";
    }

    @BeforeClass
    @Parameters("browser")
    public void initWebDriverAndPages(String browserName) {
        if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", resourcesDir + "\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", resourcesDir + "\\IEDriverServer.exe");

            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability("requireWindowFocus", true);
            dc.setCapability("ignoreProtectedModeSettings", true);
            dc.setCapability("ie.ensureCleanSession", true);
            dc.setCapability("unexpectedAlertBehaviour", "dismiss");
            dc.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
            dc.setJavascriptEnabled(true);
            driver = new InternetExplorerDriver(dc);
        } else if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", resourcesDir + "\\chromedriver.exe");
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        loginPage = PageFactory.initElements(driver, LoginPage.class);
        dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
        userManagementPage = PageFactory.initElements(driver, UserManagementPage.class);
        userProfilePage = PageFactory.initElements(driver, UserProfilePage.class);
    }

    @AfterClass(alwaysRun = true)
    public void afterMethod() {
        driver.quit();
    }

}