package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//Sayfa bağımlı element ve methodlar
public class TempPageTemplate extends BasePage {

    @FindBy(xpath="")
    private WebElement  pageObject;

    public boolean pageMethodTemplate() throws Throwable {
        return action.equals(pageObject);
    }
}
