package tests;

import base.BaseClass;
import dataPorter.ImportFromXL;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.TempPage;
import utility.managers.FeedBackManager;
import utility.managers.Log;


public class OptiimTest extends BaseClass {
    private TempPage tempPage;

    @Test(dataProvider = "Constants", dataProviderClass = ImportFromXL.class)
    public void optiimMulakatTesti(String userName, String password, String URL)throws Throwable{

        FeedBackManager f=new FeedBackManager();
        tempPage=new TempPage();

        Log.startTestCase("Test Başlangıç");

        Assert.assertEquals(tempPage.launchCheckUrl(URL),URL+"/","Anasayfa Açılmadı");
        f.infoLog("Adım 1."," Tamamlandı",
                "http://www.amazon.com.tr sitesini açılacak ve anasayfanın açıldığını onaylayacak");


        Assert.assertTrue(tempPage.login(userName,password),"Giriş İşlemi tamamlanmadı");
        f.infoLog("Adım 2.","Tamamlandı",
                "Login ekranını açıp, bir kullanıcı ile login olacak ( daha önce siteye üyeliğin varsa o olabilir )");


        Assert.assertTrue(tempPage.searchSpecificProduct("samsung"),"Özel arama işlemi tamamlanmadı");
        f.infoLog("Adım 3-4-5", " Tamamlandı" ,
                "Ekranın üstündeki Search alanına 'samsung' yazip 'Ara' butonuna bas\n" +
                "Sol menüden 'Telefon' sonrasında 'Cep Telefonu' tıklayacak\n" +
                "Gelen sayfada samsung için sonuç bulunduğunu onaylayacak");


        Assert.assertTrue(tempPage.goAndCheckIfSecondPage());
        f.infoLog("Adım 6"," Tamamlandı",
                "Arama sonuçlarından 2. sayfaya tıklayacak ve açılan sayfada 2. sayfanın şu an gösterimde olduğunu onaylayacak");


        Assert.assertTrue(tempPage.chooseLikeCheck());
        f.infoLog("Adım 7 8 9"," Tamamlandı",
                "5. ürüne tıklayacak\n" +
                "Ürün detayında 'Beğen' butonuna tıklayacak\n" +
                "Ürün listenize eklendi. popup kontrolü yapacak");


        Assert.assertTrue(tempPage.getLikedProductIDfromList(),"Ürünler Aynı değil.");
        f.infoLog("Adım 10 11"," Tamamlandı",
                "Ekranın en üstündeki hesabım alanında 'Beğendiklerim' tıklayacak\n" +
                "Açılan sayfada bir önceki sayfada beğendiklerime alınmış ürünün bulunduğunu onaylayacak");


        Assert.assertTrue(tempPage.productCartCheck(),"Ürün Sepete Eklenirken hata oldu.");
        f.infoLog("Adım 12 13"," Tamamlandı",
                "Beğendiklerime alınmış ürün bulunup seçilecek ve sepete eklenecek\n" +
                "'Ürün sepete eklendi' popup kontrolü yapacak");


        Assert.assertTrue(tempPage.removeFromCart(),"Ürün Sepetten silinirken hata oluştu");
        f.infoLog("Adım 14 15 16"," Tamamlandı",
                "Sepetim sayfasına gidecek\n" +
                "Sepete eklenen bu ürünün içine girilip 'Kaldır' butonuna basılacak, sepetimden çıkarılacak\n" +
                "Bu ürünün artik sepette olmadığını onaylayacak");

        Log.endTestCase("Test Sonu");
    }




    /* //Sistem kontrolü
    public void sampleMethodForEmailEntering(){
        Z_Obsolete.Events_Obsolete events=new Z_Obsolete.Events_Obsolete();
        events.hoverOnSignInLoginDiv();
        events.clickOnSignInButton();
        events.verifyLoginPageOpenedOrNot();
        events.enterEmailId();
    }*///proje kalkış için ilk test
}
