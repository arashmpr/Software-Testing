Feature: User_Behavior_withdrawCredit
#  Background:
#    Given a user with a credit balance of 100.0

  Scenario: Successful withdrawal within available credit
    Given a user with a credit balance of 100.0
    When the user withdraws 50.0 credit
    Then the new credit balance should be 50.0

  Scenario: Insufficient credit for withdrawal
    Given a user with a credit balance of 100.0
    When the user attempts to withdraw 150.0 credit
    Then an InsufficientCredit exception should be thrown

  Scenario: Withdrawal of zero credit
    Given a user with a credit balance of 100.0
    When the user attempts to withdraw 0.0 credit
    Then the new credit balance should be 100.0

  Scenario: Withdrawal of negative credit
    Given a user with a credit balance of 100.0
    When the user attempts to withdraw -30.0 credit
    Then an InvalidCreditRange exception should be thrown