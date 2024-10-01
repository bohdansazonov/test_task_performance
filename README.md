Bohdan Sazonov test task
============================================

Test will include 3 scenarios that will run simultaneously

For each scenario 10 users will send request every secod during 30 seconds

## Run test

To run test please execute maven command `mvn gatling:test`

It can be executed directly from readme file for your comfort

## Test execution report
* Test report for each run will be stored in the `target` folder
* Each test report will be marked with timestamp
* Please note that each test report wil contain both: aggregated report for all requests and detailed report for each endpoint
