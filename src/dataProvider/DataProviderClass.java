package dataProvider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderClass {
	//Args: URL, Title of the page
		 @DataProvider(name="pages",parallel=false)
		 public static Object[][] pages() {
			 return new Object[][] {
					 //{"https://www.facebook.com","Facebook - Log In or Sign Up"},
					 //{"https://www.google.com","Google"},
					 {"https://www.walmart.com/account","Walmart"}
			 };
		 }
		 
		 @DataProvider(name="inputKeywords",parallel=false)
		 public static Object[][] inputKeywords(){
			 return new Object[][] {
					 {"tv","/ip/SCEPTRE-X322BV-HDR-32-LED-Class-720P-HDTV-with-ultra-slim-metal-brush-bezel-60Hz/25059351","/ip/25059351"},
					 {"socks","/ip/Fruit-of-the-Loom-Ladies-Low-Cut-Socks-6-pair-2-free-bonus-pack/37202066","/ip/37202072"},
					 {"iPhone","/ip/Refurbished-Straight-Talk-Apple-Prepaid-iPhone-5C-8GB-LTE-Smartphone-with-30-day-45-Service-Plan/45599395","/ip/39665237"},
					 {"dvd","/ip/Verbatim-95102-DVD-R-4.7GB-16X-100-Pack/8207324","/ip/8207324"},
					 {"toys","/ip/Lincoln-Logs-Prairie-Town-Mine-Building-Set/22716922","/ip/22716922"}
			 };
		 }
}
