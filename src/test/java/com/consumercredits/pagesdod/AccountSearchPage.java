package com.consumercredits.pagesdod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountSearchPage {

    public static WebElement element;

    //Elements for Headers
    public static WebElement header_text (WebDriver driver) {
        element = driver.findElement(By.tagName("h2"));
        return element;
    }

    //Elements for Search fields on the screen
    public static WebElement account_id_search (WebDriver driver) {
        element = driver.findElement(By.id("accountNumber"));
        return element;
    }
    public static WebElement T24id_search (WebDriver driver) {
        element = driver.findElement(By.id("t24Id"));
        return element;
    }
    public static WebElement Materaid_search (WebDriver driver) {
        element = driver.findElement(By.id("matiraId"));
        return element;
    }
    public static WebElement aacid_search (WebDriver driver) {
        element = driver.findElement(By.id("aacCode"));
        return element;
    }

    //Elements for buttons on the screen
    public static WebElement language_button_nl (WebDriver driver) {
        element = driver.findElement(By.xpath(".//button[contains(@class , 'btn btn-default') and text() = 'NL']"));
        return element;
    }
    public static WebElement language_button_en (WebDriver driver) {
        element = driver.findElement(By.xpath(".//button[contains(@class , 'btn btn-default') and text() = 'EN']"));
        return element;
    }

    //Elements for Tabs on the Screen
    public static WebElement trigger_registration_tab_en (WebDriver driver) {
        element = driver.findElement(By.xpath(".//span[contains(@class , 'ng-scope') and text() = 'Trigger Registration']"));
        return element;
    }
    public static WebElement approval_list_tab_en (WebDriver driver) {
        element = driver.findElement(By.xpath(".//span[contains(@class , 'ng-scope') and text() = 'Approval list']"));
        return element;
    }
    public static WebElement trigger_registration_tab_nl (WebDriver driver) {
        element = driver.findElement(By.xpath(".//span[contains(@class , 'ng-scope') and text() = 'Trigger registratie']"));
        return element;
    }
    public static WebElement approval_list_tab_nl (WebDriver driver) {
        element = driver.findElement(By.xpath(".//span[contains(@class , 'ng-scope') and text() = 'Approval list']"));
        return element;
    }

    //Elements for fixed values on the screen
    public static WebElement text_rekeningnummer (WebDriver driver) {
        element = driver.findElement(By.xpath(".//span[contains(@class , 'ng-scope') and text() = 'Rekeningnummer']"));
        return element;
    }
    public static WebElement text_accountnumber (WebDriver driver) {
        element = driver.findElement(By.xpath(".//span[contains(@class , 'ng-scope') and text() = 'Account Number']"));
        return element;
    }
    public static WebElement text_t24id (WebDriver driver) {
        element = driver.findElement(By.xpath(".//span[contains(@class , 'ng-scope') and text() = 'T24 Id']"));
        return element;
    }
    public static WebElement text_materaid (WebDriver driver) {
        element = driver.findElement(By.xpath(".//span[contains(@class , 'ng-scope') and text() = 'Matera ID']"));
        return element;
    }
    public static WebElement text_aaccode (WebDriver driver) {
        element = driver.findElement(By.xpath(".//span[contains(@class , 'ng-scope') and text() = 'AAC@Code']"));
        return element;
    }
    public static WebElement text_naam (WebDriver driver) {
        element = driver.findElement(By.xpath(".//span[contains(@class , 'ng-scope') and text() = 'Naam']"));
        return element;
    }
    public static WebElement text_name (WebDriver driver) {
        element = driver.findElement(By.xpath(".//span[contains(@class , 'ng-scope') and text() = 'Name']"));
        return element;
    }
    public static WebElement bcid (WebDriver driver) {
        element = driver.findElement(By.xpath(".//span[contains(@class , 'ng-scope') and text() = 'BCID']"));
        return element;
    }
}
