package utility;

import actiondriver.Action;
import base.BaseClass;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import utility.managers.ExtentManager;
import utility.managers.FeedBackManager;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SuiteListener extends ExtentManager implements ITestListener, IAnnotationTransformer {
    /*ExtentTest test;*/
    Action action= new Action();
    FeedBackManager f=new FeedBackManager();

    @Override
    public void onTestStart(ITestResult result) {
        /*test = ((ExtentTestObject) result.getInstance()).test;*/
        test = extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getName() + "Testi Başarı ile sonlanmıştır");
            try {
                f.Log(result.getName()+"Testi", "ITestResult="+result.getStatus()+"ile Sonlanmıştır", "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {


        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                test.log(Status.FAIL,MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
                test.log(Status.FAIL,MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
                f.Log(result.getName()+"Testi", "ITestResult="+result.getStatus()+" ile Sonlanmıştır.","HataKodu:"+result.getThrowable().toString());


                String imgPath = action.screenShot(BaseClass.getDriver(), result.getName());

                test.fail("ScreenShot is Attached", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());

                /*String fileName = System.getProperty(System.getProperty("user.dir")+ File.separator+"screenShots"+File.separator+"failShot");
                File f = ((TakesScreenshot) BaseClass.getDriver()).getScreenshotAs((OutputType.FILE));
                FileUtils.copyFile(f, new File(fileName + ".png"));

                test.fail("ScreenShot is Attached", MediaEntityBuilder.createScreenCaptureFromPath(fileName + ".png").build());*/

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Skipped Test case is: " + result.getName());
            try {
                f.Log(result.getName()+"Testi", result.getStatus()+"ile Sonlanmıştır",result.getThrowable().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyser.class);
        IAnnotationTransformer.super.transform(annotation, testClass, testConstructor, testMethod);
    }
}
