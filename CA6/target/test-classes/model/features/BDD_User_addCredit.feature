Feature: BDD_User_aBDD_User_AddCreditddCredit

  Scenario: Adds positive amount of credit, No Error, (ADD CREDIT)
    Given a user with 230.0 initial credit
    When the user adds 50.0 credit
    Then credit is added successfully and he/she has 280.0 credit

  Scenario: Adds negative amount of credit, Gets Error, (ADD CREDIT)
    Given a user with 230.0 initial credit
    When the user wants to add negative amount of -50.0 credit
    Then an InvalidCreditRange exception should be thrown at him/her

  Scenario: Adds zero amount of credit, Gets Error, (ADD CREDIT)
    Given a user with 230.0 initial credit
    When the user adds 0.0 credit
    Then an InvalidCreditRange exception should be thrown at him/her
