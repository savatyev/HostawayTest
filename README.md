## Test description and implementation details

https://dashboard.hostaway.com/login

1.	Navigate to User Management and create a user with the following requirements: <br />
     - Has Admin access
     - Has a listing (any) assigned
     - Receives reservation email notifications
     - Has access to financial reservation information
     - Has access to guest contact data
2.	Edit the user to: <br />
     - View only guest contact data
     - View only Reservations module
     - Can Create a new reservation from Calendar.

Configured to run in 3 browsers one by one - Internet Explorer, Chrome, Firefox. <br />
**See the demo video of test execution - HostawayDemoSeleniumTest.wmv**

Implemented in Java 8 + Selenium Webdriver + TestNG + Maven, using PageObject and PageFactory design patterns. <br />
I aslo added Allure reporting tool, so you can see the HTML report in \target\site folder (report content works properly in Firefox only). <br />
There are 2 positive test cases in \src\test\java\CreateUpdateUserTest.java file, along with setUp and cleanUp methods.

Use the folowing command to run the tests and report <br />
**mvn clean test site -Pprod** <br />
since **prod** profile is a default one, you can also use <br />
**mvn clean test site**