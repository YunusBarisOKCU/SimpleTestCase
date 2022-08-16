package pageObjects;

import actiondriver.Action;
import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//Page Object Model. Her sayfada bulunan ve kullanılabilen elementler ve methodlar için temel sınıf
public class BasePage extends BaseClass {
    Action action= new Action();
    @FindBy(xpath="")
    private WebElement tempElement;



    public boolean methodTemplate() throws Throwable {
        return action.equals(tempElement);
    }

    public String getCurrURL() throws Throwable {
        return action.getCurrentURL(getDriver());
    }

    public boolean launchUrl(String url) {
        return action.launchUrl(getDriver(),url);
    }

    //login page

}
