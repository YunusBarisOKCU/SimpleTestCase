package utility.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public abstract class ExcelBase {

    protected String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\";
    protected File file = null;
    protected FileInputStream inputStream = null;
    protected FileOutputStream outputStream = null;

    protected Workbook workBook = null;
    protected Sheet sheet = null;
    protected Row row = null;
    protected Cell cell = null;

    protected abstract void initialize();

    protected int getRowCount(String sheetName){
        int index = workBook.getSheetIndex(sheetName);
        if(index==-1)
            return 0;
        else{
            sheet = workBook.getSheetAt(index);
            int number=sheet.getLastRowNum()+1;
            return number;
        }

    }

    protected int getColumnCount(String sheetName){

        if(!isSheetExist(sheetName))
            return -1;

        sheet = workBook.getSheet(sheetName);
        row = sheet.getRow(0);

        if(row==null)
            return -1;

        return row.getLastCellNum();

    }

    protected boolean isSheetExist(String sheetName){
        int index = workBook.getSheetIndex(sheetName);
        if(index==-1){
            index= workBook.getSheetIndex(sheetName.toUpperCase());
            if(index==-1)
                return false;
            else
                return true;
        }
        else
            return true;
    }




}
