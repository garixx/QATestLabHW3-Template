package myproject.automation.hw3.tests;

import myproject.automation.hw3.BaseScript;
import myproject.automation.hw3.PageActions;
import myproject.automation.hw3.utils.Properties;

import static myproject.automation.hw3.PageActions.waitMilliSeconds;

/**
 * The QATestLab's test automation course #104, lecture #3 homework program.
 * The task is execute the test scenario at the Chrome, IE and Firefox.
 * The scenario is:
 *      1. Enter the training site.
 *      2. Log in the admin panel.
 *      3. Create a new category.
 *      4. Check is a new category is successfully created.
 *      5. Log out the admin panel.
 *      6. Close the browser
 *
 * @author  Isametov Ihor
 * @version 1.0
 * @since   2018-06-10
 */
public class CreateCategoryTest extends BaseScript {

    /**
     * Takes browsers list from command line or set through
     * Run -> Edit Configurations -> Program arguments.
     *
     * If list is empty then run Chrome browser.
     * @param browsersList
     */
    public static void main(String[] browsersList) {

        //if list not empty then execute all browsers from the list.
        if (browsersList.length != 0) {
            for (String browserName:browsersList) {
                executeTestWith(browserName.toLowerCase());
            }
        // if list empty execute only Chrome.
        } else {
            executeTestWith("cHromE".toLowerCase());
        }
    }

    /**
     * Execute test with defined browser.
     * Comment waitMilliSeconds() calls if you don't need visual control of the test.
     *
     * @param browserName
     */
    private static void executeTestWith(String browserName) {

        // open the browser and go to the site page.
        getConfiguredDriver(browserName);
        driver.navigate().to(Properties.getBaseAdminUrl());

        page = new PageActions(driver);

        // Go to the admin page.
        page.login(Properties.getLogin(), Properties.getPassword() );
        waitMilliSeconds(2000);

        // Create the new category with the name and the description.
        page.createCategory("S-Children","_test category");
        waitMilliSeconds(2000);

        // Check for category created
        boolean pageIsCreated = page.checkIsCategoryCreated("S-Children");

        if (pageIsCreated) {
            System.out.println("The category is successfully created");
        } else {
            System.out.println("The category isn't successfully created");
        }


        // log out and shutdown the browser.
        waitMilliSeconds(2000);
        page.logout();

        driver.unregister(webDriverLogger);
        driver.quit();

    }


}
