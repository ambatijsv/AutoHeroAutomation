package com.auto.hero.steps;

import org.openqa.selenium.WebDriver;

import com.auto.hero.pages.AutoHeroSearchPage;
import com.auto.hero.setup.SetUp;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AutoHeroSteps {

	WebDriver driver = SetUp.getDriver();
	AutoHeroSearchPage searchPage = new AutoHeroSearchPage(driver);

	@Given("User launches the autohero application")
	public void user_launches_the_autohero_application() {
		searchPage.lunchAutoHeroApp();
	}

	@When("User land on the search page of the application")
	public void user_land_on_the_search_page_of_the_application() {
		searchPage.verifyuserLandsSearchPages();
	}

	@Then("user apply filters for registration statring from (.*) in the dropdown")
	public void user_apply_filters_for_registration_statring_from_the_dropdown(String regStartYear) {
		searchPage.applyRegistrationFilter(regStartYear);
	}

	@Then("User apply sort by price in descending orrder with input as (.*)")
	public void user_apply_sort_by_price_in_descending_orrder(String ascendinDorderText) throws InterruptedException {
		searchPage.applyDescendingOrderFilter(ascendinDorderText);
		Thread.sleep(5000);
	}

	@Then("^Verify all cars are filtered by first registration year from (.*) in the search results$")
	public void verify_all_cars_are_filtered_by_first_registration_year_in_the_search_results(String startYear)
			throws Exception {
		searchPage.verifyCarsRegFilter(startYear);
	}

	@Then("Verify all cars are sorted by price descending")
	public void verify_all_cars_are_sorted_by_price_descending() throws Exception {
		searchPage.verifyCarsDisplayedDscendingOrder();
	}
}
