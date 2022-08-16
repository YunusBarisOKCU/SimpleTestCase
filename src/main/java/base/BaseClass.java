package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import utility.managers.ExtentManager;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();//paralel test koşumu için selenium doc
    public static Actions actionBase;
    //DesiredCapabilities capabilities = new DesiredCapabilities(); //obsolete Bonigarcia dan sonra

    @BeforeSuite
    public void Initialize(){
        //raporlama kurulumu
        ExtentManager.setExtent();
        DOMConfigurator.configure("log4j.xml");//olmaz ise uyarı veriyor sonra bak
    }

    @BeforeMethod
    @Parameters(value = {"browserName"})
    public void beforeMethodMethod(String browserName ,Method method){
        //Browser initialization segmenti
        setupDriver(browserName);
        actionBase=new Actions(getDriver());

    }

    @AfterMethod
    public void afterMethodMethod(/*ITestResult result*/){
        getDriver().quit();

        /*
        if(result.getStatus()==ITestResult.SUCCESS){
            String methodName= result.getMethod().getMethodName();
            String logText = "TestCase: " + methodName + " Passed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            extentTest.log(Status.PASS,m);
        }
        else if(result.getStatus()==ITestResult.FAILURE){
            String methodName= result.getMethod().getMethodName();
            String logText = "TestCase: " + methodName + " Failed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
            extentTest.log(Status.FAIL,m);
        }*///obsolete Suite Listenerden Sonra

    }

    @AfterSuite
    public void afterSuite(){
        ExtentManager.endReport();
    }

    //Browser seçimi ve kurulumu
    public void setupDriver(String browserName){
        if (browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            // Set Browser to ThreadLocalMap
            driver.set(new ChromeDriver());
        } else if (browserName.equalsIgnoreCase("FireFox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        } else if (browserName.equalsIgnoreCase("IE")) {
            WebDriverManager.iedriver().setup();
            driver.set(new InternetExplorerDriver());
        }
        //Maximize the screen
        getDriver().manage().window().maximize();
        //Delete all the cookies
        getDriver().manage().deleteAllCookies();
        //Implicit TimeOuts
        getDriver().manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
        //PageLoad TimeOuts
        getDriver().manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        /*else {
            ChromeOptions options = new ChromeOptions();
            capabilities .setCapability("browserName",browserName);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            LoggingPreferences loggingPreferences = new LoggingPreferences();
            loggingPreferences.enable(LogType.BROWSER, Level.ALL);
            capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
            options.merge(capabilities);
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--kiosk");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--start-fullscreen");
            System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
            System.out.println("if(browserName.equalsIgnoreCase(\"chrome\"))   sonu");
        }*///Obsolete Bonigarcia geçişden sonra
    }

    public static WebDriver getDriver() {
        return driver.get();
    }


}
