package pages.home;

import org.openqa.selenium.remote.RemoteWebDriver;
import framework.CommonPage;

public class SearchResultsPage extends CommonPage {

	public SearchResultsPage(RemoteWebDriver webDriver) {
		super(webDriver);
		waituntilPageLoads();
	}
	
	/************ locators ***************/
	/************ actions ****************/
	/************ accessors **************/
	/************ validations ************/
	public boolean isDisplayedSearchResultsFor(String ProductName) {
		return isPageTitleDisplayed("Search results for: '"+ProductName+"'");
	}
}
