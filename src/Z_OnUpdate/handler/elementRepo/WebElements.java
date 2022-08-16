package handler.elementRepo;

import org.openqa.selenium.By;

public class WebElements {
    //HomePage
    //public By mainPageTitle= By.xpath("Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com");
    public String mainPageTitle="Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com";
    public By mainPageTitleXpath=   "/html/head/title"
    public By signInLoginDiv=   "//*[@id=\"myAccount\"]"
    public By signInButton=   "//*[@id=\"login\"]"

    //loginPage
    public By userName=   "//*[@id=\"txtUserName\"]"
    public By btnLogin=   "//*[@id=\"btnLogin\"]"
    public By txtPassword=   "//*[@id=\"txtPassword\"]"
    public By btnEmailSelect=   "//*[@id=\"btnEmailSelect\"]"





    /*
    public String mainPageTitleXpath="/html/head/title";
    public String signInLoginDiv = "//*[@id=\"myAccount\"]";
    public String signInButton = "//*[@id=\"login\"]";
    //loginPage
    public String userName = "//*[@id=\"txtUserName\"]";
    public String btnLogin = "//*[@id=\"btnLogin\"]";
    public String txtPassword = "//*[@id=\"txtPassword\"]";
    public String btnEmailSelect = "//*[@id=\"btnEmailSelect\"]";*/




}
