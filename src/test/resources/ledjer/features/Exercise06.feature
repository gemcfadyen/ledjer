Feature: Exercise 6
  Assign each Transaction a sequential id number on creation with nextNumber.
  (Hint: nextNumber can be static.)

  Scenario: Each transaction is assigned a sequential id number.
    Given an empty ledger
    When a deposit of 100p is made on May 14, 2013
    And a deposit of 100p is made on May 15, 2013
    And a payment of 100p is made on May 16, 2013 to amazon.com
    And a payment of 100p is made on May 17, 2013 to amazon.com
    And a deposit of 100p is made on May 18, 2013
    Then the statement contains
    """
    May 14, 2013 1. Deposit: £1.00
    May 15, 2013 2. Deposit: £1.00
    May 16, 2013 3. Payment to amazon.com: (£1.00)
    May 17, 2013 4. Payment to amazon.com: (£1.00)
    May 18, 2013 5. Deposit: £1.00
    Total: £1.00
    """
