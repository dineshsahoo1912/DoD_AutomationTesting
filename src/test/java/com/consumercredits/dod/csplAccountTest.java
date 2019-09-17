package com.consumercredits.dod;

import com.consumercredits.commonsteps.commonSteps;
import com.consumercredits.pagesdod.AccountSearchPage;
import com.consumercredits.pagesdod.TriggerViewSearchPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class csplAccountTest extends commonSteps {

    @Test
    public void searchAccountNumber() {
        AccountSearchPage.language_button_en(driver).click();
        wait.until(ExpectedConditions.textToBePresentInElement(AccountSearchPage.text_name(driver),"Name"));
        AccountSearchPage.account_id_search(driver).sendKeys("101192592");
        AccountSearchPage.account_id_search(driver).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOf(TriggerViewSearchPage.trigger_search(driver,"Legal problems")));
        Assert.assertEquals("Account Number", AccountSearchPage.text_accountnumber(driver).getText());

    }

    @Test (priority = 1)
    public void checkClientManualTrigger() {
        String fontWeight;
        test = extent.createTest("Search CSPL Account Test");
        if (AccountSearchPage.language_button_en(driver).isEnabled()) {
            fontWeight = TriggerViewSearchPage.trigger_search(driver,"Legal problems").getCssValue("font-weight");
        }

        else {
            fontWeight = TriggerViewSearchPage.trigger_search(driver,"Veroordeling+").getCssValue("font-weight");
        }
        Assert.assertEquals("700",fontWeight);
    }

    @Test (priority = 2)
    public void checkAccountManualTrigger() {
        test = extent.createTest("Check Manual Account level trigger");
        TriggerViewSearchPage.account_expand_icon(driver).click();
        wait.until(ExpectedConditions.visibilityOf(TriggerViewSearchPage.trigger_search(driver,"Forbearance")));
        String fontWeight = TriggerViewSearchPage.trigger_search(driver, "Forbearance").getCssValue("font-weight");
        Assert.assertNotEquals("700",fontWeight);
    }

//    @Test (priority = 3)
//    public void modifyManualTrigger() {
//        test = extent.createTest("Modification of a Manual Trigger");
//        TriggerViewSearchPage.trigger_search(driver,"Legal Problems").click();
//        wait
//    }
}
