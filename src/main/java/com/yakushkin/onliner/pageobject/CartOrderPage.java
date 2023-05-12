package com.yakushkin.onliner.pageobject;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.time.Duration.ofSeconds;

public class CartOrderPage implements Cart {

    public void verifyIsCartOrderPageOpen() {
        webdriver()
                .shouldHave(url("https://cart.onliner.by/order"));

        $x("//div[normalize-space(text())='Оформление заказа']")
                .shouldBe(and("displayed", exist, visible), ofSeconds(5));
    }

    @Override
    public void clickOnBrowserBackArrow() {
        back();
    }
}
