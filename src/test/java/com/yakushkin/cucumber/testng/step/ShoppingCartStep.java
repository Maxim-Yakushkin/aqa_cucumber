package com.yakushkin.cucumber.testng.step;

import com.yakushkin.onliner.pageobject.CartPage;
import com.yakushkin.onliner.pageobject.ProductPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.StringUtils;

/**
 * The ShoppingCartStep has the same scenario steps ordering as feature file.
 * Therefore, the annotations @And have not been changed to @When to avoid confusion.
 */
public class ShoppingCartStep {

    private final ProductPage productPage = new ProductPage();
    private final CartPage cart = new CartPage();
    private String productPageUrl = StringUtils.EMPTY;

    @Given("the user opened any product page {string}")
    public void userOpenedAnyProductPage(String link) {
        productPageUrl = link;
        productPage.openByUrl(productPageUrl);
    }

    @When("the user clicks on {string} button from any supplier")
    public void userClicksOnButton(String buttonName) {
        productPage.clickOnBuyNowButton(buttonName);
    }

    @And("the user close recommended side bar")
    public void userCloseRecommendedSideBar() {
        productPage.closeProductRecommendedSidebar();
    }

    @And("the user click on shopping cart icon")
    public void userClickOnShoppingCartIcon() {
        productPage.clickOnShoppingCartIcon();
    }

    @Then("the shopping cart contains the previously selected product")
    public void verifyShoppingCartContainsThePreviouslySelectedProduct() {
        cart.clickOnProductLink();
        productPage
                .verifyCurrentProductUrl(productPageUrl);
    }

    @Then("the product page contains button with {string} name for previously selected supplier")
    public void verifyProductPageContainsButtonWithName(String buttonName) {
        productPage.verifyOrderButtonHasNameInCart(buttonName);
    }
}
