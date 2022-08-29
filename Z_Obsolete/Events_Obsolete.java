package Z_Obsolete;

import elementRepo.elementsInfos.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import dataporter.Constants;


public class Events_Obsolete {
    public WebDriver driver;
    public Actions actions;
    public WebDriverWait webDriverWait;

    public Events_Obsolete(WebDriver driver, Actions actions, WebDriverWait webDriverWait) {
        this.driver = BaseClass_Obsolete.getWebDriver();
        this.actions = new Actions(driver);
        this.webDriverWait=new WebDriverWait(driver, 3);
    }


    public void hoverOnSignInLoginDiv(){
        ElementFetch_Obsolete fetch=new ElementFetch_Obsolete();
        hoverElement(fetch.getWebElement("XPATH", Elements.signInLoginDiv));
    }
    public void clickOnSignInButton(){
        ElementFetch_Obsolete fetch=new ElementFetch_Obsolete();
        fetch.getWebElement("XPATH", Elements.signInButton).click();
    }
    public void verifyLoginPageOpenedOrNot(){
        ElementFetch_Obsolete fetch=new ElementFetch_Obsolete();
        Assert.assertTrue(fetch.getWebElementsList("XPATH", Elements.loginPageIndicatorCheck).size()>0,"Login page did not open1;");
    }
    public void enterEmailId(){
        ElementFetch_Obsolete fetch=new ElementFetch_Obsolete();
        fetch.getWebElement("XPATH", Elements.textBoxForEmail).sendKeys(Constants.emailAdress);
    }



    public void hoverElement(WebElement element) {
        actions.moveToElement(element).build().perform();
    }
}
