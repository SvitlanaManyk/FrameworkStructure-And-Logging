package my.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBoxFrontPage {
	
	final static Logger LOGGER = Logger.getLogger(RedBoxFrontPage.class);

	String url = "http://www.redbox.com/";
	WebDriver driver;
	WebDriverWait wdWait;
	By rowsXpath = By.xpath("//div[@class='row extra-padding ng-scope']");

	public RedBoxFrontPage(WebDriver driver) {
		this.driver = driver;
		wdWait = new WebDriverWait(driver, 5);
	}

	public void openPage() {
		System.out.println("Opening page " + url);
		driver.get(url);
	}

	public void searchQuery(String searchQuery) {
		By searchIcon = new By.ByXPath("//a[@id='searchdigital0_SearchIcon']");
		By searchBar = new By.ByXPath("//input[@id='searchdigital0_SearchBox']");

		System.out.println("Clicking Search button");
		WebElement weSearchIcon = driver.findElement(searchIcon);
		weSearchIcon.click();
		wdWait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));

		System.out.println("Locating search bar");
		WebElement weSearchBar = driver.findElement(searchBar);
		weSearchBar.sendKeys("Batman" + Keys.ENTER);
		try {
			wdWait.until(ExpectedConditions.visibilityOfElementLocated(rowsXpath));
		} catch (Exception e) {
			String message = "Error waiting for row";
			LOGGER.error(message, e);
			Assert.fail(message);
		}
	}

	public void rowsValidator(int rowNumber) {
		List<WebElement> allResultRows = driver.findElements(rowsXpath);

		if (rowNumber <= 0 || rowNumber > allResultRows.size()) {
			String message = String.format(
					"Row number %d does not exist - %d rows found", rowNumber,
					allResultRows.size());
			System.out.println(message);
			Assert.fail(message);
		}

		WebElement weRow = allResultRows.get(rowNumber - 1);

		By articlesXpath = By.xpath("./div/div/a");
		List<WebElement> articleList = weRow.findElements(articlesXpath);
		System.out.println("The size of the row is " + articleList.size());
	}
}
