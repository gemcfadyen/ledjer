Feature: Exercise 2
  The ledger can accept payments.
  Payments decrease the balance of the ledger.

  Scenario: Payments.
    Given an empty ledger
    And a deposit of 10000p is made on Feb 6, 2013
    When a payment of 100p is made on Feb 7, 2013 to Apple
    And a payment of 200p is made on Feb 8, 2013 to Amazon 
    And a payment of 300p is made on Feb 9, 2013 to BBC 
    Then the balance is 9400p
    And the statement contains
    """
    Feb 6, 2013 1. Deposit: £100.00
    Feb 7, 2013 2. Payment to Apple: (£1.00)
    Feb 8, 2013 3. Payment to Amazon: (£2.00)
    Feb 9, 2013 4. Payment to BBC: (£3.00)
    Total: £94.00
    """
