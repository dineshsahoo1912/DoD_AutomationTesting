package com.consumercredits.pagesdod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TriggerViewSearchPage {

    public static WebElement element;

    public static WebElement trigger_search(WebDriver driver, String trigger_name) {
        String triggerXpath = ".//span[@class = 'ng-binding ng-scope' and contains(text(),'" + trigger_name + "')]";
        element = driver.findElement(By.xpath(triggerXpath));
        return element;
    }

    public static WebElement save_button(WebDriver driver) {
        element = driver.findElement(By.xpath(".//span[contains(@class , 'ng-scope') and contains(text(),'Save')]"));
        return element;
    }

    public static WebElement account_expand_icon(WebDriver driver) {
        element = driver.findElement(By.xpath(".//button[contains(@class , 'ng-scope') and contains(text(),'+')]"));
        return element;
    }

}
