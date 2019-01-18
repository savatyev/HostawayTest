package pages;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public AbstractPage open(String Url) {
        driver.get(Url);
        return this;
    }

    /**
     * Is this a page where the user is currently on or not?
     */
    public abstract boolean isCurrent();

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


}
