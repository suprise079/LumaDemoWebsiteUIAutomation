package utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ExcelFunctions {


    //Get Excel sheet with testing data
    private static XSSFSheet getExcelSheet(){
        XSSFWorkbook workbook = null;
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/userData.xlsx");
            workbook = new XSSFWorkbook(fis);
            return  workbook.getSheetAt(0);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }

    }

    //get the column range of the merged cells, to know which range of cells to read
    private static int[] getColumsRange(String columnTitle){

        XSSFSheet sheet = getExcelSheet();

        int startColumn = 0;
        int endColumn = 0;

        //get the merged regions (cells)
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        for(CellRangeAddress mergedRegion : mergedRegions) {
            //Get the parameters of the merged cells
            int firstRow = mergedRegion.getFirstRow();
            int lastRow = mergedRegion.getLastRow();
            int firstColumn = mergedRegion.getFirstColumn();
            int lastColumn = mergedRegion.getLastColumn();

            //get the value of the first cell in the merged range
            Row row = sheet.getRow(firstRow);
            String cellValue = row.getCell(firstColumn).getStringCellValue();

            //if the value of the first cell is equal to the column title, then get the range of the merged cells
            if (cellValue.contains(columnTitle)){
                startColumn = firstColumn;
                endColumn = lastColumn;
                System.out.println("Start column is " + startColumn);
                System.out.println("End column is " + endColumn);
            }
        }

        return new int[]{startColumn, endColumn};
    }

    //Get the range of rows to read by using the start and end text
    private static int[] getRowsRange(String startText, String endText){
        //get the sheet
        XSSFSheet sheet = getExcelSheet();
        //get the row count
        int rowCount = sheet.getLastRowNum();
        //get the column count
        int colCount = sheet.getRow(0).getLastCellNum();

        //get the start row
        int startRow = 0;
        for (int i = 0; i <= rowCount; i++) {
            try {
                // if the cell has the start text, then get the row number
                if (sheet.getRow(i).getCell(0).getStringCellValue().contains(startText)) {
                    startRow = i;
                    System.out.println("Start row is " + startRow);
                    break;
                }
            } catch (Exception e) {
                continue;
            }
        }

        //get the end row
        int endRow = 0;
        for (int i = startRow; i <= rowCount; i++) {
            try {
                // if the cell has the end text, then get the row number
                if (sheet.getRow(i).getCell(0).getStringCellValue().contains(endText)) {
                    endRow = i;
                    System.out.println("End row is " + endRow);
                    break;
                }
            } catch (Exception e) {
                continue;
            }
        }

        return new int[]{startRow, endRow};
    }

    /**
     * Get the data from the sheet
     * @param startText - Text in the first column of the row where the data starts
     * @param EndText - Text in the first column of the row where the data ends
     * @param dataHeader - The umbrella title of the data to be read
     *
     * @return - 2D array of the data, with list of rows and columns : [row][column].
     */
    public static String[][] getSheetData(String startText, String EndText, String dataHeader){

        //get the sheet
        XSSFSheet sheet = getExcelSheet();

        //get the range of rows and columns to read
        int[] rowsRange = getRowsRange(startText, EndText);
        int[] columnsRange = getColumsRange(dataHeader);
        int startRow = rowsRange[0];
        int endRow = rowsRange[1];
        int startColumn = columnsRange[0];
        int endColumn = columnsRange[1];

        // get the number of rows and columns to read
        int colCount = endColumn - startColumn;
        int rowCount = endRow - startRow - 1;

        //get the data
        String[][] data = new String[rowCount+2][colCount+2];
        int rowCounter = 0;
        int columnCounter = 0;
        //loop  through the rows
        for (int i = startRow; i <= endRow; i++) {
            //loop through the columns
            for (int j = startColumn; j <= endColumn; j++) {
                data[rowCounter][columnCounter] = sheet.getRow(i).getCell(j).getStringCellValue();
                columnCounter++;
            }
            //Add test scenario to the first column
            data[rowCounter][columnCounter] = sheet.getRow(i).getCell(1).getStringCellValue();
            columnCounter = 0;
            rowCounter++;
        }
        return data;
    }
}
