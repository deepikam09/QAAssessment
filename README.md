# QAAssessment
Page Object model Test automation framework using Selenium with Java, TestNG and Maven-
This is an assessment performed on XYZ Bank UI 

Dependency Java Maven

###libraries used Selenium TestNG 

Steps to clone execute the tests
git clone https://github.com/deepikam09/QAAssessment
mvn clean test




############Test Script###########
src/main/java
= com.qa.base > Test Base file to initiate all common functionalities
= com.qa.util > Test Util file to invoke all utilities to handle the test 
= com.qa.pages > Page based WebElement init and functionalities call
= com.qa.config > Properties file


src/test/java
TestCase1_Q1.java
Consist of three test as follows
1. Insert Customer
2. Verify Customer added 
3. Delete the given customers

TestCase2_Q2.java

consist of below test 
1. Select Customer
2. Perform Transaction and verify 
