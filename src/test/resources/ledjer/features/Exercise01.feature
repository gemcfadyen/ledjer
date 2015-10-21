Feature: Exercise 1
  The ledger can accept deposits.
  Deposits increase the balance of the ledger.

  Scenario: Deposits.
    Given an empty ledger
    When a deposit of 100p is made on Jan 6, 2013
    And a deposit of 200p is made on Feb 7, 2013
    Then the balance is 300p
    And the statement contains
    """
    Jan 6, 2013 1. Deposit: £1.00
    Feb 7, 2013 2. Deposit: £2.00
    Total: £3.00
    """
