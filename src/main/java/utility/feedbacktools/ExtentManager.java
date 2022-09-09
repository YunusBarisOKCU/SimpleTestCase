package utility.feedbacktools;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;

public class ExtentManager  {
    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    public static void setExtent() {

        htmlReporter =new ExtentHtmlReporter(System.getProperty("user.dir")+ File.separator+"reports"+File.separator+"AutomationReport.html");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Automation Test Results");

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);

        extentReports.setSystemInfo("Tester", "Yunus Barış OKCU");
        extentReports.setSystemInfo("OS", "Win10");
        extentReports.setSystemInfo("Browser", "Chrome");
    }

    public static void createTest(String testName){
        extentTest = extentReports.createTest(testName);
    }

    public static void extentInfo(String log){
        extentTest.info(log);
    }

    public static void extentLog(Status status,String details){
        extentTest.log(status,details);
    }

    public static void extentLog(Status status, Markup markup){
        extentTest.log(status,markup);
    }

    public static void extentFailScreenShot(String imgPath) throws IOException {
        extentTest.fail("ScreenShot is Attached", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());

    }

    public static void endReport() {
        extentReports.flush();
    }


}
