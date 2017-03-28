package com.test.glue;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.test.framework.ParentScenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StepDefinitions extends ParentScenario {

    private JsonArray boxOfficeData;

    @After
    public void afterScenario() {
        closeBrowser();
    }

    @Before
    public void beforeScenario() {
        startBrowser();
    }

    @Given("^I am on the 2017 Yearly Box Office page$")
    public void goToBoxOfficePage() {
        navigateToBoxOfficePage(2017);
    }


    @When("^I have a structure to store the box office data$")
    public void storeBoxOfficeDataFromWebTable() {
        boxOfficeData = ybo.convertWebTableDataToJson();
    }

    /*
    @Then("^I should see \"([^\"]*)\" download link on Download page$")
    public void I_should_see_download_link_on_download_page(String linkText) {
        assertThat(downloadPage.hasDownloadLinkFor(linkText), is(true));
    }
    */

}