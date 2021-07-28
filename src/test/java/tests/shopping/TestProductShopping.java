package tests.shopping;

import org.testng.annotations.Test;
import baseTestScripts.TestData;
import baseTestScripts.TestNGBaseTest;
import framework.TestDocumentation;
import pages.catalogue.CompareProductListPage;
import pages.catalogue.NewLumaYogaCollection;
import pages.catalogue.ProductDescriptionPage;
import pages.catalogue.ProductSorterFunction;
import pages.home.CustomerSignInPage;
import pages.home.HomePage;
import pages.home.MyWishListPage;
import utils.GeneratorUtils;

public class TestProductShopping extends TestNGBaseTest {

	@TestDocumentation(TestNumber = "1", Coverage = "Verifies user can add product to card by selecting size, color and quantity.", CreateDate = "06/07/2021")
	@Test()
	public void testAddProductToCart() throws Exception {

		logStep("Navigate to Magento Website Home Page.");
		HomePage homePage = navigateToMagentoWebsite();
		assertTrue(homePage.validateNotLoggedInUser(), "Default Welcome Message is Displayed.");

		logStep("Choose Product Radiant Tee to see details");
		homePage.clickLink("Radiant Tee");
		ProductDescriptionPage descriptionPage = new ProductDescriptionPage(driver);
		assertTrue(descriptionPage.isProductNameDisplayed("Radiant Tee"), "Product Name Displayed Correctly");
		assertEquals("$22.00", descriptionPage.getItemPrice(), "Price is Displayed");

		logStep("Select Size and Color for product");
		descriptionPage.selectQuantityAs("3");
		descriptionPage.selectSizeOptionAs("L");
		descriptionPage.selectColorOptionAs("Orange");
		descriptionPage.clickAddToCart();
		assertTrue(descriptionPage.isSuccessMessageDisplayed("You added Radiant Tee to your shopping cart."),
				"Success Message is Displayed");
		closeBrowser();
	}

	@TestDocumentation(
			TestNumber = "2", 
			Coverage = "Verifies user can add or remove product from wishlist.", 
			CreateDate = "06/07/2021")
	@Test()
	public void testAddRemoveProductFromWishList() throws Exception {

		logStep("Navigate to Magento Website Home Page.");
		HomePage homePage = navigateToMagentoWebsite();
		assertTrue(homePage.validateNotLoggedInUser(), "Default Welcome Message is Displayed.");

		logStep("Choose Product Radiant Tee to see details");
		homePage.clickLink("Argus All-Weather Tank");
		ProductDescriptionPage descriptionPage = new ProductDescriptionPage(driver);
		assertTrue(descriptionPage.isProductNameDisplayed("Argus All-Weather Tank"),
				"Product Name Displayed Correctly");

		logStep("Add Product to Wish List");
		descriptionPage.clickAddToWishList();
		CustomerSignInPage customerSignInPage = new CustomerSignInPage(driver);
		assertTrue(
				customerSignInPage.isErrorMessageDisplayed("You must login or register to add items to your wishlist."),
				"Error Message is Displayed");

		logStep("Set username and Password");
		customerSignInPage.setEmail(TestData.email);
		customerSignInPage.setPassword(TestData.password);
		customerSignInPage.clickSignIn();
		assertTrue(homePage.validatLoggedInUser(TestData.user_fullname), "Logged In User Welcome Message is Displayed");

		MyWishListPage myWishListPage = new MyWishListPage(driver);
		assertTrue(myWishListPage.isSuccessMessageDisplayed("Argus All-Weather Tank has been added to your Wish List. Click here to continue shopping."),
				"Product Added to Wish List Successfully");

		myWishListPage.removeItemFromWishList();
		assertTrue(myWishListPage.isSuccessMessageDisplayed("Argus All-Weather Tank has been removed from your Wish List."),
				"Product Removed from Wish List Successfully");
		closeBrowser();
	}

	@TestDocumentation(TestNumber = "3", 
			Coverage = "Verifies user can add product to compare.", 
			CreateDate = "08/07/2021")
	@Test()
	public void testAddProductToCompare() throws Exception {

		logStep("Navigate to Magento Website Home Page.");
		HomePage homePage = navigateToMagentoWebsite();
		assertTrue(homePage.validateNotLoggedInUser(), "Default Welcome Message is Displayed.");

		logStep("Choose Product Breathe-Easy Tank to see details");
		homePage.clickLink("Breathe-Easy Tank");
		ProductDescriptionPage descriptionPage = new ProductDescriptionPage(driver);
		assertTrue(descriptionPage.isProductNameDisplayed("Breathe-Easy Tank"), "Product Name Displayed Correctly");

		logStep("Add Product to Compare List");
		descriptionPage.clickAddToCompare();
		assertTrue(descriptionPage.isSuccessMessageDisplayed(
				"You added product Breathe-Easy Tank to the comparison list."), "Success Message is Displayed");

		logStep("Choose Product Hero Hoodie to see details");
		homePage = descriptionPage.clickHome();
		homePage.clickLink("Hero Hoodie");
		descriptionPage = new ProductDescriptionPage(driver);
		assertTrue(descriptionPage.isProductNameDisplayed("Hero Hoodie"), "Product Name Displayed Correctly");

		logStep("Add Product to Compare List");
		descriptionPage.clickAddToCompare();
		assertTrue(descriptionPage.isSuccessMessageDisplayed("You added product Hero Hoodie to the comparison list."),
				"Success Message is Displayed");

		logStep("Go To Compare Product Page");
		CompareProductListPage compareProductListPage = descriptionPage.clickCompareProducts();
		assertTrue(compareProductListPage.isProductCompareListPageDisplayed(), "Compare Product Page is Displayed");
		
		String heroHoodie = "Gray and black color blocking sets you apart as the Hero Hoodie keeps you warm on the bus, "
				+ "campus or cold mean streets. Slanted outsize front pockets keep your style real . . . convenient."; 
		
		String breatheEasyTank = "The Breathe Easy Tank is so soft, lightweight, and comfortable, you won't even know it's there"
				+ " -- until its high-tech Cocona® fabric starts wicking sweat away from your body to help you stay dry and focused. Layer it over your favorite sports bra and get moving.";
		
		String productName1 = "Hero Hoodie";
		String productName2 = "Breathe-Easy Tank";
		String productPrice1 =  "$54.00";
		String productPrice2 =  "$34.00";
		
		assertTrue(compareProductListPage.getDesription(heroHoodie), "Comparison Details shown correctly for Hero Hoodie");
		assertTrue(compareProductListPage.getDesription(breatheEasyTank), "Comparison Details shown correctly for Breathe Easy Tank");
		assertTrue(compareProductListPage.getDesription(productName1), "Comparison name shown correctly for Hero Hoodie");
		assertTrue(compareProductListPage.getDesription(productName2), "Comparison name shown correctly for Breathe Easy Tank");
		assertTrue(compareProductListPage.getDesription(productPrice1), "Comparison price shown correctly for Hero Hoodie");
		assertTrue(compareProductListPage.getDesription(productPrice2), "Comparison price shown correctly for Breathe Easy Tank");
		
		compareProductListPage.clickLumaLogo();
		closeBrowser();
	}
	
	@TestDocumentation(TestNumber = "4", 
			Coverage = "Verifies product details, more information, reviews section and submit a review.", 
			CreateDate = "09/07/2021")
	@Test()
	public void testProductDetailsMoreInformationAndReviews() throws Exception {

		logStep("Navigate to Magento Website Home Page.");
		HomePage homePage = navigateToMagentoWebsite();
		assertTrue(homePage.validateNotLoggedInUser(), "Default Welcome Message is Displayed.");

		logStep("Choose Product Breathe-Easy Tank to see details");
		homePage.clickLink("Fusion Backpack");
		ProductDescriptionPage descriptionPage = new ProductDescriptionPage(driver);
		assertTrue(descriptionPage.isProductNameDisplayed("Fusion Backpack"), "Product Name Displayed Correctly");
		
		logStep("Validate Product Details");
		descriptionPage.clickLink("Details");
		
		String details =  "With the Fusion Backpack strapped on, every trek is an adventure - even a bus ride to work. "
				+ "That's partly because two large zippered compartments store everything you need, "
				+ "while a front zippered pocket and side mesh pouches are perfect for stashing those little extras, "
				+ "in case you change your mind and take the day off.";
		
		assertTrue(descriptionPage.getItemDetails().contains(details), "Product Details shown correctly for Fusion Backpack");
		
		logStep("Validate More Information");
		descriptionPage.clickLink("More Information");
		
		String moreInformation = "Backpack, Laptop";
		assertTrue(descriptionPage.getMoreInformation().contains(moreInformation), "Product More Information shown correctly for Fusion Backpack");
		
		logStep("Validate Reviews");
		descriptionPage.clickReviews();
		
		String customerReviews = "Customer Reviews";
		assertTrue(descriptionPage.getReviews().contains(customerReviews), "Product Review Section shown correctly for Fusion Backpack");
	
		logStep("Submit a Review");
		assertTrue(descriptionPage.validateReviewingProductNameAs("Fusion Backpack"), "You are Reviewing Fusion Backpack");
		
		descriptionPage.setRating(4);
		descriptionPage.setNickName(GeneratorUtils.generateUniqueId("Nickname"));
		descriptionPage.setSummary(GeneratorUtils.generateUniqueId("Summary"));
		descriptionPage.setReview(GeneratorUtils.generateUniqueId("Review"));
		descriptionPage.clickSubmitReview();

		assertTrue(descriptionPage.isSuccessMessageDisplayed("You submitted your review for moderation."), "Review Submitted for Review Successfully");
		
		descriptionPage.clickHome();
		closeBrowser();
	}
	
	@TestDocumentation(
			TestNumber = "5",
			Coverage = "Verifies New Luma Yoga Collection Sorting Methods from home page.",
			CreateDate = "14/07/2021")
	@Test()
	public void testNewLumaYogaCollectionSortingMethods() throws Exception {

		logStep("Navigate to Magento Website Home Page.");
		HomePage homePage = navigateToMagentoWebsite();
		assertTrue(homePage.validateNotLoggedInUser(), "Default Welcome Message is Displayed.");
		
		logStep("Go to New Luma Yoga Collection");
		homePage.clickPartialLink("Shop New Yoga");
		NewLumaYogaCollection newLumaYogaCollection = new NewLumaYogaCollection(driver);
		assertTrue(newLumaYogaCollection.isPageTitleDisplayed("New Luma Yoga Collection"), "New Luma Yoga Collection Page is Displayed");
		
		logStep("Validate Sorting Options");
		ProductSorterFunction productSorterFunction = newLumaYogaCollection.getProductSorterFunction();
		productSorterFunction.sortByOptionAs("price");
		productSorterFunction.sortByOptionAs("position");
		productSorterFunction.sortByOptionAs("name");
		
		logStep("Validate Sorting Orders");
		productSorterFunction.sortByDescendingOrder();
		productSorterFunction.sortByAscendingOrder();
		
		productSorterFunction.clickHome();
		closeBrowser();
	}
}