package actioninterface;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ActionInterface {
	

	public void click(WebDriver ldriver, WebElement ele);
	public boolean isDisplayed(WebDriver ldriver, WebElement ele);
	public boolean type(WebElement ele, String text);
	public boolean findElement(WebDriver ldriver, WebElement ele);
	public boolean alert(WebDriver driver);
	public boolean launchUrl(WebDriver driver,String url);
	public String getCurrentURL(WebDriver driver);
	public String screenShot(WebDriver driver, String filename);

}
