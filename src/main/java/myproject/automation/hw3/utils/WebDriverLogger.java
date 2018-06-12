package myproject.automation.hw3.utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logger implementation for logging WebDriver events of the test execution.
 * Implement methods you need for your tasks.
 */
public class WebDriverLogger implements WebDriverEventListener {

    private static final Logger LOG = LoggerFactory
            .getLogger(WebDriverLogger.class);

    @Override
    public void beforeAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        LOG.debug("Should navigate to '" + url + "'");
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        LOG.debug("WebDriver navigated to '" + url + "'");
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {

    }

    @Override
    public void afterNavigateBack(WebDriver driver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {

    }

    @Override
    public void afterNavigateForward(WebDriver driver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        LOG.debug("Should be " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        LOG.debug("Element found " + element);
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        LOG.debug("Should click " + element);
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        LOG.debug("Clicked successfull " + element);
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        LOG.debug("Should be changed " + webElement);
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        LOG.debug("Is changed " + webElement);
    }

    @Override
    public void beforeScript(String script, WebDriver driver) {

    }

    @Override
    public void afterScript(String script, WebDriver driver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {

    }
}
