package dataproviders;

import org.testng.annotations.DataProvider;
import utility.excel.ExcelRead;

public class DataProviderClass {

    ExcelRead obj = new ExcelRead();

    @DataProvider(name = "Constants")
    public Object[][] getConstants() {
        return obj.getData("Constants");
    }

}
