package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.yakushkin.enumiration.SearchFrameTab;
import org.openqa.selenium.WebElement;

import java.util.function.Predicate;

import static com.codeborne.selenide.CollectionCondition.allMatch;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;
import static java.util.Arrays.stream;
import static org.openqa.selenium.By.className;

public class SearchFrame {

    public ElementsCollection verifyAllSearchTabItems() {
        return $$x("//div[contains(@class,'search__tabs-item')]")
                .shouldBe(allMatch("all elements displayed", WebElement::isDisplayed))
                .shouldHave(allMatch("text for each element is not blank", el -> !el.getText().isBlank()))
                .shouldHave(size(SearchFrameTab.values().length), ofSeconds(5))
                .shouldHave(exactTexts(stream(SearchFrameTab.values()).map(SearchFrameTab::getName).toList()));
    }

    public SearchFrame clickOnNews() {
        verifyAllSearchTabItems()
                .filter(Condition.text(SearchFrameTab.IN_NEWS.getName()))
                .first()
                .click();

        return this;
    }

    public SearchFrame clickInNewsOnVideoFilterCheckbox() {
        $x("//span[contains(@class,'i-checkbox search__filter-checkbox')]/span")
                .shouldBe(visible, ofSeconds(5))
                .click();

        return this;
    }

    public SearchFrame verifyCatalogSearchResultCategories() {
        int indexOfCategoryName = 0;
        int indexOfCountOfItems = 1;

        $$x("//ul[@class='search__results']//div[contains(@class,'result__item_category')]")
                .shouldBe(allMatch("all elements displayed", WebElement::isDisplayed))
                .shouldHave(allMatch("text for each element is not blank", el -> !el.getText().isBlank()))
                .shouldHave(allMatch("category name is not blank", el ->
                        !el.getText().split("\n")[indexOfCategoryName].isBlank()))
                .shouldHave(allMatch("info about count of items is not blank and contains key word",
                        checkItemCountInfo(indexOfCountOfItems)));

        return this;
    }

    public SearchFrame verifyNewsSearchResultWithVideo() {
        $$x("//div[contains(@class,'result__item result__item_news')]")
                .shouldBe(allMatch("displayed", WebElement::isDisplayed))
                .shouldHave(allMatch("contains preview and data", el ->
                        el.findElement(className("news__preview")).isDisplayed() &&
                        el.findElement(className("news__data")).isDisplayed()));

        return this;
    }

    private static Predicate<WebElement> checkItemCountInfo(int indexOfCountOfItems) {
        return el ->
                !el.getText().split("\n")[indexOfCountOfItems].isBlank() &&
                el.getText().split("\n")[indexOfCountOfItems].toLowerCase().contains("товар");
    }
}