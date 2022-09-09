package pageobjects;

import actiondriver.Action;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.feedbacktools.Log;

//
public class TempPage extends BasePage {

    public String likedProductID;
    Action action= new Action();

    //mainpage
    @FindBy(xpath="//*[@id=\"nav-link-accountList\"]")
    private WebElement signInLoginDiv;
    @FindBy(xpath="//*[@id=\"nav-flyout-ya-signin\"]/a")
    private WebElement signInButton;

    //loginpage
    @FindBy(xpath="//*[@id=\"ap_email\"]")
    private WebElement userNameTextBox;
    @FindBy(xpath="//*[@id=\"continue\"]")
    private WebElement btnLogin;
    @FindBy(xpath="//*[@id=\"ap_password\"]")
    private WebElement txtPassword;
    @FindBy(xpath="//*[@id=\"auth-signin-button\"]")
    private WebElement btnEmailSelect;

    //mainpageafterlogin
    @FindBy(xpath = "//*[@id=\"twotabsearchtextbox\"]")
    private WebElement searchBox;
    @FindBy(xpath = "//*[@id=\"nav-search-submit-button\"]")
    private WebElement searchBoxButton;

    //searchPage
    @FindBy(xpath = "//*[@id=\"n/13709907031\"]/span/a")
    private WebElement listItemMobilePhone;
    @FindBy(xpath = "//*[@class=\"s-pagination-strip\"]/a")
    private WebElement paginationButton;
    @FindBy(xpath = "//*[@class=\"s-pagination-strip\"]/span")
    private WebElement paginationButtonClicked;
    @FindBy(xpath = "//*[@cel_widget_id='MAIN-TOP_BANNER_MESSAGE-0']//*/h3/span")
    private WebElement isSearchedElementListOnPage;//obsolete
    @FindBy(xpath = "//*[@cel_widget_id=\"MAIN-SEARCH_RESULTS-5\"]/div/div/div/span/a")
    private WebElement fiftElement;
    @FindBy(xpath = "//*[@id=\"add-to-wishlist-button-submit\"]")
    private WebElement addToList;
    @FindBy(xpath = "//*[@id=\"huc-view-your-list-button\"]/span")
    private WebElement viewYourListButton;
    @FindBy(xpath = "//*[@id=\"a-popover-6\"]/div/header/button")
    private WebElement popUpCloseButton;
    @FindBy(xpath = "//*[@id=\"huc-view-your-list-button\"]/span/a")
    private WebElement likedListButton;
    @FindBy(xpath = "//*[@id=\"g-items\"]/li[2]")
    private WebElement firstProductInLikedList;
    @FindBy(xpath = "//*[@id=\"g-items\"]/li[2]//*[@class=\"a-size-base\"]/a")
    private WebElement firstProductInLikedListLink;
    @FindBy(xpath = "//*[@id=\"add-to-cart-button\"]")
    private WebElement addToCartButton;
    @FindBy(xpath = "//*[@id=\"sw-atc-details-single-container\"]/div[2]/div/span")
    private WebElement addedToCartNotification;
    @FindBy(xpath = "//*[@id=\"buybox-see-all-buying-choices\"]/span/a")
    private WebElement buyingOptionsButton;
    @FindBy(xpath = "//*[@id=\"a-autoid-2-offer-1\"]/span/input")
    private WebElement buyingOptionsFirstAddToCartButton;
    @FindBy(xpath = "//*[@id=\"sw-atc-details-single-container\"]/div[2]/div/span")
    private WebElement buyingOptionsAddedToCartNotification;
    @FindBy(xpath = "//*[@id=\"nav-cart\"]")
    private WebElement navCart;
    @FindBy(xpath = "//*[@data-item-index=\"1\"]//*[@data-feature-id=\"delete\"]/span/input")
    private WebElement removeFirstItemFromCart;
    @FindBy(xpath = "//*[@id=\"g-items\"]/li[2]//*[@class=\"a-button a-button-normal a-button-base wl-info-aa_buying_options_button\"]/span/a")
    private WebElement buyingOptionsButtonOnList;
    @FindBy(xpath = "")
    private WebElement tempElemente;



    public TempPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public String launchCheckUrl(String url) throws Throwable {
        if(launchUrl(url))
            return getCurrURL();
        else
            return "Sayfa Açımı Sırasında hata";
    }

    public boolean login(String userName, String password) throws InterruptedException {
        actionBase.moveToElement(signInLoginDiv).pause(3).build().perform();
        action.click(getDriver(),signInButton);
        action.type(userNameTextBox,userName);
        action.click(getDriver(),btnLogin);
        action.type(txtPassword,password);
        action.click(getDriver(),btnEmailSelect);
        Log.info("Giriş İşlemi Tamamlandı");
        return true;
    }

    public boolean searchSpecificProduct(String product) throws Exception {
        action.type(searchBox,product);
        action.click(getDriver(),searchBoxButton);
        action.click(getDriver(),listItemMobilePhone);
        return !action.isDisplayed(getDriver(),isSearchedElementListOnPage);
    }

    public boolean goAndCheckIfSecondPage() {
        action.click(getDriver(),paginationButton);
        return paginationButtonClicked.getText().equals("2");
    }

    public boolean chooseLikeCheck() {
        action.click(getDriver(),fiftElement);
        action.click(getDriver(),addToList);
        return action.isDisplayed(getDriver(),viewYourListButton);
    }

    public boolean getLikedProductIDfromList() {
        likedProductID=action.getCurrentURL(getDriver());
        int index=likedProductID.indexOf("/dp/");
        likedProductID=likedProductID.substring(index);
        index=likedProductID.indexOf("/ref");
        likedProductID=likedProductID.substring(4,index);
        action.click(getDriver(),likedListButton);
        return firstProductInLikedList.getAttribute("data-reposition-action-params").contains("ASIN:"+likedProductID);
    }

    public boolean productCartCheck() {
        action.click(getDriver(),firstProductInLikedListLink);
        if(action.findElement(getDriver(),addToCartButton)){
            action.click(getDriver(),addToCartButton);
            return addedToCartNotification.getText().contains("Sepete Eklendi");
        }
        else {
            action.click(getDriver(),buyingOptionsButton);
            action.click(getDriver(),buyingOptionsFirstAddToCartButton);
            return buyingOptionsAddedToCartNotification.getText().contains("Eklendi");
        }
    }

    public boolean removeFromCart() {
        action.click(getDriver(),navCart);
        action.click(getDriver(),removeFirstItemFromCart);
        return !action.isDisplayed(getDriver(),removeFirstItemFromCart);
    }
}
