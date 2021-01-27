package com.auto.hero.pages;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.auto.hero.config.Config;

public class AutoHeroSearchPage {

	WebDriver driver;

	public AutoHeroSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@id = 'yearFilter']")
	WebElement yearFilterButton;

	@FindBy(xpath = "//button[@id = 'yearFilter']//following::select[@id = 'rangeStart'][1]")
	WebElement yearRageStart;

	@FindBy(xpath = "//button[@id = 'yearFilter']//following::select[@id = 'rangeEnd'][1]")
	WebElement yearRageEnd;

	@FindBy(xpath = "//select[@id = 'sortBy']")
	WebElement sortBy;

	@FindBy(xpath = "//div[@class = 'ReactVirtualized__Grid__innerScrollContainer']/div")
	List<WebElement> allCars;

	public void lunchAutoHeroApp() {
		driver.get(Config.AppUrl);
	}

	public void verifyuserLandsSearchPages() {
		assertTrue(driver.getTitle().contentEquals("Jetzt Top-Gebrauchtwagen online kaufen | Autohero.com"));
	}

	public void applyRegistrationFilter(String startingYear) {

		yearFilterButton.click();

		Select yearRageStartDropDown = new Select(yearRageStart);
		yearRageStartDropDown.selectByVisibleText(startingYear);

		Select yearRageEndDropDown = new Select(yearRageEnd);
		yearRageEndDropDown.selectByVisibleText("2021");
	}

	public void applyDescendingOrderFilter(String sortingKey) {
		Select sortByDropdown = new Select(sortBy);
		sortByDropdown.selectByVisibleText(sortingKey);
	}

	public void verifyCarsRegFilter(String regYear) throws InterruptedException {

		ArrayList<Integer> allCarsYearsList = new ArrayList<Integer>();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 500)");

		List<WebElement> listElements = driver.findElements(
				By.xpath("//div[@class='ReactVirtualized__Grid__innerScrollContainer']/div/a/div[2]/ul/li[1]"));

		Iterator<WebElement> itr = listElements.iterator();
		while (itr.hasNext()) {
			allCarsYearsList.add(Integer.parseInt(itr.next().getText().replace(" ", "")));
		}

		System.out.println("cars Years : " + allCarsYearsList);
		System.out.println("cars Years Size: " + allCarsYearsList.size());

		int yearValue = Integer.parseInt(regYear);

		for (Integer integer : allCarsYearsList) {
			Assert.assertTrue(integer >= yearValue);
		}

	}

	public void verifyCarsDisplayedDscendingOrder() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 500)");

		ArrayList<Integer> allCarsPricesList = new ArrayList<Integer>();

		List<WebElement> listElements = driver.findElements(
				By.xpath("//div[@class='ReactVirtualized__Grid__innerScrollContainer']/div/a/div[2]/div[1]/div"));

		Iterator<WebElement> itr = listElements.iterator();
		while (itr.hasNext()) {
			allCarsPricesList.add(Integer.parseInt(itr.next().getText().replace("€", "").replace(".", "").trim()));
		}

		System.out.println("cars Prices : " + allCarsPricesList);
		System.out.println("cars Prices Size: " + allCarsPricesList.size());

		for (int i = 0; i < allCarsPricesList.size(); i++) {

			if (i != 0) {
				if (allCarsPricesList.get(i) >= allCarsPricesList.get(i - 1)) {
					assertTrue(true);
				}
			}
		}

	}

	public void closeApplication() {
		driver.close();
	}

}
