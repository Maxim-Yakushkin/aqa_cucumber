@smoke
Feature: As an open web user
  I want to add any product from Catalog section to shopping cart

  Scenario: Shopping cart should contains selected product by click on 'Купить сейчас'
    Given the user opened any product page
    When the user clicks on "В корзину" button
    And the user close recommended side bar
    And the user click on shopping cart icon
    Then the shopping cart contains the previously selected product