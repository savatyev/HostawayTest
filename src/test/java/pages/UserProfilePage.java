package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserProfilePage extends DashboardPage {

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//label[text()='First name']")
    private WebElement firstNameLabel;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameInput;

    @FindBy(id = "isAdmin")
    private WebElement isAdmin;

    @FindBy(id = "receiveReservationNotificationEmail")
    private WebElement receiveReservationNotificationEmail;

    @FindBy(id = "accessContactData")
    private WebElement accessContactData;

    @FindBy(id = "accessFinancialData")
    private WebElement accessFinancialData;

    @FindBy(id = "hasAccessToNewListings")
    private WebElement hasAccessToNewListings;

    @FindBy(id = "readListing")
    private WebElement readListing;

    @FindBy(id = "readReservation")
    private WebElement readReservation;

    @FindBy(id = "readCalendar")
    private WebElement readCalendar;

    @FindBy(xpath = "//*[text()='Listing restriction']/../..//button[contains(text(),'elect all')]")
    private WebElement selectUnSelectAllListingsButton;

    @FindBy(xpath = "//*[text()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//span[text()='Create user']")
    private WebElement confirmCreationButton;

    public UserProfilePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isCurrent() {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 5, 200);
        wait.until(ExpectedConditions.visibilityOf(firstNameLabel));
        return true;
    }

    public UserProfilePage setEmail(String email) {
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    public UserProfilePage setPassword(String password) {
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public UserProfilePage setFirstName(String firstName) {
        firstNameInput.click();
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        return this;
    }

    private void clickCheckboxWithJS(WebElement checkbox) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);", checkbox);
        js.executeScript("arguments[0].click();", checkbox);
    }

    private void setCheckboxValue(WebElement checkbox, boolean value) {
        boolean isChecked = false;
        if (checkbox.getAttribute("value").equals("true")) {
            isChecked = true;
        }
        if (isChecked ^ value) {
            clickCheckboxWithJS(checkbox);
        }
    }

    public UserProfilePage setIsAdmin(boolean value) {
        setCheckboxValue(isAdmin, value);
        return this;
    }

    public UserProfilePage setReceiveReservationNotificationEmail(boolean value) {
        setCheckboxValue(receiveReservationNotificationEmail, value);
        return this;
    }

    public UserProfilePage setAccessContactData(boolean value) {
        setCheckboxValue(accessContactData, value);
        return this;
    }

    public UserProfilePage setAccessFinancialData(boolean value) {
        setCheckboxValue(accessFinancialData, value);
        return this;
    }

    public UserProfilePage setHasAccessToNewListings(boolean value) {
        setCheckboxValue(hasAccessToNewListings, value);
        return this;
    }

    public UserProfilePage setReadListing(boolean value) {
        setCheckboxValue(readListing, value);
        return this;
    }

    public UserProfilePage setReadReservation(boolean value) {
        setCheckboxValue(readReservation, value);
        return this;
    }

    public UserProfilePage setReadCalendar(boolean value) {
        setCheckboxValue(readCalendar, value);
        return this;
    }

    public UserProfilePage unselectAllListings() {
        selectUnSelectAllListingsButton.click();
        if (selectUnSelectAllListingsButton.getText().equals("Unselect all")) {
            selectUnSelectAllListingsButton.click();
        }
        return this;
    }

    public UserProfilePage selectAllListings() {
        selectUnSelectAllListingsButton.click();
        if (selectUnSelectAllListingsButton.getText().equals("Select all")) {
            selectUnSelectAllListingsButton.click();
        }
        return this;
    }

    private List<List<WebElement>> getAllListings() {
        List<WebElement> nameElements = driver.findElements(By.xpath("//div[@class='col-lg-6']//label"));
        List<WebElement> inputElements = driver.findElements(By.xpath("//div[@class='col-lg-6']//input"));
        List<WebElement> statusElements = driver.findElements(By.xpath("//div[@class='col-lg-6']/div"));
        List<List<WebElement>> result = new ArrayList();
        result.add(nameElements);
        result.add(inputElements);
        result.add(statusElements);
        return result;
    }

    public UserProfilePage assignRandomListing() {
        List<List<WebElement>> allListings = getAllListings();
        final Random random = new Random();
        int n = random.nextInt(allListings.get(0).size());

        if (!allListings.get(2).get(n).getAttribute("class").equals("listing-item--selected")) {
            clickCheckboxWithJS(allListings.get(1).get(n));
        }
        return this;
    }

    public UserProfilePage saveUser() {
        saveButton.click();
        return this;
    }

    public UserProfilePage confirmUserCreation() {
        confirmCreationButton.click();
        return this;
    }

}