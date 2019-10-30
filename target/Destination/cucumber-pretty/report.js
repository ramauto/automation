$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("FeatureFiles/EE_TC.feature");
formatter.feature({
  "line": 2,
  "name": "Validate Estern Enterprise functionality",
  "description": "",
  "id": "validate-estern-enterprise-functionality",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@EETc"
    }
  ]
});
formatter.scenario({
  "line": 3,
  "name": "EE\u003eCreate User",
  "description": "",
  "id": "validate-estern-enterprise-functionality;ee\u003ecreate-user",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "I will be launching EE on \"QA\" environment",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "create new user",
  "keyword": "Then "
});
formatter.step({
  "line": 6,
  "name": "I logout from application",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "I login into application",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "I create test report for \"EE\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "QA",
      "offset": 27
    }
  ],
  "location": "EESteps.launchEE(String)"
});
formatter.result({
  "duration": 94405523799,
  "status": "passed"
});
formatter.match({
  "location": "EESteps.create_new_user()"
});
formatter.result({
  "duration": 12195814686,
  "status": "passed"
});
formatter.match({
  "location": "EESteps.logout()"
});
formatter.result({
  "duration": 3883584972,
  "status": "passed"
});
formatter.match({
  "location": "EESteps.login()"
});
formatter.result({
  "duration": 5402477544,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "EE",
      "offset": 26
    }
  ],
  "location": "EESteps.createTestReport(String)"
});
formatter.result({
  "duration": 364186915,
  "status": "passed"
});
});