// Copyright, Svitlana Manyk 2016
package my.tests;

import my.pages.RedBoxFrontPage;

import org.junit.Test;

import util.PropertiesLoader;
import framework.FrameworkBase;

public class RedBoxTest extends FrameworkBase {

	PropertiesLoader props = new PropertiesLoader();

	@Test
	public void batmanRowCheckingTest() {
		String searchPhrase = "Batman";
		int rowNumber = props.getPropertyAsInt("row.number");

		RedBoxFrontPage frontPage = new RedBoxFrontPage(driver);
		frontPage.openPage();
		frontPage.searchQuery(searchPhrase);
		frontPage.rowsValidator(rowNumber);

	}

}
