package tests.demo;

import org.testng.annotations.Test;
import baseTestScripts.TestNGBaseTest;

public class TestDemo extends TestNGBaseTest {

	@Test()
	public void testAutomationSetUp() throws InterruptedException {
		
		logStep("Check connection to Magento Website...");
		navigateToMagentoWebsite();
		assertEquals(driver.getTitle(), "Home Page", "Connection Established Successfully...");
		closeBrowser();
	}
}
