package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='logo-hostaway-expanded']")
    protected WebElement hostawayLogo;

    @FindBy(xpath = "//a[@href='/users-management']")
    protected WebElement userManagementMenu;

    private final String USER_MANAG_URL = envUrl + "users-management";

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isCurrent() {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 5, 200);
        wait.until(ExpectedConditions.visibilityOf(hostawayLogo));
        return true;
    }

    public UserManagementPage navigateToUserManagement() {
        driver.get(USER_MANAG_URL);
        return PageFactory.initElements(driver, UserManagementPage.class);
    }
}
