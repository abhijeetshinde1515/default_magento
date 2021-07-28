package tests.home;

import org.testng.annotations.Test;
import baseTestScripts.TestData;
import baseTestScripts.TestNGBaseTest;
import framework.TestDocumentation;
import pages.home.CustomerRegisterPage;
import pages.home.CustomerSignInPage;
import pages.home.HomePage;
import pages.home.InformationLinksPage;
import pages.home.MyAccountPage;
import pages.home.MyWishListPage;
import pages.home.SearchResultsPage;
import pages.home.SignOutPage;
import pages.sections.GearSection;
import pages.sections.MenSection;
import pages.sections.SaleSection;
import pages.sections.TrainingSection;
import pages.sections.WhatsNewSection;
import pages.sections.WomenSection;
import utils.GeneratorUtils;

public class TestHomePage extends TestNGBaseTest {

	@TestDocumentation(
			TestNumber = "1",
			Coverage = "Verifies that a authorised client User can log in and see welcome message.", 
			CreateDate = "05/07/2021")
	@Test()
	public void testLogInAuthenticatedUser() throws Exception {

		logStep("Navigate to Magento Website Home Page.");
		HomePage homePage = navigateToMagentoWebsite();
		assertTrue(homePage.validateNotLoggedInUser(), "Default Welcome Message is Displayed.");

		logStep("Go to Customer Log In Page and Validate Authenicated User Log In.");
		CustomerSignInPage customerSignInPage = homePage.clickSignIn();
		assertTrue(customerSignInPage.isCustomerLogInPageDisplayed(), "Customer Log In Page is Displayed");

		logStep("Set username and Password");
		customerSignInPage.setEmail(TestData.email);
		customerSignInPage.setPassword(TestData.password);
		customerSignInPage.clickSignIn();
		assertTrue(homePage.validatLoggedInUser(TestData.user_fullname), "Logged In User Welcome Message is Displayed");
		closeBrowser();
	}
	
	@TestDocumentation(
			TestNumber = "2",
			Coverage = "Verifies that a client User can register with unique email id.", 
			CreateDate = "05/07/2021")
	@Test()
	public void testRegisterUserByUniqueEmailId() throws Exception {
		String firstName = "First";
		String lastName = "Last";
		String email = GeneratorUtils.generateUniqueEmail();
		String password = "password@123";
		String message = "Thank you for registering with Main Website Store.";
		String welcomeName = "First Last";
		
		logStep("Navigate to Magento Website Home Page.");
		HomePage homePage = navigateToMagentoWebsite();
		assertTrue(homePage.validateNotLoggedInUser(), "Default Welcome Message is Displayed");

		logStep("Go to Customer Register Page");
		CustomerRegisterPage customerRegisterPage = homePage.clickCreateAnAccount();
		assertTrue(customerRegisterPage.isCustomerRegisterPageDisplayed(), "Customer Register Page is Displayed");

		logStep("Set required fields");
		customerRegisterPage.setFirstName(firstName);
		customerRegisterPage.setLastName(lastName);
		customerRegisterPage.setEmail(email);
		customerRegisterPage.setPassword(password);
		customerRegisterPage.setConfirmPassword(password);
		
		logStep("Create account");
		MyAccountPage myAccount = customerRegisterPage.clickCreateAccount();
		assertTrue(myAccount.isSuccessMessageDisplayed(message), "Customer Registered successfully!");
		assertTrue(myAccount.isMyAccountPageDisplayed(), "My Account Page is Displayed");
		
		myAccount.clickLumaLogo();
		assertTrue(homePage.validatLoggedInUser(welcomeName), "Logged In User Welcome Message is Displayed");
		closeBrowser();
	}
	
	@TestDocumentation(
			TestNumber = "3",
			Coverage = "Verifies all information Links given at bottom of home page.", 
			CreateDate = "06/07/2021")
	@Test()
	public void testHomePageInformationLinks() throws Exception {

		String abousUsConent = "With more than 230 stores spanning 43 states and growing,"
				+ " Luma is a nationally recognized active wear manufacturer and retailer."
				+ " We’re passionate about active lifestyles – and it goes way beyond apparel.";

		String customerServiceConent = "We hope you love shopping with Luma. Here are our delivery"
				+ " and returns policies to help make sure we meet your expectations.";

		String searchConent = "There are no search terms available.";

		String privacyPolicyConent = "This website (\"website\") is operated by Luma Inc.,"
				+ " which includes Luma stores, and Luma Private Sales. This privacy policy only "
				+ "covers information collected at this website, and does not cover any information "
				+ "collected offline by Luma. All Luma websites are covered by this privacy policy.";

		String contactUs = "We love hearing from you, our Luma customers. Please contact us about anything at all."
				+ " Your latest passion, unique health experience or request for a specific product. "
				+ "We’ll do everything we can to make your Luma experience unforgettable every time. Reach us however you like";
		

		logStep("Navigate to Magento Website Home Page.");
		HomePage homePage = navigateToMagentoWebsite();
		assertTrue(homePage.validateNotLoggedInUser(), "Default Welcome Message is Displayed.");

		InformationLinksPage infoLinks = homePage.clickInfoLinksAs("About us");
		assertTrue(infoLinks.isInformationPageDisplayed("About us"), "About us Page is Displayed.");
		assertTrue(infoLinks.isDisplayedPageInformation(abousUsConent), "Abous us Content is Displayed");
		infoLinks.clickLumaLogo();

		infoLinks = homePage.clickInfoLinksAs("Customer Service");
		assertTrue(infoLinks.isInformationPageDisplayed("Customer Service"), "Customer Service Page is Displayed.");
		assertTrue(infoLinks.isDisplayedPageInformation(customerServiceConent),
				"Customer Service Content is Displayed");
		infoLinks.clickLumaLogo();

		infoLinks = homePage.clickInfoLinksAs("Search Terms");
		assertTrue(infoLinks.isInformationPageDisplayed("Popular Search Terms"), "Search Terms Page is Displayed.");
		assertTrue(infoLinks.isDisplayedPageInformation(searchConent), "Search Content is Displayed");
		infoLinks.clickLumaLogo();

		infoLinks = homePage.clickInfoLinksAs("Privacy and Cookie Policy");
		assertTrue(infoLinks.isInformationPageDisplayed("Privacy Policy"), "Privacy Policy Page is Displayed.");
		assertTrue(infoLinks.isDisplayedPageInformation(privacyPolicyConent), "Privacy Policy Content is Displayed");
		infoLinks.clickLumaLogo();

		infoLinks = homePage.clickInfoLinksAs("Orders and Returns");
		assertTrue(infoLinks.isInformationPageDisplayed("Orders and Returns"), "Orders and Returns Page is Displayed.");
		infoLinks.clickLumaLogo();

		infoLinks = homePage.clickInfoLinksAs("Advanced Search");
		assertTrue(infoLinks.isInformationPageDisplayed("Advanced Search"), "Advanced Search Page is Displayed.");
		infoLinks.clickLumaLogo();

		infoLinks = homePage.clickInfoLinksAs("Contact Us");
		assertTrue(infoLinks.isInformationPageDisplayed("Contact Us"), "Contact Us Page is Displayed.");
		assertTrue(infoLinks.isDisplayedPageInformation(contactUs), "Contact Us Content is Displayed");
		
		infoLinks.clickLumaLogo();
		assertTrue(homePage.validateCopyright(), "Copyrights mark is Displayed");
		closeBrowser();
	}
	
	@TestDocumentation(
			TestNumber = "4",
			Coverage = "Verifies My Account, My Wish List and Sign Out links from user profile dropdown of home page.",
			CreateDate = "06/07/2021")
	@Test()
	public void testUserProfileNavigationLinks() throws Exception {

		logStep("Navigate to Magento Website Home Page.");
		HomePage homePage = navigateToMagentoWebsite();
		assertTrue(homePage.validateNotLoggedInUser(), "Default Welcome Message is Displayed.");

		logStep("Go to Customer Log In Page and Validate Authenicated User Log In.");
		CustomerSignInPage customerSignInPage = homePage.clickSignIn();
		assertTrue(customerSignInPage.isCustomerLogInPageDisplayed(), "Customer Log In Page is Displayed");

		logStep("Set username and Password");
		customerSignInPage.setEmail(TestData.email);
		customerSignInPage.setPassword(TestData.password);
		customerSignInPage.clickSignIn();
		assertTrue(homePage.validatLoggedInUser(TestData.user_fullname), "Logged In User Welcome Message is Displayed");
		
		logStep("Go to My Account");
		MyAccountPage myAccountPage = homePage.GoToMyAccount();
		assertTrue(myAccountPage.isMyAccountPageDisplayed(), "My Account Page is Displayed");
		myAccountPage.clickLumaLogo();
		
		logStep("Go to My Wish List Page");
		MyWishListPage myWishListPage = homePage.GoToMyWishList();
		assertTrue(myWishListPage.isMyWishListPageDisplayed(), "My Wish List Page is Displayed");
		myAccountPage.clickLumaLogo();
		
		logStep("Sign Out");
		SignOutPage signOutPage = homePage.SignOut();
		assertTrue(signOutPage.isSignOutPageDisplayed(), "Sign Out Message is Displayed");
		assertTrue(signOutPage.isDisplayedSignOutInformation(), "Sign Out Information Message is Displayed");
		closeBrowser();
	}
	
	@TestDocumentation(
			TestNumber = "5",
			Coverage = "Verifies email subscription option from home page.", 
			CreateDate = "06/07/2021")
	@Test()
	public void testEmailSubscription() throws Exception {

		logStep("Navigate to Magento Website Home Page.");
		HomePage homePage = navigateToMagentoWebsite();
		assertTrue(homePage.validateNotLoggedInUser(), "Default Welcome Message is Displayed.");

		logStep("Subscrition email without entering email.");
		homePage = homePage.clickEmailSubScribe();
		assertTrue(homePage.validateEmailSubscriptionError(), "Email Required error Message is Displayed");

		logStep("Subscrition email by entering existing email.");
		homePage = homePage.subscribeToEmailAs(TestData.email);
		assertTrue(homePage.isErrorMessageDisplayed("This email address is already subscribed."), "Error Message is Displayed");
		
		logStep("Subscrition email by entering new email.");
		homePage = homePage.subscribeToEmailAs(GeneratorUtils.generateUniqueEmail());
		assertTrue(homePage.isSuccessMessageDisplayed("Thank you for your subscription."), "Success Message is Displayed");
		closeBrowser();
	}
	
	@TestDocumentation(
			TestNumber = "6",
			Coverage = "Verifies shopping category sections from home page.", 
			CreateDate = "09/07/2021")
	@Test()
	public void testHomePageCategoryLinks() throws Exception {

		logStep("Navigate to Magento Website Home Page.");
		HomePage homePage = navigateToMagentoWebsite();
		assertTrue(homePage.validateNotLoggedInUser(), "Default Welcome Message is Displayed.");
		
		String whatsNew = "What's New";
		
		logStep("Validate Whats New Section");
		homePage.clickLink(whatsNew);
		WhatsNewSection whatsNewSection = new WhatsNewSection(driver);
		assertTrue(whatsNewSection.isPageTitleDisplayed(whatsNew), "Page title Displayed Correctly");
		
		String women = "Women";
		
		logStep("Validate Women Section");
		homePage = whatsNewSection.clickHome();
		homePage.clickLink(women);
		WomenSection womenSection = new WomenSection(driver);
		assertTrue(womenSection.isPageTitleDisplayed(women), "Page title Displayed Correctly");
		
		String men = "Men"; 
		
		logStep("Validate Men Section");
		homePage = womenSection.clickHome();
		homePage.clickLink(men);
		MenSection menSection = new MenSection(driver);
		assertTrue(menSection.isPageTitleDisplayed(men), "Page title Displayed Correctly");
		
		String gear = "Gear"; 
		
		logStep("Validate Gear Section");
		homePage = menSection.clickHome();
		homePage.clickLink(gear);
		GearSection gearSection = new GearSection(driver);
		assertTrue(gearSection.isPageTitleDisplayed(gear), "Page title Displayed Correctly");
		
		String training = "Training"; 
		
		logStep("Validate Training Section");
		homePage = gearSection.clickHome();
		homePage.clickLink(training);
		TrainingSection trainingSection = new TrainingSection(driver);
		assertTrue(trainingSection.isPageTitleDisplayed(training), "Page title Displayed Correctly");
		
		String sale = "Sale"; 
		
		logStep("Validate Sale Section");
		homePage = trainingSection.clickHome();
		homePage.clickLink(sale);
		SaleSection saleSection = new SaleSection(driver);
		assertTrue(saleSection.isPageTitleDisplayed(sale), "Page title Displayed Correctly");
		
		saleSection.clickHome();
		closeBrowser();
	}
	
	@TestDocumentation(
			TestNumber = "7",
			Coverage = "Verifies product search functionality from home page.", 
			CreateDate = "13/07/2021")
	@Test()
	public void testSearchProductsFromHomePage() throws Exception {

		logStep("Navigate to Magento Website Home Page.");
		HomePage homePage = navigateToMagentoWebsite();
		assertTrue(homePage.validateNotLoggedInUser(), "Default Welcome Message is Displayed.");

		String productName = "ABC";
		String noticeMessage = "Your search returned no results.";
		
		logStep("Search for a Product as "+productName);
		SearchResultsPage searchResultsPage = homePage.searchFor(productName);
		assertTrue(searchResultsPage.isDisplayedSearchResultsFor(productName), "Search Results is Displayed");
		assertTrue(searchResultsPage.isNoticeMessageDisplayed(noticeMessage), "No Search Results Message is Displayed");
		
		searchResultsPage.clickHome();
		closeBrowser();
	}
}