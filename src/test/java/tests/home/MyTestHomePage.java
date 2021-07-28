//package tests.home;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.Test;
//import baseTestScripts.TestData;
//
//public class MyTestHomePage {
//
//	@Test
//	public void test() throws Exception {
//		
//	
//	System.setProperty("webdriver.chrome.driver", TestData.driver_location);
//	WebDriver driver = new ChromeDriver();
//	driver.manage().window().maximize();
//	driver.get("https://dev-sct-recruitment-frontend.azurewebsites.net/crm/admin/organization");
//	
//	driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/main/div[2]/div/div/div/button[2]/span")).click();;
//	
//	WebElement e=driver.findElement(By.xpath("//*[@id=\"simple-tabpanel-1\"]/div/div/div[2]/div[2]/div/div[1]/div/div/div/button/span[1]/div"));
//	JavascriptExecutor executer = (JavascriptExecutor) driver;
//	executer.executeScript("arguments[0].click();", e);
//
////	driver.findElement(By.cssSelector("//*[@id=\"color-popover\"]/div[3]/div/div/div[1]/div/div/div/div/div")).click();
//	driver.findElement(By.id("hex")).clear();
//	driver.findElement(By.id("hex")).sendKeys("12212E");
//	driver.findElement(By.id("r")).clear();
//	driver.findElement(By.id("r")).sendKeys("54");
//	driver.findElement(By.id("g")).clear();
//	driver.findElement(By.id("g")).sendKeys("77");
//	driver.findElement(By.id("b")).clear();
//	driver.findElement(By.id("b")).sendKeys("140");
//	
//	Actions action = new Actions(driver);
//	action.sendKeys(Keys.ESCAPE).build().perform();
//	
////	driver.findElement(By.cssSelector(".muicc-colorbox-color.ColorPicker-jss271.ColorPicker-jss277")).click();
////Thread.sleep(2000);
//////driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/header/div/div/div/div[2]/div[2]/button/span[1]")).click();
////WebElement e1=driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div/main/div[2]/div/div/div/button[2]/span"));
////new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(e1)).click();
////e1.click();
//	}
//}