package pages.home;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import framework.CommonPage;

public class InformationLinksPage extends CommonPage {

	public InformationLinksPage(RemoteWebDriver webDriver) {
		super(webDriver);
		waituntilPageLoads();
	}

	/************ locators ***************/

	@FindBy(css = ".page-title")
	WebElement pageTitle_by;

	@FindBy(id = "maincontent")
	WebElement blockContent_by;

	/************ accessors ***************/

	public String getPageInformationContent() {
		return blockContent_by.getText();
	}

	/************ actions ***************/

	/************ validations ***************/

	public boolean isInformationPageDisplayed(String pageTitle) {
		return pageTitle_by.isDisplayed() && pageTitle_by.getText().equals(pageTitle);
	}

	public boolean isDisplayedPageInformation(String content) {
		return getPageInformationContent().contains(content);
	}
}
