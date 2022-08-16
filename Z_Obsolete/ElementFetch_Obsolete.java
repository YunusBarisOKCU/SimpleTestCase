package Z_Obsolete;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
//Became obsolete after @FindBy usage
public class ElementFetch_Obsolete {
    public WebElement getWebElement(String identifierType, String identifierValue){
        switch(identifierType){
            case "ID":
                return BaseClass.driver.findElement(By.id(identifierValue));
            case "CSS":
                return BaseClass.driver.findElement(By.cssSelector(identifierValue));
            case "TAGNAME":
                return BaseClass.driver.findElement(By.tagName(identifierValue));
            case "XPATH":
                return BaseClass.driver.findElement(By.xpath(identifierValue));
            default:
                return null;
        }
    }
    public List<WebElement> getWebElementsList(String identifierType, String identifierValue){
        switch(identifierType){
            case "ID":
                return BaseClass.driver.findElements(By.id(identifierValue));
            case "CSS":
                return BaseClass.driver.findElements(By.cssSelector(identifierValue));
            case "TAGNAME":
                return BaseClass.driver.findElements(By.tagName(identifierValue));
            case "XPATH":
                return BaseClass.driver.findElements(By.xpath(identifierValue));
            default:
                return null;
        }
    }
}
