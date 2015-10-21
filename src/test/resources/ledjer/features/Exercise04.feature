Feature: Exercise 4
  Ledger uses only Transaction objects.
  Introduce printString() for statement().
  Ledger.pay() and Ledger.deposit() take a Transaction as an argument.
  Payments and deposits are stored in the same data structure.
  Maybe have a test that adds: deposit, payment, deposit.

  Scenario: The statement shows the transactions in the order that they are entered.
    Given an empty ledger
    When a deposit of 1000p is made on May 4, 2013
    And a payment of 100p is made on May 5, 2013 to Apple
    And a deposit of 200p is made on May 7, 2013
    And a payment of 300p is made on May 9, 2013 to Amazon
    Then the statement contains
    """
    May 4, 2013 1. Deposit: £10.00
    May 5, 2013 2. Payment to Apple: (£1.00)
    May 7, 2013 3. Deposit: £2.00
    May 9, 2013 4. Payment to Amazon: (£3.00)
    Total: £8.00
    """