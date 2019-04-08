package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import utils.PropertyLoader;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected String envUrl;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        try {
            this.envUrl = PropertyLoader.loadProperty("env.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
