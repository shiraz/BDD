package com.test.glue;

import com.google.gson.JsonArray;
import com.test.framework.ParentScenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

/**
 * <p>
 * The Step definitions class.
 * </p>
 */
public class StepDefinitions extends ParentScenario {

    /**
     * Declare test variables.
     */
    private JsonArray boxOfficeData2016, boxOfficeData2017;
    private JsonArray universalBO2016, universalBO2017;

    @After
    public void afterScenario() {
        closeBrowser();
    }

    @Before
    public void beforeScenario() {
        startBrowser();
    }

    @Given("^I am on the (\\d+) Yearly Box Office page$")
    public void goTo2017BoxOfficePage(int year) {
        navigateToBoxOfficePage(year);
    }

    @Then("^I should store all (\\d+) box office data pertaining to the Universal brand$")
    public void storeAllUniversalMoviesBoxOfficeData(int year) {
        if(year == 2017) universalBO2017 = ybo.getBoxOfficeDataOfUniversal(boxOfficeData2017);
        if (year == 2016) universalBO2016 = ybo.getBoxOfficeDataOfUniversal(boxOfficeData2016);
    }

    @When("^I have a structure to store the (\\d+) box office data$")
    public void storeBoxOfficeDataFromWebTable(int year) {
        if(year == 2017) boxOfficeData2017 = ybo.convertWebTableDataToJson();
        if(year == 2016) boxOfficeData2016 = ybo.convertWebTableDataToJson();
    }

    @Then("^I should verify that all the (\\d+) Universal movies have ([^\"]*) earnings of over \\$([0-9\\.]+) million each$")
    public void verifyUniversalBoxOfficeEarnings(int year, String boxOfficeType, double expectedValue) {
        // Get the correct box office data structure.
        JsonArray universalBoxOffice = null;
        if(year == 2016) universalBoxOffice = universalBO2016;
        if (year == 2017) universalBoxOffice = universalBO2017;
        // For all the Universal movies, verify the earnings.
        universalBoxOffice.forEach(x -> {
            // Get the correct earnings value.
            double earnings = 0;
            if(boxOfficeType.equalsIgnoreCase(ybo.DOMESTIC)) {
                earnings = Double.parseDouble(x.getAsJsonObject().get(ybo.DOMESTIC)
                        .getAsString().replace("$", ""));
            }
            if(boxOfficeType.equalsIgnoreCase(ybo.WORLDWIDE)) {
                earnings = Double.parseDouble(x.getAsJsonObject().get(ybo.WORLDWIDE)
                        .getAsString().replace("$", ""));
            }
            // Verify that they are above the expected value.
            Assert.assertTrue(earnings >= expectedValue, "FAILED. The expected value is more than the actual value. "
                            + "The expected value is $" + expectedValue + ", and the actual value is $" + earnings);
        });
    }
}