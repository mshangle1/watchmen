package com.ally.demo.steps;

import com.ally.d3.watchmen.steps.CommonApiStepsDefinition;
import com.ally.d3.watchmen.utilities.RequestHelper;
import com.ally.d3.watchmen.utilities.SeleniumHelper;
import com.ally.d3.watchmen.utilities.dataDriven.PlaceholderResolve;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


public class SeleniumStepsDefinition {

    private static final Logger logger = LoggerFactory.getLogger(SeleniumStepsDefinition.class);

    @Autowired
    CommonApiStepsDefinition watchmen;

    @Autowired
    SeleniumHelper seleniumHelper;

    @Autowired
    RequestHelper requestHelper;

    @Value("${outlookURL}")
    private String outlookUrl;


    @Given("^I open Web Page \"([^\"]*)\"$")
    public void openWebPage(String url) {
        logger.info("I open Web Page " + url);

        logger.debug("Check if there are any placeholder for the URL and replace it");
        String newURL = requestHelper.resolveAllPlaceholdersURL(url);

        seleniumHelper.openPage(newURL);

    }

    @And("^I navigate to the element by Id \"([^\"]*)\" and verify Text equals to \"([^\"]*)\"$")
    public void navigateElementByIdTextEquals(String id, String expectedText) {

        logger.info("I navigate to the element " + id + " by Id and check Text equals to " + expectedText);

        String text = seleniumHelper.getElementTextById(id);
        if (text == null)
            Assert.assertTrue("Was NOT able to get text for the element with id: " + id, (false));
        else if (text.equals(expectedText)) {
            Assert.assertTrue("Text for the element with id: " + id + " matches expected", (true));
        } else
            Assert.assertTrue("Text Does not match expected text for the element with id: " + id + ". Actual text is: " + text, (false));
    }

    @And("^I navigate to the element by Id \"([^\"]*)\" and verify Text contains \"([^\"]*)\"$")
    public void navigateElementByIdTextContains(String id, String expectedText) {
        logger.info("I navigate to the element " + id + " by Id and check Text equals to " + expectedText);

        String text = seleniumHelper.getElementTextById(id);
        if (text == null)
            Assert.assertTrue("Was NOT able to get text for the element " + id, (false));
        else if (text.contains(expectedText)) {
            Assert.assertTrue("Text matches expected " + id, (true));
        } else
            Assert.assertTrue("Text Does not contain expected text for the element with id: " + id + ". Actual text is: " + text, (false));
    }

    @And("^I navigate to the element by Xpath \"([^\"]*)\" and verify Text equals to \"([^\"]*)\"$")
    public void navigateElementByXpathTextEquals(String xpath, String expectedText) {
        logger.info("I navigate to the element " + xpath + " by Xpath and check Text equals to " + expectedText);

        String text = seleniumHelper.getElementTextByXpath(xpath);
        if (text == null)
            Assert.assertTrue("Was NOT able to get text for the element with Xpath: " + xpath, (false));
        else if (text.equals(expectedText)) {
            Assert.assertTrue("Text for the element with Xpath: " + xpath + " matches expected", (true));
        } else
            Assert.assertTrue("Text Does not match expected text for the element with Xpath: " + xpath + ". Actual text is: " + text, (false));
    }

    @And("^I navigate to the element by Xpath \"([^\"]*)\" and verify Text contains \"([^\"]*)\"$")
    public void navigateElementByXpathTextContains(String xpath, String expectedText) {
        logger.info("I navigate to the element " + xpath + " by Xpath and check Text equals to " + expectedText);

        String text = seleniumHelper.getElementTextByXpath(xpath);
        if (text == null)
            Assert.assertTrue("Was NOT able to get text for the element " + xpath, (false));
        else if (text.contains(expectedText)) {
            Assert.assertTrue("Text matches expected " + xpath, (true));
        } else
            Assert.assertTrue("Text Does not contain expected text for the element with Xpath: " + xpath + ". Actual text is: " + text, (false));
    }


    @And("^I navigate to the element by Id \"([^\"]*)\" and click on it$")
    public void navigateElementByIdClick(String id) {
        logger.info("I navigate to the element " + id + " by Id and click on it");


        Assert.assertTrue("Was not able to click on the element with id " + id, (seleniumHelper.getElementByIdClick(id)));
    }

    @And("^I navigate to the element by Xpath \"([^\"]*)\" and click on it$")
    public void navigateElementByXpathClick(String xpath) {
        logger.info("I navigate to the element " + xpath + " by Id and click on it");


        Assert.assertTrue("Was not able to click on the element with xpath " + xpath, (seleniumHelper.getElementByXpathClick(xpath)));
    }

    @And("^I navigate to the element by Xpath \"([^\"]*)\" and double click on it$")
    public void navigateElementByXpathDoubleClick(String xpath) {
        logger.info("I navigate to the element " + xpath + " by Id and click on it");


    }
}
