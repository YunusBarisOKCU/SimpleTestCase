package dataporter;

import java.io.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class ExportToXL {
    //TODO ExportToXL sınıfı dosya yazım işlemleri için exel Base sınıfını kullansın

    String filePath=System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TestOutput.xlsx";
    String fileName="TestOutput.xlsx";
    String sheetName="TestReport";

    File file = null;
    FileInputStream inputStream = null;
    FileOutputStream outputStream = null;

    Workbook workBook = null;
    Sheet sheet = null;

    public ExportToXL() {
        try{
            //TODO initialize methodu okuma yazma işlemleri yapılırken Dosya, sayfa yada sayfa satır başlıkları olmaması gibi durumlara karşı uygun hale gelsin
            this.file = new File(filePath);

            if(!file.isFile()){
                file.createNewFile();
                this.workBook = new XSSFWorkbook();
            }
            else {
                this.inputStream = new FileInputStream(file);
                if(file.length()>0)
                    this.workBook = new XSSFWorkbook(inputStream);
                else
                    this.workBook = new XSSFWorkbook();
                inputStream.close();
            }

            this.sheet = workBook.createSheet(sheetName+" "+java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm.ss")));
            Row firstRow=sheet.createRow(0);
            firstRow.createCell(0).setCellValue("Test Step");
            firstRow.createCell(1).setCellValue("Result");
            firstRow.createCell(2).setCellValue("Description");

            streamFlush();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void exportToExcel(String[] dataToWrite) throws IOException {
        Export(dataToWrite);
        streamFlush();
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



    private void streamFlush(){
        try {
            this.outputStream = new FileOutputStream(file);
            workBook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
