package pages.home;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import framework.CommonPage;

public class CustomerSignInPage extends CommonPage {
	
	public CustomerSignInPage(RemoteWebDriver webDriver) {
		super(webDriver);
		waituntilPageLoads();
	}

	/************ locators ***************/

	@FindBy(css = ".page-title")
	WebElement pageTitle_by;
	
	@FindBy(id = "email")
	WebElement email_by;
	
	@FindBy(id = "pass")
	WebElement password_by;
	
	@FindBy(id = "send2")
	WebElement signIn_by;

	/************ actions ***************/
	
//	public CustomerSignInPage clickSignIn() {
//		signInButton.click();
//		return new CustomerSignInPage(driver);
//	}
	
	public void setEmail(String email) {
		email_by.sendKeys(email);
	}
	
	public void setPassword(String password) {
		password_by.sendKeys(password);
	}
	
	public HomePage clickSignIn() {
		click(signIn_by);
		return new HomePage(driver);
	}

	/************ validations ***************/
	
	public boolean isCustomerLogInPageDisplayed() {
		return pageTitle_by.isDisplayed() && pageTitle_by.getText().equals("Customer Login");
	}

}
