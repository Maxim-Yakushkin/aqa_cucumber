package com.yakushkin.cucumber.testng.step;

import com.yakushkin.onliner.pageobject.Cart;
import com.yakushkin.onliner.pageobject.CartPage;
import com.yakushkin.onliner.pageobject.ProductPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShoppingCartStep {

    private static final String PRODUCT_PAGE_URL = "https://catalog.onliner.by/videocard/gigabyte/gvn407teagleoc12";
    private final ProductPage productPage = new ProductPage();
    private Cart cart = null;

    @Given("the user opened any product page")
    public void theUserOpenedAnyProductPage() {
        productPage.openByUrl(PRODUCT_PAGE_URL);
    }

    @When("the user clicks on {string} button")
    public void theUserClicksOnButton(String buttonName) {
        productPage.clickOnBuyNowButton(buttonName);
    }

    @And("the user close recommended side bar")
    public void theUserCloseRecommendedSideBar() {
        productPage.closeProductRecommendedSidebar();
    }

    @And("the user click on shopping cart icon")
    public void theUserClickOnShoppingCartIcon() {
        cart = productPage.clickOnShoppingCartIcon();
    }

    @Then("the shopping cart contains the previously selected product")
    public void theShoppingCartContainsThePreviouslySelectedProduct() {
        CartPage cartPage = (CartPage) cart;
        cartPage.clickOnProductLink();
        productPage.verifyCurrentProductUrl(PRODUCT_PAGE_URL);
    }
}
