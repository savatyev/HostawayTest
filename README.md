**Test description and implementation details**

https://dashboard.hostaway.com/login

1.	Navigate to User Management and create a user with the following requirements:
    a.	Has Admin access
    b.	Has a listing (any) assigned
    c.	Receives reservation email notifications
    d.	Has access to financial reservation information
    e.	Has access to guest contact data
2.	Edit the user to:
    a.	View only guest contact data
    b.	View only Reservations module
    c.	Can Create a new reservation from Calendar.

Configured to run in 3 browsers one by one - Internet Explorer, Chrome, Firefox.
**See the demo video of test execution - HostawayDemoSeleniumTest.wmv**

Implemented in Java 8 + Selenium Webdriver + TestNG + Maven, using PageObject and PageFactory design patterns.
I aslo added Allure reporting tool, so you can see the HTML report in \target\site folder (report content works properly only in Firefox).
There are 2 positive test cases in \src\test\java\CreateUpdateUserTest.java file, along with setUp and cleanUp methods.

**mvn clean test site** to run the tests and report
