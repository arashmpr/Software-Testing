Feature: User_Behavior_removeItemFromBuyList

  Scenario: Remove item from buy list with multiple quantities
    Given a user with the following buy list:
      | Commodity ID | Quantity |
      | apple        | 2        |
      | banana       | 1        |
      | orange       | 3        |
    When the user removes one apple from the buy list
    Then the buy list should have:
      | Commodity ID | Quantity |
      | apple        | 1        |
      | banana       | 1        |
      | orange       | 3        |

  Scenario: Remove last item from buy list
    Given a user with the following buy list:
      | Commodity ID | Quantity |
      | apple        | 2        |
      | banana       | 1        |
      | orange       | 3        |
    When the user removes the last banana from the buy list
    Then the buy list should have:
      | Commodity ID | Quantity |
      | apple        | 2        |
      | orange       | 3        |

  Scenario: Attempt to remove non-existent item
    Given a user with the following buy list:
      | Commodity ID | Quantity |
      | apple        | 2        |
      | banana       | 1        |
      | orange       | 3        |
    When the user attempts to remove a non-existent item "grape" from the buy list
    Then a CommodityIsNotInBuyList exception should be thrown

  Scenario: Attempt to remove item with zero quantity
    Given a user with the following buy list:
      | Commodity ID | Quantity |
      | grape        | 0        |
    When the user attempts to remove one grape from the buy list
    Then a CommodityIsNotInBuyList exception should be thrown
