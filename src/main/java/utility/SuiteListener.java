package utility;

import actiondriver.Action;
import base.BaseClass;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SuiteListener implements ITestListener, IAnnotationTransformer {
    Action action= new Action();
    FeedBackManager feedBackManager =new FeedBackManager();

    @Override
    public void onTestStart(ITestResult result) {
        feedBackManager.createExtentTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            try {
                feedBackManager.log(Status.PASS, result.getName() + " Testi Başarı ile sonlanmıştır");
                //feedBackManager.log(result.getName()+"Testi", "ITestResult="+result.getStatus()+"ile Sonlanmıştır", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                feedBackManager.log(Status.FAIL,MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
                feedBackManager.log(Status.FAIL,MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
                //feedBackManager.log(result.getName()+" Testi", "ITestResult="+result.getStatus()+" ile Sonlanmıştır.","HataKodu:"+result.getThrowable().toString());

                String imgPath = action.screenShot(BaseClass.getDriver(), result.getName());

                feedBackManager.testFailScreenshot(imgPath);

                /*String fileName = System.getProperty(System.getProperty("user.dir")+ File.separator+"screenShots"+File.separator+"failShot");
                File f = ((TakesScreenshot) BaseClass.getDriver()).getScreenshotAs((OutputType.FILE));
                FileUtils.copyFile(f, new File(fileName + ".png"));

                test.fail("ScreenShot is Attached", MediaEntityBuilder.createScreenCaptureFromPath(fileName + ".png").build());*/

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (result.getStatus() == ITestResult.SKIP) {
            try {
                feedBackManager.log(Status.SKIP, "Skipped Test case is: " + result.getName());
                //feedBackManager.log(result.getName()+"Testi", result.getStatus()+"ile Sonlanmıştır",result.getThrowable().toString());
            } catch (Exception e) {
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
