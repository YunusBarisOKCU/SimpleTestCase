package dataPorter;

import org.testng.annotations.DataProvider;

public class ImportFromXL {

    ExelBase obj = new ExelBase();


    @DataProvider(name = "Constants")
    public Object[][] getConstants() {
        // Totals rows count
        int rows = obj.getRowCount("Constants");
        // Total Columns
        int column = obj.getColumnCount("Constants");
        int actRows = rows - 1;

        Object[][] data = new Object[actRows][column];

        for (int i = 0; i < actRows; i++) {
            for (int j = 0; j < column; j++) {
                data[i][j] = obj.getCellData("Constants", j, i + 2);
            }
        }
        return data;
    }

}
