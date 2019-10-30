EEAutomation Framework

This is hybrid frameork with Business Data Driven approach and based on maven. This framework is developed to run test cases on Chrome Browser but with provision to run on other browsers.
In this framework have added pico container dependency so object can be shared amoung all classes easily. I have added Page Object Model as well where object reposiroty and test cases are seperated. All the dependencies have added in maven pom.xml and configuration part kept in properties files. No need of manual intervention at all.
Report generation-

In this framework have generated reports in-

Extent Report
xcelsheet reports
testng default reports

we can have cucumber reports as well that we can cover in jenkis setup and can be shared amoung team members


Below are the tools used to develop this framework-

1. Selenium
2. Java
3. Maven
4. Test NG
5. Log4J
6. Cucumber
7. Extent HTML reports 


what is covered in this framework-

1. Execution on chrom browsers with single chrome instance
2. Summerised Reporting- Can see the report in pictorial diagrams
3. BDD and POM based approach- We can show features files to clients so they can easily get the ideas about automaion test coverage
4. Configuration in external files. All enviornment URLs and credintionals we can keep in properties files
5. Test cases and object repository seperated so easily we can add new test cases without touching existing one and can maintain easily.
6. Well maintainand folder structure
7. Maven is used for build purpose- Added all depenedencies in POM file
8. Framework well modularised so its easy to maintain code
9. Maintained logs so can debug code easily
10. Taken care of redudent code...no duplicate code at all.
11. Easy to trigger execution so non IT person also can trigger execution very easily and can analyse reports
12. We can trigger test cases execution from commandline also. No need of eclpise or intellij installed on machine.
13. Easy to configure on other machine
14. Written Reusable methods
15. Exception handling
16. Coding standards maintanied, high portability, all tools used in this framework are open sources so license cost is reduced.

What is not covered and can cover later on-

1. CICD
2. Parallel execution
3. Execution on multiple browsers and browser versions
4. Test cases execution on different machines(OSs and VMs)
5. Test cases execution report sharing on mail
6. API test cases integration- without making any changes in above framework we can add API test cases easily
7. We can parametrise test cases by using DB, Features file or xcelsheets and testbg xml file.
8. We can group test cases by using testNG. Prioritize and execute test cases in order. 
9. We can add database test cases very easily








