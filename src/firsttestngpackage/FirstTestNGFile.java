package firsttestngpackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataProvider.DataProviderClass;

public class FirstTestNGFile {
	private WebDriver driver;
	
	 @BeforeClass(alwaysRun = true)
	 public void setup(){
		 System.setProperty("webdriver.chrome.driver", "/Users/Sneha/Desktop/chromedriver");
		 driver = new ChromeDriver();
	 }
	 
	 @AfterClass(alwaysRun = true)
	 public void teardown(){
		 driver.close();
	 }
	 
	 @Test(groups={"p1","pageloads"},dataProvider = "pages" , dataProviderClass=DataProviderClass.class)
	 public void checkPageLoad(String url, String title){
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.get(url);
		 Assert.assertEquals(driver.getTitle(), title);
	 }
	 
	 /*@Test(groups={"p1","pageloads"})
	 public void checkPageLoad(){
		 driver.get("https://www.walmart.com/account");
		 Assert.assertEquals(driver.getTitle(), "Walmart");
	 }*/
		
	  @Test(groups={"p1","pageloads"}, dependsOnMethods="checkPageLoad")
	  public void filloutEmailFld() throws InterruptedException {
		  String email = "snehasaran700@gmail.com";
		  WebElement enterUsername = driver.findElement(By.xpath("//*[@id='login-username']"));
		  enterUsername.sendKeys(email);
		  Thread.sleep(200);
		  Assert.assertEquals(enterUsername.getAttribute("value"), email);
	  }
	  
	  @Test(groups={"p1","pageloads"},timeOut= 1000, dependsOnMethods="filloutEmailFld")
	  public void filloutPasswordFld(){
		  String password="sneha123";
		  WebElement enterPassword = driver.findElement(By.xpath("//*[@id='login-password']"));
		  enterPassword.sendKeys(password);
		  Assert.assertEquals(enterPassword.getAttribute("value"), password);
	  }
	  
	  @Test(groups={"p1","pageloads"},timeOut= 1000, dependsOnMethods="filloutPasswordFld",priority = 1)
	  public void findSubmitButton() throws InterruptedException{
		WebElement searchBox = driver.findElement(By.xpath("(//button[@type='submit'])[3]"));
		searchBox.click();
		Assert.assertTrue(true);		
	  }
	  
	  //verify this one
	  // Doesn't locate the heading text
	  // tried using Xpath,Classname - everything!
	  /*  
	  @Test(groups={"p1","fieldtests"},timeOut= 1000, dependsOnMethods="findSubmitButton")
	  public void testCorrectLoginIn(){
			WebElement divElement = driver.findElement(By.xpath("/html/body/div[2]/section/section[4]/div/div/div/div/div[2]/div/h1"));
			String str = divElement.getText();
			Assert.assertTrue(str.contains("Welcome to your Walmart account!"));
	  }*/
	  
	 /* @Test(groups={"p2","fieldtests"},timeOut= 1000)
	  public void findSearchBar(){
		  String keyword = "tv";
		  WebElement searchbar = driver.findElement(By.xpath("//input[@id='search']"));
		  searchbar.sendKeys(keyword);
		  Assert.assertEquals(searchbar.getAttribute("value"), keyword);
	  }*/
	  
	  //Using dataproviders
	  @Test(groups={"p1","pageloads"},dataProvider = "inputKeywords" , dataProviderClass=DataProviderClass.class,dependsOnMethods="findSubmitButton",priority = 10)
	  public void findSearchBar(String keyword,String dummy) throws InterruptedException{
		  //String keyword = "tv";
		  WebElement searchbar = driver.findElement(By.xpath("//input[@id='search']"));
		  searchbar.sendKeys(keyword);
		  System.out.println("Your input keyword is: "+keyword);
		  Thread.sleep(300);
		  System.out.println("Test value for Assert: "+searchbar.getAttribute("value")+"----"+keyword);
		  Assert.assertEquals(searchbar.getAttribute("value"), keyword);
		  searchbar.clear();
	  }
	  
	  @Test(groups={"p1","pageloads"},dependsOnMethods="findSearchBar",priority = 15)
	  public void clickSearchButton() throws InterruptedException{
		  WebElement findSearchButton = driver.findElement(By.className("searchbar-submit"));
		  findSearchButton.click(); 
		  System.out.println("Search button clicked");
		  Thread.sleep(200);
		  Assert.assertTrue(true);
	  }
	  
	  
}
