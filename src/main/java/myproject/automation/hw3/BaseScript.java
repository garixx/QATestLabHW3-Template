package myproject.automation.hw3;

import myproject.automation.hw3.utils.WebDriverLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseScript {

    public static WebDriverLogger webDriverLogger;
    public static EventFiringWebDriver driver;
    public static PageActions page;

    /**
     *
     * @return New instance of {@link WebDriver} object. Driver type is based on passed parameters
     * to the automation project, returns {@link ChromeDriver} instance by default.
     */
    public static WebDriver getDriver(String browserName) {
        switch (browserName) {
            case "chrome" :
                // 64-bit driver for 64-bit chrome
                System.setProperty("webdriver.chrome.driver", BaseScript.class.getResource("/chromedriver.exe").getPath());
                return new ChromeDriver();
            case "firefox":
                //64-bit driver for 64-bit firefox
                System.setProperty("webdriver.gecko.driver", BaseScript.class.getResource("/geckodriver.exe").getPath());
                return new FirefoxDriver();
            case "ie":
            case "internetexplorer":
                // 32-bit instance of the IEDriver for 32-bit Internet Explorer. You should have 64-bit driver for 64-bit IE.
                System.setProperty("webdriver.ie.driver", BaseScript.class.getResource("/IEDriverServer.exe").getPath());
                return new InternetExplorerDriver();
        }
        return null;
    }

    /**
     * Creates {@link WebDriver} instance with timeout and browser window configurations.
     *
     * @return New instance of {@link EventFiringWebDriver} object. Driver type is based on passed parameters
     * to the automation project, returns {@link ChromeDriver} instance by default.
     */
    public static EventFiringWebDriver getConfiguredDriver(String browserName) {
        driver = new EventFiringWebDriver(getDriver(browserName));
        webDriverLogger = new WebDriverLogger();
        driver.register(webDriverLogger);

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }
}
