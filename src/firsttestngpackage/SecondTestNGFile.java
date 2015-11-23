package firsttestngpackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import dataProvider.DataProviderClass;

public class SecondTestNGFile {
	private WebDriver driver;
	public WebDriverWait wait;
	public WebElement pickElem;
	
	 @BeforeClass(alwaysRun = true)
	 public void setup(){
		 System.setProperty("webdriver.chrome.driver", "/Users/Sneha/Desktop/chromedriver");
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 wait = new WebDriverWait(driver, 5);
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
		
	 @Test(groups={"p1","pageloads"}, dependsOnMethods="checkPageLoad",testName="filloutEmailFld")
	  public void filloutEmailFld() throws InterruptedException {
		  String email = "snehasaran700@gmail.com";
		  WebElement enterUsername = driver.findElement(By.xpath("//*[@id='login-username']"));
		  enterUsername.sendKeys(email);
		  Thread.sleep(200);
		  Assert.assertEquals(enterUsername.getAttribute("value"), email);
	  }
	  
	  @Test(groups={"p1","pageloads"},timeOut= 1000, dependsOnMethods="filloutEmailFld",testName="filloutPassword",priority=1)
	  public void filloutPasswordFld(){
		  String password="sneha123";
		  WebElement enterPassword = driver.findElement(By.xpath("//*[@id='login-password']"));
		  enterPassword.sendKeys(password);
		  Assert.assertEquals(enterPassword.getAttribute("value"), password);
	  }
	  
	  @Test(groups={"p1","pageloads"}, dependsOnMethods="filloutPasswordFld",priority = 2,testName ="findSubmitButton")
	  public void findSubmitButton() throws InterruptedException{
		WebElement searchBox = driver.findElement(By.xpath("(//button[@type='submit'])[3]"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='submit'])[3]")));
		searchBox.click();
		Assert.assertTrue(true);		
	  }
	  
	  //verify this one
	  // Doesn't locate the heading text
	  // tried using Xpath,Classname - everything!
	  
	  @Test(groups={"p1","pageloads"}, dependsOnMethods="findSubmitButton",testName="testCorrectLoginIn")
	  public void testCorrectLoginIn() throws InterruptedException{
			WebElement divElement = driver.findElement(By.xpath("/html/body/div[2]/section/section[4]/div/div/div/div/div[2]/div/h1"));
			String str = divElement.getText();
			System.out.println("Text in str: "+str);
			Thread.sleep(200);
			Assert.assertTrue(str.contains("Welcome to your Walmart account!"));
	  }
	  
	  
	  //Using dataproviders
	  
	 
	@Test(groups="p1",dataProvider="inputKeywords",dataProviderClass=DataProviderClass.class,threadPoolSize = 3,priority=10,dependsOnMethods="testCorrectLoginIn")
	  public void findSearchBar(String name, String href,String cartLink) throws InterruptedException{
		  WebElement searchbar = driver.findElement(By.xpath("//input[@id='search']"));
		  searchbar.clear();
		  searchbar.sendKeys(name);
		  System.out.println("Your input keyword is: "+name);
		  //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='search']")));
		  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='search']")));
		  try{
			  System.out.println("Test value for Assert: "+searchbar.getAttribute("value")+"----"+name);
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  Assert.assertEquals(searchbar.getAttribute("value"), name);
		  try{
			  searchbar.submit();  
		  }catch(Exception e){
			  System.out.println("failed in submit");
		  }
		  Thread.sleep(5000);
		  
		  // process HREF
		  if(!name.equals("iPhone")){
			  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='"+href+"']")));
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='"+href+"']")));
				pickElem = driver.findElement(By.cssSelector("a[href='"+href+"']"));
		  }
		  else{
			  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tile-container']/div[1]/div/div/h4/a")));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tile-container']/div[1]/div/div/h4/a")));
				pickElem = driver.findElement(By.xpath("//*[@id='tile-container']/div[1]/div/div/h4/a"));
		  }
		  
		  
		  try{
			  Actions action = new Actions(driver);
				action.moveToElement(pickElem).click().perform();
				System.out.println("Element selected");
		  }
		  catch(Exception e){
			  e.printStackTrace();
				System.out.println("Element not found"); 
		  }
		  
		  //Selecting mandatory conditions for Socks and iPhone
		  
		  if(name.equals("socks") || name.equals("iPhone")){
				try{
					// WAIT UNTIL appropriate color IS NOT selected
					System.out.println("Selecting mandatory conditions");
					WebDriverWait wait = new WebDriverWait(driver, 100);
					String xpath ="";
					switch (name) {
					case "socks":
						xpath = "//div[2]/section/section[4]/div/div[2]/div[1]/div[5]/div[2]/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div/span[2]/label/span";
						break;
					case "iPhone":
						xpath = "/html/body/div[2]/section/section[4]/div/div[2]/div[1]/div[5]/div[2]/div/div[2]/div/div[2]/div/div[1]/div/div/div/span[1]/label/span";
						break;
					default:
						break;
					}
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));		        	 

					//tests black color
					WebElement getColor = driver.findElement(By.xpath(xpath));

					//tests white color, uncomment this statement and comment the above
					//WebElement getColor = driver.findElement(By.xpath("/html/body/div[2]/section/section[4]/div/div[2]/div[1]/div[5]/div[2]/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div/span[1]/label/span"));
					getColor.click();
					System.out.println("Appropriate color found");

					//black
					//  /html/body/div[2]/section/section[4]/div/div[2]/div[1]/div[5]/div[2]/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div/span[1]/label/span

					//to test for white, uncomment
					//    /html/body/div[2]/section/section[4]/div/div[2]/div[1]/div[5]/div[2]/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div/span[2]/label/span

				}
				catch(Exception e){
					System.out.println("Color not found");
					System.exit(0);
				}
		  }
		 
		  
		  if(name.equals("socks")){
				//searches dropdown on page
				try{
					// WAIT UNTIL dropdown is not located
					WebDriverWait wait = new WebDriverWait(driver, 100);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div[2]/div/div[2]/div/div/div/div/div/div/div")));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div/div[2]/div/div[2]/div/div/div/div/div/div/div")));		        	 


					WebElement selectSize = driver.findElement(By.xpath("//div[2]/div/div[2]/div/div[2]/div/div/div/div/div/div/div"));
					selectSize.click();
					System.out.println("searching for dropdown");

				}
				catch(Throwable e){
					System.out.println("Error in finding dropdown");
					System.exit(0);
				}
			}
			
			if(name.equals("socks")){
				// select one option from dropdown
				try{
					// WAIT UNTIL one Option IS NOT selected from the dropdown
					WebDriverWait wait = new WebDriverWait(driver, 100);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/section/section[4]/div/div[2]/div[1]/div[5]/div[2]/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/div/button[2]")));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/section/section[4]/div/div[2]/div[1]/div[5]/div[2]/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/div/button[2]")));;		        	 


					WebElement selectSizeActual = driver.findElement(By.xpath("//div[2]/section/section[4]/div/div[2]/div[1]/div[5]/div[2]/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/div/button[2]"));
					//selectSizeActual.sendKeys("4-10");
					selectSizeActual.click();
					/*if(selectSizeActual.isSelected()){

			        		 System.out.println("Value in stock");
			        	 }
			        	 else{
			        		 System.out.println("Out of stock");
			        	 }*/

					System.out.println("Option selected from dropdown"); 
				}
				catch(Throwable e){
					System.out.println("Can't select option from dropdown");
					System.exit(0);
				}
			}
		  
		  // Process cartlink
			
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='WMItemAddToCartBtn']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='WMItemAddToCartBtn']")));

			Thread.sleep(1000);
			
			boolean submitbuttonPresence=driver.findElement(By.xpath("//*[@id='WMItemAddToCartBtn']")).isDisplayed();
			if(submitbuttonPresence){
				WebElement addToCart = driver.findElement(By.xpath("//*[@id='WMItemAddToCartBtn']"));
				addToCart.click();
				System.out.println("Added successfully");
			}
		}
		catch(Exception e){
			try{
				WebDriverWait wait = new WebDriverWait(driver, 10);
				//System.out.println("Stopped for 10 sec");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-out-of-stock")));
				wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-out-of-stock")));
				boolean getInStockAlertButton=driver.findElement(By.className("btn-out-of-stock")).isDisplayed();
				if(getInStockAlertButton){
					System.out.println("Item out of stock");
					System.exit(0);
				}
			}catch(Exception e1){
				e.printStackTrace();
				System.out.println("Something went wrong, item couldn't be added to cart."+e1.getMessage());
				System.exit(0);
			}
			
		}
			
			
	  }//End of findSearchbar method
	  
	  /*@Test(groups={"p1","pageloads"},dependsOnMethods="findSearchBar",priority = 15,testName="clickSearchButton",singleThreaded=false)
	  public void clickSearchButton() throws InterruptedException{
		  WebElement findSearchButton = driver.findElement(By.className("searchbar-submit"));
		  findSearchButton.click(); 
		  System.out.println("Search button clicked");
		  Thread.sleep(200);
		  Assert.assertTrue(true);
	  }*/
	  
      /*@Test(groups="p1",dataProvider="inputKeywords",dataProviderClass=DataProviderClass.class,threadPoolSize = 3)
      public void t1(String category,String exampleDesc) {
          System.out.println("t1: " + category + ":" + exampleDesc);
          System.out.println(Thread.currentThread().getId());
      }*/
	
	

	  
	  
}
