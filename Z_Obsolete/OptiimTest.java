package tests;

import base.BaseClass;
import dataporter.ExportToXL;
import dataporter.ImportFromXL;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.TempPage;
import utility.managers.ExtentManager;
import utility.managers.FeedBackManager;
import utility.managers.Log;


public class OptiimTest extends BaseClass {
    private TempPage tempPage;

    @Test(dataProvider = "Constants", dataProviderClass = ImportFromXL.class)
        public void hepsiBuradaAutomation(String userName,String password,String URL)throws Throwable{
        //ExportToXL exportToXL=new ExportToXL();
        FeedBackManager f=new FeedBackManager();
        String[] veri={"Step 1" , "tamamlandı" , "111"};

        Log.startTestCase("Hepsiburada Test");

        //1. <http://www.hepsiburada.com/> sitesini açılacak ve anasayfanın açıldığını onaylayacak
        //Assert.assertTrue(step.NavigateCheck(URL));

        tempPage=new TempPage();
        Assert.assertTrue(tempPage.LaunchUrl(URL),"Sayfa Başlatılamadı");
        Assert.assertEquals(tempPage.getCurrURL(),URL+"/","Anasayfa Açılmadı");
        f.Log("Adım ","tamamlandı","");


        //exportToXL.Export(veri);
        //exportToXL.StreamFlush();
        //ExtentManager.extentInfo("Step 1 Completed");
        //Log.info("deneme 111");

        //2. Login ekranını açıp, bir kullanıcı ile login olacak ( daha önce siteye üyeliğin varsa o olabilir )
        //Assert.assertTrue(step.LoginCheck(userName,password));
        f.Log("Adım 2","Tamamlandı","2. Login ekranını açıp, bir kullanıcı ile login olacak ( daha önce siteye üyeliğin varsa o olabilir )");
/*
        //3. Ekranın üstündeki Search alanına 'samsung' yazip 'Ara' butonuna tıklayacak
        //4. Sol menüden 'Telefon' sonrasında 'Cep Telefonu' tıklayacak
        //5. Gelen sayfada samsung için sonuç bulunduğunu onaylayacak
        // 6. Arama sonuçlarından 2. sayfaya tıklayacak ve açılan sayfada 2. sayfanın şu an gösterimde olduğunu onaylayacak
        // 7. Üstten 5. ürünü tıklayacak
        Assert.assertTrue(step.SearchElement("Samsung"));
        click(ara);
        test.info("Step 1 Completed");

        Assert.assertTrue(step.leftBarSelect("Telefon","Cep Telefonu"));
        click(cepTelefonu);
        test.info("Step 1 Completed");

        Assert.assertTrue(checkProductListName("samsung",4));
        test.info("Step 1 Completed");

        Assert.assertTrue(movetoLastProductInGridOne());
        checkIsVisibleGridtwo();
        test.info("Step 1 Completed");


        Assert.assertTrue(click(fiftproduct));
        test.info("Step 1 Completed");

        //8. Ürün detayında 'Beğen' butonuna tıklayacak
        //9. 'Ürün listenize eklendi.' popup kontrolü yapacak
        Assert.assertTrue(click(begen));
        var=productUniqueInfo;
        test.info("Step 1 Completed");

        checkIsVisible(urunListenizeEklendi);
        test.info("Step 1 Completed");

        //10. Ekranın en üstündeki hesabım alanında 'Beğendiklerim' tıklayacak
        //11. Açılan sayfada bir önceki sayfada beğendiklerime alınmış ürünün bulunduğunu onaylayacak
        click(begendiklerim);
        test.info("Step 1 Completed");

        check(productinfo,productUniqueInfo);
        test.info("Step 1 Completed");

        //12. Beğendiklerime alınmış ürün bulunup seçilecek ve sepete eklenecek
        //13. 'Ürün sepete eklendi' popup kontrolü yapacak
        click(likedProduct);
        click(sepeteEkle);
        test.info("Step 1 Completed");

        isVisible(popUpUrunsepette);
        test.info("Step 1 Completed");

        //14. Sepetim sayfasına gidecek
        click(sepetim);
        //navigateTo(checkOut.url);
        test.info("Step 1 Completed");

        //15. Sepete eklenen bu ürünün içine girilip 'Kaldır' butonuna basılacak, sepetimden çıkarılacak
        //16. Bu ürünün artik sepette olmadığını onaylayacak
        hoverOn(hoverToolTip);
        click(deleteProduct);
        test.info("Step 1 Completed");

        IsVisible(elementSepetBoş);
        test.info("Step 1 Completed");
*/
        f.streamClose();
        Log.endTestCase("Hepsiburada Test");
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
