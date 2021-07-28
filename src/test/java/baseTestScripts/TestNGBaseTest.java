package baseTestScripts;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.home.HomePage;
import utils.AssertUtil;
import utils.ReportUtils;

public class TestNGBaseTest extends AssertUtil {

	protected RemoteWebDriver driver;

	public HomePage navigateToMagentoWebsite() {
		
		logStep("Opening Browser Successfully...");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu");
		options.addArguments("window-size=1280x768");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		System.setProperty("webdriver.chrome.driver", TestData.driver_location);
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(TestData.magento_url);
		return new HomePage(driver);
	}

	public void closeBrowser() {
		logStep("Closing Browser Successfully...");
		driver.quit();
	}

	public void logStep(String logs) {
		ReportUtils.logStep(logs);
	}
}