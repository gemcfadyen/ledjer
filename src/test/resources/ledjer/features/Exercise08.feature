Feature: Exercise 8
  Support unlimited transactions using a LinkedList.

  Scenario: Many transactions
    Given an empty ledger
    When 100 deposits of 100p are made on Jan 1, 2015
    Then the balance is 10000p
