package dataPorter;

import java.io.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class ExportToXL {

    String filePath=System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TestOutput.xlsx";
    String fileName="TestOutput.xlsx";
    String sheetName="TestReport";

    File file = null;
    FileInputStream inputStream = null;
    FileOutputStream outputStream = null;

    Workbook workBook = null;
    Sheet sheet = null;

    public void exportToExel(String[] dataToWrite) throws IOException {
        Initialize();
        Export(dataToWrite);
        StreamFlush();
    }

    private void Initialize(){
        try{
            //TODO -RW işlemlerini Dosya, sayfa yada sayfa başlıkları olmaması gibi durumlara karşı uygun hale getir.
            this.file = new File(filePath);
            this.inputStream = new FileInputStream(file);
            this.workBook = new XSSFWorkbook(inputStream);
            this.sheet = workBook.getSheet(sheetName);
            this.outputStream = new FileOutputStream(file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void Export(String[] dataToWrite) throws IOException {
        int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
        Row row = sheet.getRow(0);
        Row newRow = sheet.createRow(rowCount+1);

        for(int j = 0; j < row.getLastCellNum(); j++){

            //Fill data in row
            Cell cell = newRow.createCell(j);
            cell.setCellValue(dataToWrite[j]);

        }
    }



    private void StreamFlush(){
        try {
            workBook.write(outputStream);
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
