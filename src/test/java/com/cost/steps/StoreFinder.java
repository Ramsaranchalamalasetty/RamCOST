package com.cost.steps;

import com.cost.factory.Pages;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class StoreFinder {


    @Given("^I navigate to store finder$")
    public void i_navigate_to_store_finder() {
        Pages.storeFinderPage().navigateToStoreFinder();
    }

    @When("^I search for store by entering \"([^\"]*)\"$")
    public void i_search_for_store_by_entering(String city) {
        Pages.storeFinderPage().searchlocation(city);
    }

    @Then("^I should get results \"([^\"]*)\" related to search$")
    public void i_should_get_results_related_to_search(String cityResult) {
        assertTrue(Pages.storeFinderPage().searchResults(cityResult));
    }

    @When("^I search for stores by locate me$")
    public void i_search_for_stores_by_locate_me() {
        Pages.storeFinderPage().locateMe();
    }

    @Then("^I should get list of nearest stores$")
    public void i_should_get_list_of_nearest_stores() {
        Pages.storeFinderPage().nearestStoresResultsList();
    }
}
