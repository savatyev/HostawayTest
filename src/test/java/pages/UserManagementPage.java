package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.ArrayList;

public class UserManagementPage extends DashboardPage {

    @FindBy(xpath = "//a[@href='/users-management/users/add']")
    public WebElement addNewUserBtn;

    @FindBy(xpath = "//*[contains(@class,'btn-danger') and text()='Confirm']")
    public WebElement confirmDeletionButton;

    @FindBy(xpath = "//table[contains(@class, 'listing-mapping-table')]/tbody/tr/td[3]")
    private List<WebElement> allEmailElements;

    public UserManagementPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isCurrent() {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 5, 200);
        wait.until(ExpectedConditions.elementToBeClickable(addNewUserBtn));
        return true;
    }

    private List<WebElement> getUserControls(String userEmail) {
        List<WebElement> userControls = new ArrayList();
        for (WebElement emailEl : allEmailElements) {
            if (emailEl.getText().equals(userEmail)) {
                WebElement viewUserPointer = driver.findElement(By.xpath("//td[text()='" + userEmail + "']/.."));
                WebElement editButton = driver.findElement(By.xpath("//td[text()='" + userEmail + "']/..//*[text()='Edit']/.."));
                WebElement deleteButton = driver.findElement(By.xpath("//td[text()='" + userEmail + "']/..//*[text()='Delete']/.."));
                userControls.add(viewUserPointer);
                userControls.add(editButton);
                userControls.add(deleteButton);
                return userControls;
            }
        }
        return userControls;
    }

    public boolean isUserExist(final String userEmail) {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 10, 200);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                try {
                    getUserControls(userEmail);
                }
                catch(StaleElementReferenceException e) {
                    return false;
                }
                return true;
            }
        });
        List<WebElement> userControls = getUserControls(userEmail);
        if (userControls.size() > 0) {
            return true;
        } else return false;
    }

    public UserManagementPage deleteUserIfExists(String userEmail) {
        List<WebElement> userControls = getUserControls(userEmail);
        if (userControls.size() > 0) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", userControls.get(2));
            userControls.get(2).click();
            confirmDeletionButton.click();
            final Wait<WebDriver> wait = new WebDriverWait(driver, 10, 200);
            wait.until(ExpectedConditions.elementToBeClickable(userManagementMenu));
        }
        return this;
    }

    public UserProfilePage editUser(String userEmail) {
        List<WebElement> userControls = getUserControls(userEmail);
        userControls.get(1).click();
        return PageFactory.initElements(driver, UserProfilePage.class);
    }
}
