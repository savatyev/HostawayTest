package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInputField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInputField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitLoginBtn;

    private static final String LOGIN_PAGE_URL = "https://dashboard.hostaway.com/login";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isCurrent() {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 5, 200);
        wait.until(ExpectedConditions.visibilityOf(emailInputField));
        return true;
    }

    public LoginPage open() {
        driver.get(LOGIN_PAGE_URL);
        return this;
    }

    public LoginPage setEmail(String email) {
        emailInputField.click();
        emailInputField.clear();
        emailInputField.sendKeys(email);
        return this;
    }

    public LoginPage setPassword(String password) {
        passwordInputField.click();
        passwordInputField.clear();
        passwordInputField.sendKeys(password);
        return this;
    }

    public LoginPage submitLogin() {
        submitLoginBtn.click();
        return this;
    }

}
