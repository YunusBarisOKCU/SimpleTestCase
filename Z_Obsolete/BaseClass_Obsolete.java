package Z_Obsolete;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class BaseClass_Obsolete {

    public static WebDriver driver;
    public static Actions action;
    public static ExtentReports extentR;
    public static ExtentTest extentTest;
    public static Logger logger = LoggerFactory.getLogger(BaseClass_Obsolete.class);
    public static ExtentSparkReporter sparkReporter;
    DesiredCapabilities capabilities = new DesiredCapabilities();

    @BeforeTest
    public void beforeTestMethod(Test test){
        //raporlama kurulumu
        sparkReporter =new ExtentSparkReporter(System.getProperty("user.dir")+ File.separator+"reports"+File.separator+"AutomationReport.html");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setDocumentTitle("Automation Report (optiim)");
        sparkReporter.config().setReportName("Automation Test Results");
        sparkReporter.config().setTheme(Theme.DARK);
        extentR =new ExtentReports();
        extentR.attachReporter(sparkReporter);
        extentR.setSystemInfo("Simple Test Scenerio for Optiim interview", "by Yunus Barış OKCU");
        extentTest = extentR.createTest(test.testName(), "Hepsiburada 16 adet temel step testi.");


        setupDriver(/*browserName*/"chrome");

    }

    @BeforeMethod
    /*@Parameters(value = {"browserName"})*/
    public void beforeMethodMethod(/*String browserName ,*/Method method){
        //Browser initialization segmenti
        //driver.get(Constants.url);
        //action = new Actions(driver);//hover gibi eylemler için



    }


    @AfterMethod
    public void afterMethodMethod(ITestResult result){
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
        }

        driver.quit();
    }


    @AfterTest
    public void afterTestMethod(){
        extentR.flush();
    }

    //Browser seçimi işlemi
    public void setupDriver(String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
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
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.firefox.driver",System.getProperty("user.dir")+ File.separator+"drivers"+File.separator+"firefox");
            driver=new FirefoxDriver();
        }
        else {
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ File.separator+"drivers"+File.separator+"chromedriver");
            driver=new ChromeDriver();
        }
    }

    public static WebDriver getWebDriver() {
        return driver;
    }


}
