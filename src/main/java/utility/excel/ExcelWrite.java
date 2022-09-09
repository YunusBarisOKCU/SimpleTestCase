package utility.excel;

import java.io.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


public class ExcelWrite extends ExcelBase {
    private String outputFileName ="TestOutput.xlsx";
    private String sheetName="TestReport";


    public ExcelWrite() {
        initialize();
    }

    @Override
    protected void initialize() {
        try{

            file = new File(filePath+outputFileName);

            if(!file.isFile()){
                file.createNewFile();
                workBook = new XSSFWorkbook();
            }
            else {
                inputStream = new FileInputStream(file);
                if(file.length()>0)
                    workBook = new XSSFWorkbook(inputStream);
                else
                    workBook = new XSSFWorkbook();
                inputStream.close();
            }

            sheet = workBook.createSheet(sheetName+" "+java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm")));
            Row firstRow=sheet.createRow(0);
            firstRow.createCell(0).setCellValue("Test Step");
            firstRow.createCell(1).setCellValue("Result");
            firstRow.createCell(2).setCellValue("Description");

            streamFlush();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeToExcel(String[] dataToWrite){
        export(dataToWrite);
        streamFlush();
    }

    private void export(String[] dataToWrite) {

        int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
        Row row = sheet.getRow(0);
        Row newRow = sheet.createRow(rowCount+1);

        for(int j = 0; j < row.getLastCellNum(); j++){

            Cell cell = newRow.createCell(j);
            cell.setCellValue(dataToWrite[j]);

        }
    }

    private void streamFlush(){
        try {
            outputStream = new FileOutputStream(file);
            workBook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
