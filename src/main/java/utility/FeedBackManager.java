package utility;

import com.aventstack.extentreports.Status;


import com.aventstack.extentreports.markuputils.Markup;
import utility.excel.ExcelWrite;
import utility.feedbacktools.ExtentManager;
import utility.feedbacktools.Log;

import java.io.IOException;

public class FeedBackManager  {
    private static ExcelWrite exel=new ExcelWrite();

    public void createExtentTest (String testName){
        ExtentManager.createTest(testName);
    }

    public void infoLog(String step, String result, String s){
        String logInfo=step+"  "+result+"  "+s;

        ExtentManager.extentInfo(logInfo);

        Log.info(logInfo);
        exel.writeToExcel(new String[]{step, result, s});
    }

    public void log(Status testStatus, Markup markup){
        String logInfo=testStatus.toString()+"  "+markup.getMarkup()+"  ";
        ExtentManager.extentLog(testStatus,markup);
        Log.info(logInfo);
        exel.writeToExcel(new String[]{testStatus.toString(), markup.getMarkup(), ""});
    }

    public void log(Status testStatus, String detail){
        String logInfo=testStatus.toString()+"  "+detail+"  ";
        ExtentManager.extentLog(testStatus,detail);
        Log.info(logInfo);
        exel.writeToExcel(new String[]{testStatus.toString(), detail, ""});
    }


    public void testFailScreenshot(String imgPath) throws IOException {
        ExtentManager.extentFailScreenShot(imgPath);
    }
}
