## Behavior Driven Development Project Using TestNG & Cucumber

### Project Description:
This is a Maven project that has Cucumber tests that validate Universal Pictures box office data using the following technologies:
1. Java 1.8
2. Maven
3. TestNG
4. Gson
5. Cucumber
6. Selenium
7. WebDriver

### Test Description:
The test grabs box office data from a dynamic web table, converts it to a JSON structure, and performs validations based on the feature files.
The test shows the behavior of TestNG and Cucumber during pass and fail states. There are 2 scenarios with 12 steps, one will fail as expected to show the test failures in the report.

### Steps to Execute Tests:
1. Install Java 1.8 or higher.
2. Install Maven.
3. Clone this repository.
4. For Mac and Linux users, open up Terminal/Command Prompt, navigate to this project, and then execute the command, "sudo chmod a+x chromedriver" (for Mac) or "sudo chmod a+x chromedriver_linux" (for Linux).
5. Open up Terminal/Command Prompt, navigate to this project, and then execute the command, "mvn clean test".
6. The reports can be found at target/cucumber-html-report/index.html.
