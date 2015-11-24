Given Problem Statement: 
Automate an end-to-end user e-commerce transaction flow using TESTNG for www.walmart.com with an existing customer on Chrome or Safari browser.

Scenario to automate:
1. Login using existing account
2. Perform a search on home page from a pool of key words given : tv, socks, dvd, toys, iPhone 
3. Identify an item from the result set that you can add to cart
4. Add the item to cart
5. Validate that item added is present in the cart and is the only item in the cart

Test Data:
• Account details: create your own account
• Search terms: tv, socks, dvd, toys, iPhone

Testing tools and Programming language to be utilized: 
TestNG using Selenium. Data is given through DataProviders ----- DATA DRIVEN TESTING. 

<------------------------------------------------------------------------------------------------------------------------------>

Requirements:
Browser Used/required: Chrome
Programming language used for testing: Java
Java version used: java version "1.7.0_79"
Testing Tool used: TestNG.
IDE tested on: Eclipse, Version: Luna Service Release 1 (4.4.1)

How to run the program using command line:
1. cd to the folder where the project is downloaded
2. Run the runnable jar with the following command:     

	java -jar loginValidationWithChromeDriver.jar 

3. On the console, enter the credentials/values in the following order: 
Enter Walmart account's username: 
<Enter username here> and then hit enter. ---Expected user input
Enter Walmart account's password: 
<Enter password here> and then hit enter.  ---Expected user input

 Please input a value from : tv, socks, dvd, toys, iPhone:
 <Enter a value from the allowed keywords specified in the above line> and then hit enter.   ---Expected user input


 Expected output on console:
Starting ChromeDriver 2.20.353124 (035346203162d32c80f1dce587c8154a1efa0c3b) on port 29203
Only local connections are allowed.
---------Email id exists --------------
----------Password exists ------------
Submit/Sign in button found
Successful
Search button clicked, Data displayed
Element selected
Added successfully
Clicking view cart button
Cart being reviewed
Selected element present in cart
1 element found in cart





Understanding the program:
Step I. The program takes the following input from the user: 

Enter Walmart account's username: 
<Expected user input>
Enter Walmart account's password: 
<Expected user input>

 Please input a value from : tv, socks, dvd, toys, iPhone:
 <Expected user input>  
 			--- The keywords are case-sensitive. 
 			--- The username and password should be of an exisiting user/customer. 
 			--- The program is not designed for New users. 
 	i)On Unsuccessful input: 
 		-- If the user enters any other value except the ones specified, the program displays "Please enter a valid values i.e. from : tv, socks, dvd, toys, iPhone" error message on screen and terminates.
 		-- You'll need to re-run the program by following the steps mentioned in  "How to run the program using command line:" above.
 	ii) On Successful input, the program begins execution and starts performing the following steps:

			1. Navigate to the URL https://www.walmart.com/account.
			2. Logs in to an existing account with the credentials that the user entered in the first step(Step I).
				-- The program only works for correct set of login credentials for now. If there was more time, I could have handled other cases too :
						* Both Email and Password Fields are blank
						* Email field is filled and Password field is blank.
						* Email field is blank and Password field is filled
						* Email and Password are entered wrong
						* Email is wrong and Password is correct 
						* Email is correct and Password is wrong
				-- In case of incorrect credentials, the program displays 
				---------Email id exists --------------
				----------Password exists ------------		
				Submit/Sign in button found
				Welcome text not found
				on the console. 

				The idea behind successful login for this program is locating the Welcome Text - "Welcome to your Walmart account!" on the webpage. After entering the credentials(correct/incorrect) and then hitting Sign-in button, if the program locates the text, it considers that as a successful login and moves on to the next steps otherwise it displays "Welcome text not found" on the console and terminates at that point.

			3. After entering the login credentials, The program looks for a sign in button. 
				a) On successfully finding it, "Submit/Sign in button found" message is printed on the console.
				b) If there's an error, "Submit/Sign in button not found" message is printed on the console.
			4. The code validates successful login by looking for the welcome text on the webpage "Welcome to your Walmart account!".
				a) If it is found, "Successful" message is displayed on the console.
				b) If not, "Welcome text not found" message is displayed on the console.
			5. Program looks for the search bar and types the keyword to search. In case of an error, "Search bar not found" message is displayed on the console.
			6. After a keyword has been entered on the search bar, the program looks for the search button to click. 
				a) If it is successfully identified on the webpage, "Search button clicked, Data displayed" message is displayed on the 
				console and data is rendered on the webpage. 
				b) In case of error, "Search button not found" message is displayed on the console.
			7. Based on the keyword entered by the user in Step I, the program performs different actions accordingly:
				a) For socks : The program is designed to pick an element from the grid with the anchor tag - /ip/Fruit-of-the-Loom-Ladies-Low-Cut-Socks-6-pair-2-free-bonus-pack/37202066
				b) For tv : The program is designed to pick an element from the grid with the anchor tag - /ip/SCEPTRE-X322BV-HDR-32-LED-Class-720P-HDTV-with-ultra-slim-metal-brush-bezel-60Hz/25059351
				c) For iPhone : The program is designed to pick an element from the grid with the anchor tag - /ip/Refurbished-Straight-Talk-Apple-Prepaid-iPhone-5C-8GB-LTE-Smartphone-with-30-day-45-Service-Plan/45599395"
				d) For dvd : The program is designed to pick an element from the grid with the anchor tag - /ip/Verbatim-95102-DVD-R-4.7GB-16X-100-Pack/8207324
				e) For toys : The program is designed to pick an element from the grid with the anchor tag -/ip/Lincoln-Logs-Prairie-Town-Mine-Building-Set/22716922";
			
					i) If the item is identified successfully, "Element selected" message is displayed on the console.
				   ii) In case of error,program returns the error message on console - "Element not found".

				-- If the keyword entered is "socks" or "iPhone", it displays "Selecting mandatory conditions" and the program selects a color. On performing this step successfully, "Appropriate color found" message is displayed on console, else the program terminates with an error message "Color not found".
					* For socks, the program is designed to select black color. 
					* For iPhone, the program is designed to select Space Gray. 	

				In order to choose another color from the available set of options, you'll need to replace the xpath with the xpath of the desired color. 	

				-- If the keyword entered is socks,the program now looks for the dropdown that selects a size for this item(This is a mandatory selection on the webpage) and clicks it.
					* If size dropdown is successfully identified, "searching for dropdown" message is displayed on the console.
				   	* In case of error/If size dropdown is not found, "Error in finding dropdown" message is displayed on the console.

				--  If the keyword entered is socks, After making the dropdown active in the previous step, the program selects an option from it and clicks it. The program is designed to select the option "4-10" from the dropdown.
					* On successfully selecting the "4-10" option, "Option selected from dropdown" message is displayed on the console.
				    * In case of error, "Can't select option from dropdown" error message is displayed on the console and program terminates.	


			8. Now, all mandatory options have been selected to add an element in the cart. The program checks if the item is in stock, it'll add it to the cart. Else it'll say "Out of Stock" and exit out of the program. 

				i) If after selecting all mandatory options, "Add to Cart" button is displayed --- That implies the item is IN STOCK. The program will then click the Add to Cart button and "Added successfully" message will be displayed on the console. 

				ii) If after selecting all mandatory options, "Get In-Stock Alert" button is displayed -- That implies the item is OUT OF STOCK. The program will then click the Get In-Stock Alert button and "Item out of stock" message will be displayed on the console and the program will exit. 
				  
				iii) If both the above cases fail, program returns the error message "Something went wrong, item couldn't be added to cart." and the program will exit. 

				iv) Program now looks for the "View cart" button to review the items in cart.
					* If button is identified successfully, "Cart being reviewed" message is displayed on the console.
				    * If not/in case of error, "Cannot review cart" error message is displayed on the console and the program terminates.

				v) At this point, items in the cart are validated. 
					i) To check if the Selected Item is present in cart :
						-- Program looks for the same element which it selected in the beginning(By checking the link to the anchor tag).
								> If successfully found, "Selected element present in cart" message is displayed on the console.
								> Otherwise, program displays "Selected element NOT present in cart" on the console and terminates.
				   ii) To check if there are other elements in cart, other than the selected item: 
				   		-- Program counts the number of elements in the cart. 
				   				> If it is 1, "1 element found in cart" message is displayed on the console.
				   				> Otherwise, "More than 1 element found in cart" message is displayed on the console.

				  iii) If the above cases fail, "Could not locate selected item in cart" message is displayed on the console and the program terminates. 





Bug found in ChromeDriver: 

* During testing of keywords 'iPhone' and 'socks' , the program sometimes DOES NOT click the selected element even though it is visible on the page/ within the viewport. The program executes the next statement after the .click() method but doesn't click the element explicitly, even though it says the element was selected. Because of this, latter steps fail in program execution and the program enters a sleep/dead state! This happens on a very random basis and I still don't have a workaround for it.

* Another bug that I encountered was "Vector smash protection is enabled" -- Still no clue what that is and where it is coming from. 

On reading/researching about it, I learnt that these 2 are bugs in ChromeDriver which doesn't allow the click event to take place. If I were to spend additional time on the project, I would have :
	--First: Implemented remaining cases for Login validation, as mentioned above.
	
	--Second: Would have tested the same project on at least one more browser like Safari. I tried working with Firefox, and upon running the selenium code on walmart.com , the website would throw a dialogue on the webpage causing the script to fail. Probably, selenium is not compatible with Firefox. I could have researched more, in case I had more time. 
	
	--Third: The problem statement given required me to navigate to www.walmart.com and then begin testing, which I noticed late and I had already started off testing with the www.walmart.com/account link. Since I did not have much time left, I left it at that. In case I were given more time, I could have made changes accordingly.


Reusability of code:

	-- The code is designed in a way that it can work for any possible keyword. Only the name of the keyword is to be added to the pool of key words. 
	-- Based on what the mandatory selections are required for that keyword, small patches need to be added too, which doesn't change the design of the program at all. 



	  			




	   	

	   


	

