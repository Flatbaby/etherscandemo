Test cases for etherscan.io

Clicking “create an account” right away

Presteps : go to https://etherscan.io/register

Steps : 
-Without inputting any information into the fields, click on “create an account”

Expected results : 
It should not allow you to go to the verification page and you will be unable to create an account

Clicking on “create an account” after putting in the information but not completing the captcha

Presteps : go to https://etherscan.io/register

Steps : 
-Input a valid username (any unused username)
-Input a valid email into the first and confirmation email fields (e.g. test@gmail.com)
-Input a valid password into the first and confirmation fields ( e.g. “password” )
-Agree to the terms and conditions and click on “create an account”

Expected results : 
The error “Error! Invalid captcha response.”  should appear and it will not proceed you to the next page.



Clicking on “create an account” after putting in the information but not agreeing to the terms and conditions

Presteps : go to https://etherscan.io/register

Steps : 
-Input a valid username (any unused username)
-Input a valid email into the first and confirmation email fields (e.g. test@gmail.com)
-Input a valid password into the first and confirmation fields ( e.g. “password” )
-Complete the captcha
- Click on “create an account”

Expected results : You should not be able to create an account and under the agreement checkbox, red text will appear.


Only entering a valid username and clicking on “Create an account”

Presteps : go to https://etherscan.io/register

Steps:
-Enter a valid username into the “Username” field
-Click on “Create an account”

Result: It should not let you create an account and will display errors under all elements except for the username field.

Entering a short username

Presteps : go to https://etherscan.io/register

Steps:
-Enter a short username (e.g. “12”)

Result: An error should appear under the username field telling you it’s too short.

Entering a non-alphanumeric username

Presteps : go to https://etherscan.io/register

Steps:
-Enter a non-alphanumeric username into the Username field(e.g. “!@#$%%”)

Expected result : An error telling you that only alphanumeric usernames are allowed and no other errors should appear.

Invalid email

Presteps : go to https://etherscan.io/register

Steps:
-Input a bad email address into the first email box (e.g. “false”)

Expected results:  It should not allow you to create the account and an error should appear under the email box until you enter a valid email address





Inputting a valid email address

Presteps : go to https://etherscan.io/register

Steps:
-Input a valid email address into the first email field (e.g. “true@gmail.com”)

Expected results: No errors should appear and the field should have a green border around it.

Invalid email with confirmation

Presteps : go to https://etherscan.io/register

-Input a false email into the first email field(e.g. “false)
-Input the same email into the confirmation field for the email address as well

Expected result:  Both fields should have errors underneath them, the first email field should prompt you to input a valid email address and the confirmation field will tell you to re-enter the email address.

Creating an account with an invalid email:

Presteps : go to https://etherscan.io/register

Steps:
-Input a valid username (e.g. “username”)
-Input an invalid email into first and second email fields (e.g. “false”)
-Input a valid password into the password fields (e.g. “password50”)
-Click on the agreement checkbox to agree with the terms and conditions
-Complete the captcha and then click on Create an account

Results: It won’t let you create the account and progress to the next page and errors underneath the email input boxes will appear.











Create an account

Presteps : go to https://etherscan.io/register

Steps: 
-Input a valid username (e.g. “username”)
-Input a valid email into both fields (e.g. “test@gmail.com”)
-Input a valid password into both fields (e.g. “password50”)
-Click on the checkbox to agree with the terms and conditions
-Complete the captcha
-Click on create an account

Result: It should take you to another page where it will prompt you to validate your email account, no errors should appear.

Registration with existing username 

Presteps : Create an account with the steps from “Create an account” and validate it through your email.

Steps:
-Input the same information for your username and email for the new account
-Enter a valid password
-Click on the checkbox to agree with the terms and conditions
-Complete the captcha and click on “Create an account”

Expected results:
An error should appear telling you that the username is already in use after clicking the Create an account button.

Short password

Presteps : go to https://etherscan.io/register

Steps:
-Input an invalid(short) password into the first and confirmation fields (e.g. “asdf”)

Expected results:
Errors under the confirmation and first input fields for passwords should appear




Wrong password confirmation

Presteps : go to https://etherscan.io/register

Steps:
-Input a valid password into the first password field(e.g. “12345678”)
-Input a valid password into the confirmation field but that does not match with the first field (e.g. “123456789”)

Expected results : An error will appear under the password confirmation field that the passwords do not match and the first input field will have a green border.

Tools used in the project

In this project, the main tools I used were Selenium along with TestNG and the build tool Maven. Selenium, along with a few different packages were used for the automation part while TestNG was used for running and organizing the tests, Maven was used for building the project and dependency management. Some major packages were WebDriverManager, TestNG annotations, JavascriptExecutor. 
WebDriverManager is used to automatically setup, update and manage WebDrivers, TestNG annotations were used to tag tests and organize them further, JavaScript executor was used to force clicks as some elements were blocked with other elements. 
BeforeMethod and AfterMethods are used for teardown and setup respectfully, to initialize and reset the tests after they run.
Asserts were also used to verify tests. 
Tools I used outside of the IDE were the Ranorex tools to grab all the XPaths.






