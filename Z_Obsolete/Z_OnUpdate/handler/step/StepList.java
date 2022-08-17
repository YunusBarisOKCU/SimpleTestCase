package handler.step;

import handler.elementRepo.WebElements;
import handler.methods.Methods;
import org.openqa.selenium.By;

public class StepList extends Methods {
    WebElements elements=new WebElements();
    public StepList() {


    }

    public boolean NavigateCheck(String url) throws Throwable{
        NavigateTo(url);
        //if(driver.getTitle().toString() == By.xpath(elements.mainPageTitleXpath).toString())
            return true;
        //else
        //    return false;
    }

    public boolean LoginCheck(String userName, String password) throws Throwable{

        moveActions(elements.signInLoginDiv);
        moveActions(elements.signInButton);
        click(elements.signInButton);
        sendKeys(elements.userName,userName);
        click(elements.btnLogin);
        sendKeys(elements.txtPassword,password);
        click(elements.btnEmailSelect);
        if(!getTextContains(elements.signInLoginDiv,"Üye Ol"))
            return true;
        else
            return false;
    }

    public boolean SearchElement(String samsung) throws Throwable{
        return false;
    }

    public boolean leftBarSelect(String telefon, String cep_telefonu) throws Throwable{
        return false;
    }
}
