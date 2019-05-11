package com.cost.factory;

import com.cost.pageObjects.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class Pages {
    public WebDriver driver;
    public static Logger log;

    public Pages() {
        this.driver = DriverHelper.driver;
        PageFactory.initElements( driver, this );
        log = Logger.getLogger( Pages.class );

    }

    public static StoreFinderPage storeFinderPage() {
        return new StoreFinderPage();
    }

    public WebElement waitForVisibilityOfElement(WebElement element) {
        try {
            return new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));
        } catch (WebDriverException e) {
            FluentWait<WebElement> fluentWait = new FluentWait<>(element);
            fluentWait.withTimeout(30, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.MILLISECONDS);
            fluentWait.ignoring(NoSuchElementException.class).ignoring(TimeoutException.class)
                    .ignoring(StaleElementReferenceException.class);
            return element;
        }

    }
    public List<WebElement> waitForVisibilityOfElements(List<WebElement> elements) {
        try {
            return new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (WebDriverException e) {
            FluentWait<List<WebElement>> fluentWait = new FluentWait<>(elements);
            fluentWait.withTimeout(30, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.MILLISECONDS);
            fluentWait.ignoring(NoSuchElementException.class).ignoring(TimeoutException.class)
                    .ignoring(StaleElementReferenceException.class);
            return elements;
        }

    }
}
