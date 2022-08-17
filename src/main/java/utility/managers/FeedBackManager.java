package utility.managers;

import dataPorter.ExportToXL;

import java.io.IOException;

public class FeedBackManager  {
    public static ExportToXL exel;

    public FeedBackManager() {
        exel=new ExportToXL();
    }


    public void infoLog(String step, String result, String s) throws IOException {
        String logInfo=step+"  "+result+"  "+s;

        ExtentManager.extentInfo(logInfo);

        Log.info(logInfo);
        exel.exportToExel(new String[]{step, result, s});
    }

    public void Log(String test, String result, String s) throws IOException {
        String logInfo=test+"  "+result+"  "+s;

        Log.info(logInfo);
        exel.exportToExel(new String[]{test, result, s});
    }

}
