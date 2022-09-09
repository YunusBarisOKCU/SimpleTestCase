package utility.excel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelRead extends ExcelBase {

    private String inputFileName ="TestInput.xlsx";

    public ExcelRead() {
        initialize();
    }

    protected void initialize() {

        try {
            inputStream = new FileInputStream(filePath+inputFileName);
            workBook = new XSSFWorkbook(inputStream);
            sheet = workBook.getSheetAt(0);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object[][] getData(String sheetName) {

        int rows = getRowCount(sheetName);
        int column = getColumnCount(sheetName);
        int actRows = rows - 1;

        Object[][] data = new Object[actRows][column];

        for (int i = 0; i < actRows; i++) {
            for (int j = 0; j < column; j++) {
                data[i][j] = getCellData(sheetName, j, i + 2);
            }
        }
        return data;
    }

    private String getCellData(String sheetName,int colNum,int rowNum){
        try{
            int index = workBook.getSheetIndex(sheetName);
            sheet = workBook.getSheetAt(index);
            row = sheet.getRow(rowNum-1);
            cell = row.getCell(colNum);

            if(rowNum <=0)
                return "";
            if(index==-1)
                return "";
            if(row==null)
                return "";
            if(cell==null)
                return "";

            if(cell.getCellType().name().equals("STRING"))
                return cell.getStringCellValue();
            else if(cell.getCellType().name().equals("NUMERIC") || cell.getCellType().name().equals("FORMULA") ){
                String cellText  = String.valueOf(cell.getNumericCellValue());
                return cellText;
            }
            else if(cell.getCellType().name().equals("BLANK"))
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        }
        catch(Exception e){

            e.printStackTrace();
            return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
        }
    }

}
