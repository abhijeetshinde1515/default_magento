package tests.shopping;

import org.testng.annotations.Test;
import baseTestScripts.TestData;
import baseTestScripts.TestNGBaseTest;
import framework.CommonModal;
import framework.TestDocumentation;
import pages.catalogue.CheckOutPage;
import pages.catalogue.ProductDescriptionPage;
import pages.catalogue.ShoppingCartPage;
import pages.home.CustomerSignInPage;
import pages.home.HomePage;
import pages.home.MinicartModal;

public class TestShoppingCart extends TestNGBaseTest {
	
	@TestDocumentation(
			TestNumber = "1", 
			Coverage = "Verifies shopping cart of products.",
			CreateDate = "12/07/2021")
	@Test()
	public void testSecureCheckOutForProducts() throws Exception {

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
		
		logStep("Validate Empty Cart and Close");
		MinicartModal minicartModal = homePage.clickMyCart();
		assertTrue(minicartModal.getEmptyCartText(), "Empty Cart Message is Displayed");
		minicartModal.closeMyCart();
		
		logStep("Choose Product Radiant Tee to see details");
		homePage.clickLink("Radiant Tee");
		ProductDescriptionPage descriptionPage = new ProductDescriptionPage(driver);
		assertTrue(descriptionPage.isProductNameDisplayed("Radiant Tee"), "Product Name Displayed Correctly");
		assertEquals("$22.00", descriptionPage.getItemPrice(), "Price is Displayed");

		logStep("Select Size and Color for product");
		descriptionPage.selectQuantityAs("1");
		descriptionPage.selectSizeOptionAs("S");
		descriptionPage.selectColorOptionAs("Orange");
		descriptionPage.clickAddToCart();
		assertTrue(descriptionPage.isSuccessMessageDisplayed("You added Radiant Tee to your shopping cart."),
				"Success Message is Displayed");
		
		descriptionPage.clickLumaLogo();
		
		logStep("Validate Non-Empty Cart and Close");
		assertEquals("1", minicartModal.getProductNumber(), "Item Quantity is Displayed");
		minicartModal = homePage.clickMyCart();
		assertTrue(minicartModal.getAmountPrice().contains("$22.00"), "Item Prices are Correct");
		assertTrue(minicartModal.getTotalItems().contains("1"), "Total Items are Correct");
		
		logStep("View Shopping Cart");
		ShoppingCartPage shoppingCartPage = minicartModal.clickViewAndEditCart();
		assertTrue(shoppingCartPage.isPageTitleDisplayed("Shopping Cart"), "Page Title is Displayed");
		
		logStep("Proceed To Checkout");
		CheckOutPage checkOutPage = shoppingCartPage.clickProceedToCheckOut();

		homePage = checkOutPage.clickLumaLogo();
		
		logStep("Remove Items from Mini Cart");
		minicartModal = homePage.clickMyCart();
		CommonModal commonModal = minicartModal.clickRemoveItemFromList();
		commonModal.clickOk();
		
		homePage = checkOutPage.clickLumaLogo();
		
		logStep("Validate Empty Cart and Close");
		homePage.refreshPage();
		minicartModal = homePage.clickMyCart();
		assertTrue(minicartModal.getEmptyCartText(), "Empty Cart Message is Displayed");
		minicartModal.closeMyCart();
		closeBrowser();
	}
}