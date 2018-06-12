package myproject.automation.hw3;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Contains main script actions that may be used in scripts.
 */

public class PageActions {
    private Actions hover;
    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * String representation of the CSS selectors of the page.
     */
    private static final String emailField = "#email";
    private static final String passwordField = "#passwd";
    private static final String mainMenuCatalogItem = "#subtab-AdminCatalog";
    private static final String submenuCategoriesItem = "#subtab-AdminCategories";
    private static final String alwaysVisibleYESbutton = "#fieldset_0 > div.form-wrapper > div:nth-child(2) > div > span > label:nth-child(2)";
    private static final String categoryName = "#name_1";
    private static final String categoryDescription = "#mce_31";
    private static final String categoryMetaTitle = "#meta_title_1";
    private static final String categoryMetaDescription = "#meta_description_1";
    private static final String categoryTags = "#fieldset_0 > div.form-wrapper > div:nth-child(10) > div > div";
    private static final String addCategoryButton = "#page-header-desc-category-new_category";
    private static final String createNewCategoryButton = "#category_form_submit_btn > i";
    private static final String uploadFilesButton= "#thumbnail-upload-button > span.ladda-label";
    private static final String searchField = "#table-category > thead > tr.nodrag.nodrop.filter.row_hover > th:nth-child(3) > input";


    /**
     * Files with images for the new category
     */
    private static final File iconFile = new File("/src/main/resources/icon.jpg");
    private static final File miniatureFile = new File("/src/main/resources/mini.jpg");
    private static final File menuMiniature = new File("/src/main/resources/menuMini.jpg");

    public PageActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        this.hover = new Actions(driver);
    }

    /**
     * Logs in to Admin Panel.
     * @param login
     * @param password
     */
    public void login(String login, String password) {

        waitForItemPresence(emailField).sendKeys(login);
        waitForItemPresence(passwordField).sendKeys(password);

        driver.findElement(By.name("submitLogin")).submit();
    }

    /**
     * Log out of the admin panel
     */
    public void logout() {
        driver.findElement(By.id("employee_infos")).click();
        driver.findElement(By.id("header_logout")).click();
    }


    /**
     * Adds new category in Admin Panel.
     *
     * @param name New category name.
     * @param description New category description.
     */
    public void createCategory(String name,
                               String description) {

        // Go to the "Категории" page
        hoverToItem(mainMenuCatalogItem);
        waitForItemPresence(submenuCategoriesItem).click();

        // click the "Создать категорию" button
        waitForItemPresence(addCategoryButton).click();

        // Fill the category page fields with the new category information
        fillField(categoryName, name);
        fillField(categoryDescription, description);


        /*
        * Fix for the Firefox issue:
        */
        try {
            hoverToItem(createNewCategoryButton);
            waitForItemPresence(createNewCategoryButton).click();
        }
        /*
         * If WebElement is situated out of the screen then Firefox throws MoveTargetOutOfBoundsException.
         * In this case we perform the the PAGE_DOWN button event and repeat operation with the item.
         */
        catch (MoveTargetOutOfBoundsException e) {
            hover.moveToElement(driver.findElement(By.xpath("//*/label"))).click()
                    // after that we can perform PAGE_DOWN button action
                    .sendKeys(Keys.PAGE_DOWN)
                    .build().perform();
            waitForItemPresence(createNewCategoryButton).click();
        }
        /*
        * End of the fix.
         */
    }


    /**
     * Fill defined field with defined text.
     * @param cssSelector Of the field.
     * @param text The data to enter.
     */
    private void fillField(String cssSelector, String text) {

        hover.moveToElement(driver.findElement(By.cssSelector(cssSelector)))
                .click()
                .sendKeys(text)
                .build()
                .perform();
    }


    /**
     * Wait until the item is available for click.
     *
     * @param selector CSS selector of the item.
     * @return WebElement The clickable item.
     */
    public WebElement waitForItemPresence(String selector) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
    }

    /**
     * Wait for visibility of the item and hover to it without clicking the item.
     *
     * @param selector CSS selector of the item.
     */
    public void hoverToItem(String selector) {

        waitForItemPresence(selector);
        hover.moveToElement(driver.findElement(By.cssSelector(selector))).build().perform();
    }


    /**
     * Check the category for presence after creation.
     *
     * @param categoryName
     * @return True if category created and False if not.
     */
    public boolean checkIsCategoryCreated(String categoryName) {
        // Go to the "Категории" page
        hoverToItem(mainMenuCatalogItem);
        waitForItemPresence(submenuCategoriesItem).click();

        // Enter the category name in the search field.
        hoverToItem(searchField);
        waitForItemPresence(searchField).sendKeys(categoryName + Keys.ENTER);

        // Wait for results page is appeared
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("page-title"), categoryName));

        // collect all of the category names from the table column in list
        List<String> list = driver.findElements(By.xpath("//*[contains(@id,'tr_2_')]/td[3]"))
                .stream()
                // Collect displayed results
                .filter(webElement -> webElement.isDisplayed())
                // get category names
                .map(webElement -> webElement.getText())
                // And make the list of the collected id's
                .collect(Collectors.toList());

        if (list.contains(categoryName)) return true;

        return false;
    }

    /**
     * Wrapper for Thread.sleep() action.
     * @param time
     */
    public static void waitMilliSeconds(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
