Feature: Exercise 5
  Cannot go bankrupt.

  Scenario: Negative balance.
    Given a ledger with a balance of 1000p
    When trying to make a payment of 1001p on Sep 10, 2014
    Then the payment will be rejected
