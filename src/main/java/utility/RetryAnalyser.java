package utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {
    int count=0;
    int retryCount=0;//hata sonrasındaki tekrar deneme sayısı

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count<retryCount){
            count++;
            return true;
        }
        return false;
    }
}
