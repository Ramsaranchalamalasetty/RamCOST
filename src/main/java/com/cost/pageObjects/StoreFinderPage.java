package com.cost.pageObjects;

import com.cost.factory.Pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.stream.Collectors;


public class StoreFinderPage extends Pages {

    @FindBy(css = ".page-header a[href='/location-finder']")
    private WebElement storeFinderBtn;
    @FindBy(name = "searchLocation")
    private WebElement searchLocation;
    @FindBy(css = ".find-store button[type='submit']")
    private WebElement submitBtn;
    @FindAll(@FindBy(css = ".accordion-item .store-name"))
    private List<WebElement> resultsList;
    @FindAll(@FindBy(css = ".accordion-item .heading"))
    private List<WebElement> nearestLocationResults;
    @FindBy(css = "#findOptions a[data-pageurl='/location-finder/']")
    private WebElement locateMeBtn;


    public void navigateToStoreFinder() {
        waitForVisibilityOfElement(storeFinderBtn).click();
    }

    public void searchlocation(String city) {
        waitForVisibilityOfElement(searchLocation).sendKeys(city);
        waitForVisibilityOfElement(submitBtn).click();

    }

    public boolean searchResults(String cityResult) {
        return waitForVisibilityOfElements(resultsList)
                .stream()
                .anyMatch(e -> waitForVisibilityOfElement(e)
                        .getText()
                        .contains(cityResult));
    }

    public void locateMe() {
        waitForVisibilityOfElement(locateMeBtn).click();
    }

    public void nearestStoresResultsList() {
        List<WebElement> elements = waitForVisibilityOfElements(nearestLocationResults);
        for (WebElement element : elements) {
            System.out.println("LOCATION :" + element.getText());
        }
    }
}
