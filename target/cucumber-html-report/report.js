$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/musicqubed/cucumber/resources/authentication/LoginWithFacebook.feature");
formatter.feature({
  "line": 1,
  "name": "Successful login via Facebook using Web Browser",
  "description": "",
  "id": "successful-login-via-facebook-using-web-browser",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "LoginWithFacebook",
  "description": "",
  "id": "successful-login-via-facebook-using-web-browser;loginwithfacebook",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I open MTV_trax",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I tap on Join With Facebook button",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "I should see Facebook activation dialog",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginWithFacebook.initApp()"
});
formatter.result({
  "duration": 5202431529,
  "status": "passed"
});
formatter.match({
  "location": "LoginWithGoogle.tapOnJoinWithFacebokButton()"
});
formatter.result({
  "duration": 80097,
  "status": "passed"
});
formatter.match({
  "location": "LoginWithO2.verifyFacebookActivationDialog()"
});
formatter.result({
  "duration": 74321,
  "status": "passed"
});
});