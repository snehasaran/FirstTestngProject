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

	java -jar testngAutomation.jar 

3. The program logs in to an exisiting account whose username and password have been hardcoded. 
For the given set of values : tv, socks, dvd, toys, iPhone , the program runs the above scenarios to automate. These values are taken from a dataprovider class, which could be updated later on to add more keywords and make the code reusable. 

Steps in sequence of occurance: 
	-- Navigate to www.walmart.com/account
	-- Enter username
	-- Enter password
	-- Search tv in the search bar
			* Select an item from the result set
			* Add it to cart
			* Validate that item added is present in the cart and is the only item in the cart

	-- Search socks in the search bar
			* Select an item from the result set
			* Add it to cart
			* Validate that item added is present in the cart and is the only item in the cart
			
	-- Search iPhone in the search bar
			* Select an item from the result set
			* Add it to cart
			* Validate that item added is present in the cart and is the only item in the cart

	-- Search dvd in the search bar
			* Select an item from the result set
			* Add it to cart
			* Validate that item added is present in the cart and is the only item in the cart
			
	-- Search toys in the search bar
			* Select an item from the result set
			* Add it to cart
			* Validate that item added is present in the cart and is the only item in the cart
		
		
	

 Expected output on console:


Starting ChromeDriver 2.20.353124 (035346203162d32c80f1dce587c8154a1efa0c3b) on port 39015
Only local connections are allowed.
Text in str: Welcome to your Walmart account!
Your input keyword is: tv
Test value for Assert: tv----tv
Element selected
Added successfully
Clicking view cart button
Cart being reviewed
Selected element present in cart
More than 1 element found in cart : 5



Bug found in ChromeDriver: 

* During testing of keywords 'dvd' and 'socks' , the program sometimes DOES NOT click the selected element even though it is visible on the page/ within the viewport. The program executes the next statement after the .click() method but doesn't click the element explicitly, even though it says the element was selected. Because of this, latter steps fail in program execution and the program enters a sleep/dead state! This happens on a very random basis and I still don't have a workaround for it.

* Another bug that I encountered was "Vector smash protection is enabled" -- Still no clue what that is and where it is coming from. 


Reusability of code:

	-- The code is designed in a way that it can work for any possible keyword. Only the name of the keyword is to be added to the pool of key words. 
	-- Based on what the mandatory selections are required for that keyword, small patches need to be added too, which doesn't change the design of the program at all. 



	  			




	   	

	   


	

