package tests.myProfile;

import org.testng.annotations.Test;
import baseTestScripts.TestData;
import baseTestScripts.TestNGBaseTest;
import framework.TestDocumentation;
import pages.home.CustomerSignInPage;
import pages.home.HomePage;
import pages.home.MyAccountPage;
import pages.home.SignOutPage;

public class TestMyProfile  extends TestNGBaseTest{

	@TestDocumentation(
			TestNumber = "1",
			Coverage = "Verifies My Account Section from user profile dropdown of home page.",
			CreateDate = "14/07/2021")
	@Test()
	public void testUserProfileMyAccountSection() throws Exception {

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
		
		String newsLetterInformation = "You aren't subscribed to our newsletter.";
		
		logStep("Validate Account Information");
		assertTrue(myAccountPage.isAccountInformationDisplayed(TestData.user_fullname), "User Name is Correct");
		assertTrue(myAccountPage.isAccountInformationDisplayed(TestData.email), "User Email Id is Correct");
		assertTrue(myAccountPage.isAccountInformationDisplayed(newsLetterInformation), "User is not Subscribed to news letter");
		
		logStep("Validate Address Book");
		assertTrue(myAccountPage.isBillingAddressDisplayed("Dhruv Complex Flat No 304"), "Billing Address is Correct");
		assertTrue(myAccountPage.isShippingAddressDisplayed("Pune, Maharashtra, 412308"), "Shipping Address is Correct");
		
		String myOrderSection = "My Orders";
		
		logStep("Go to "+myOrderSection);
		myAccountPage.clickLink(myOrderSection);
		assertTrue(myAccountPage.isPageTitleDisplayed(myOrderSection), myOrderSection+" Section is Displayed");
		assertTrue(myAccountPage.isEmptyMessageDisplayed("You have placed no orders."), "You have placed no orders.");
		
		String myDownLoadableProducts = "My Downloadable Products"; 
		
		logStep("Go to "+myDownLoadableProducts);
		myAccountPage.clickLink(myDownLoadableProducts);
		assertTrue(myAccountPage.isPageTitleDisplayed(myDownLoadableProducts), myDownLoadableProducts+" Section is Displayed");
		assertTrue(myAccountPage.isEmptyMessageDisplayed("You have not purchased any downloadable products yet."), "You have not purchased any downloadable products yet.");
		
		String myWishList = "My Wish List"; 
		
		logStep("Go to "+myWishList);
		myAccountPage.clickLink(myWishList);
		assertTrue(myAccountPage.isPageTitleDisplayed(myWishList), myWishList+" Section is Displayed");
		assertTrue(myAccountPage.isEmptyMessageDisplayed("You have no items in your wish list."), "You have no items in your wish list.");
		
		String storedPaymentMethod = "Stored Payment Methods";
		
		logStep("Go to "+storedPaymentMethod);
		myAccountPage.clickLink(storedPaymentMethod);
		assertTrue(myAccountPage.isPageTitleDisplayed(storedPaymentMethod), storedPaymentMethod+" Section is Displayed");
		assertTrue(myAccountPage.isEmptyMessageDisplayed("You have no stored payment methods."), "You have no stored payment methods.");
		
		String myProductReviews = "My Product Reviews";
		
		logStep("Go to "+myProductReviews);
		myAccountPage.clickLink(myProductReviews);
		assertTrue(myAccountPage.isPageTitleDisplayed(myProductReviews), myProductReviews+" Section is Displayed");
		assertTrue(myAccountPage.isEmptyMessageDisplayed("You have submitted no reviews."), "You have submitted no reviews.");
		
		String newsLetterSubscription = "Newsletter Subscription";
		
		logStep("Go to "+newsLetterSubscription);
		myAccountPage.clickLink("Newsletter Subscriptions");
		assertTrue(myAccountPage.isPageTitleDisplayed(newsLetterSubscription), newsLetterSubscription+" Section is Displayed");
		
		String savedCards = "Saved Cards";
		
		logStep("Go to "+savedCards);
		myAccountPage.clickLink("Saved card");
		assertTrue(myAccountPage.isPageTitleDisplayed(savedCards), savedCards+" Section is Displayed");
		
		logStep("Sign Out");
		SignOutPage signOutPage = homePage.SignOut();
		assertTrue(signOutPage.isSignOutPageDisplayed(), "Sign Out Message is Displayed");
		assertTrue(signOutPage.isDisplayedSignOutInformation(), "Sign Out Information Message is Displayed");
		closeBrowser();
	}
}
