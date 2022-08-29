package utility.managers;

import dataporter.ExportToXL;

import java.io.IOException;

public class FeedBackManager  {
    private static ExportToXL exel=new ExportToXL();

    /*public FeedBackManager() {
        exel=new ExportToXL();
    }*/


    public void infoLog(String step, String result, String s) throws IOException {
        String logInfo=step+"  "+result+"  "+s;

        ExtentManager.extentInfo(logInfo);

        Log.info(logInfo);
        exel.exportToExcel(new String[]{step, result, s});
    }

    public void Log(String test, String result, String s) throws IOException {
        String logInfo=test+"  "+result+"  "+s;

        Log.info(logInfo);
        exel.exportToExcel(new String[]{test, result, s});
    }

}
