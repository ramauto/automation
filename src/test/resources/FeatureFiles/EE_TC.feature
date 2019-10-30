@EETc
Feature: Validate Estern Enterprise functionality
  Scenario: EE>Create User
    Given I will be launching EE on "QA" environment
    Then create new user
    Then I logout from application
    Then I login into application
    Then I create test report for "EE"

