package commonUtils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * Created by aaronbriel on 7/10/17.
 * Extended by aarora
 */
public class ExcelUtils {

    private final static Logger LOGGER = Logger.getLogger(ExcelUtils.class.getName());
    private String filePath;

    public ExcelUtils(String file) {
        filePath = file;
    }

    private static FileOutputStream fileOut;
    private static final String OVC_EXCEL_DATA = "OvcData";
    private static final String OVC_EXCEL_FOLDER = "data";

    /**
     * Returns the value of the cell contained in specified row and column
     *
     * @param rowValue    The row of the cell
     * @param columnValue The column of the cell
     * @param sheetValue  The sheet of the cell
     * @return String     The value of the cell
     * @throws Exception General exception to catch multiple
     */
    public String getCellValue(String rowValue, String columnValue, String sheetValue) throws Exception {

        String cellValue;
        int sheetIndex = getSheetNumber(sheetValue);
        int rowIndex = getRowNumber(rowValue, sheetIndex);
        int columnIndex = getColumnNumber(columnValue, sheetIndex);
        cellValue = getCellValue(rowIndex, columnIndex, sheetIndex);

        return cellValue;
    }

    /**
     * Returns the value of the cell contained in specified row and column
     *
     * @param rowIndex    The row of the cell
     * @param columnIndex The column of the cell
     * @param sheetIndex  The sheet of the cell
     * @return String     The value of the cell
     * @throws Exception General exception to catch multiple
     */
    public String getCellValue(int rowIndex, int columnIndex, int sheetIndex) throws Exception {

        String value = "";
        DataFormatter fmt = new DataFormatter();

        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            //TODO: Assumes top row will be used for column descriptions for data
            Row row = sheet.getRow(rowIndex);
            Cell cell = row.getCell(columnIndex);
            value = fmt.formatCellValue(cell);
            file.close();

        } catch (Exception e) {
            throw new Exception("FAIL: There was an exception with extracting the cell value (" + e + ")");
        }
        return value;
    }

    public void setCellValue(int sheetIndex, int rowIndex, int columnIndex, String cellValue) throws Exception {
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            //TODO: Assumes top row will be used for column descriptions for data
            Row row = sheet.getRow(rowIndex);
            Cell cell = row.getCell(columnIndex);
            cell.setCellValue(cellValue);

            saveAsExcelFile(workbook, fileOut, OVC_EXCEL_DATA, OVC_EXCEL_FOLDER);
        } catch (Exception e) {
            throw new Exception("FAIL: There was an unhandled exception writing " + cellValue + " to cell " + e);
        }
    }

    /**
     * Returns column number of top row value
     *
     * @param columnValue The cell value of the top row to get the column for
     * @return int          The column number of the value
     * @throws Exception General exception to catch if cellValue not found
     */
    public int getColumnNumber(String columnValue, int sheetIndex) throws Exception {

        int column = -1;
        String cellValue;


        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            //TODO: Assumes top row will be used for column descriptions for data
            Row row = sheet.getRow(0);
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                    cellValue = Integer.toString((int) cell.getNumericCellValue());
                    if (cellValue.equalsIgnoreCase(columnValue)) {
                        column = cell.getColumnIndex();
                        break;
                    }
                } else if (cell.getCellTypeEnum() == CellType.STRING) {
                    if (cell.getStringCellValue().equalsIgnoreCase(columnValue)) {
                        column = cell.getColumnIndex();
                        break;
                    }
                }
            }
            file.close();

        } catch (Exception e) {
            throw new Exception("FAIL: There was an exception with extracting the column number (" + e + ")");
        }

        if (column == -1) {
            throw new Exception("None of the cells in the first row contained \"" + columnValue + "\".");
        }

        return column;
    }

    public int getSheetNumber(String sheetValue) throws Exception {
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            return workbook.getSheetIndex(sheetValue);
        } catch (Exception e) {
            throw new Exception("FAIL: There was an exception with extracting the sheet number (" + e + ")");
        }
    }

    /**
     * Returns row number of left-most column value
     *
     * @param rowValue The cell value of the left-most column to get the row for
     * @return int  The row number of the value
     * @throws Exception General exception to catch if cellValue not found
     */
    public int getRowNumber(String rowValue, int sheetIndex) throws Exception {

        int rowIndex = -1;
        String cellValue;

        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(sheetIndex);

            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                        cellValue = Integer.toString((int) cell.getNumericCellValue());
                        if (cellValue.equalsIgnoreCase(rowValue)) {
                            rowIndex = row.getRowNum();
                            break;
                        }
                    } else if (cell.getCellTypeEnum() == CellType.STRING) {
                        if (cell.getStringCellValue().equalsIgnoreCase(rowValue)) {
                            rowIndex = row.getRowNum();
                            break;
                        }
                    }

                }
            }
            file.close();

        } catch (Exception e) {
            throw new Exception("FAIL: There was an exception with extracting the row number (" + e + ")");
        }

        if (rowIndex == -1) {
            throw new Exception("None of the cells in the first column contained \"" + rowValue + "\".");
        }

        return rowIndex;
    }

    /**
     * Creates and returns a new excel workbook object
     *
     * @return Workbook     Object of Workbook
     * @throws Exception General exception to catch multiple
     */
    public static Workbook createNewExcelObject() throws Exception {
        Workbook wb;

        try {
            wb = new HSSFWorkbook();
        } catch (Exception e) {
            throw new Exception("FAIL: There was an exception with creating new excel object (" + e + ")");
        }
        return wb;
    }

    /**
     * Creates new creation helper object to set/utilize data layer in excel
     *
     * @param wb Workbook object
     * @return Workbook     Object of Workbook
     * @throws Exception General exception to catch multiple
     */
    public static CreationHelper createExcelWorkerObject(Workbook wb) throws Exception {
        CreationHelper excelWorkerObject;
        try {
            excelWorkerObject = wb.getCreationHelper();
        } catch (Exception e) {
            throw new Exception("FAIL: There was an exception with creating new excel helper object (" + e + ")");
        }
        return excelWorkerObject;
    }

    /**
     * Creates & returns a new excel workbook object
     *
     * @param wb            Workbook object
     * @param workSheetName Name of the WorkSheet
     * @return Sheet          Object of Workbook's Sheet (worksheet)
     * @throws Exception General exception to catch multiple
     */
    public static Sheet createNewSheetObject(Workbook wb, String workSheetName) throws Exception {
        Sheet sheet;

        try {
            sheet = wb.createSheet(workSheetName);
        } catch (Exception e) {
            throw new Exception("FAIL: There was an exception with creating new worksheet object (" + e + ")");
        }
        return sheet;
    }

    /**
     * Creates a new Row, setup the column headers & returns the Row object
     *
     * @param wb                Workbook object
     * @param excelWorkerObject CreationHelper object
     * @param sheet             Excel spreadsheet sheet object
     * @param headers           Array of type String contains names of the column headers
     * @return Row                 Row object
     * @throws Exception General exception to catch multiple
     */
    public static Row setupSpreadsheetHeader(Workbook wb, CreationHelper excelWorkerObject, Sheet sheet, String[] headers) throws Exception {
        int index = 0;
        Row row;
        try {
            row = sheet.createRow((short) 0);
            for (String header : headers) {
                row.createCell(index).setCellValue(excelWorkerObject.createRichTextString(header));
                index++;
            }
        } catch (Exception e) {
            throw new Exception("FAIL: There was an exception with creating new spreadsheet header (" + e + ")");
        }
        return row;
    }

    /**
     * Creates a new Row, setup the column headers & returns the Row object
     *
     * @param wb                Workbook object
     * @param excelWorkerObject CreationHelper object
     * @param sheet             Excel spreadsheet sheet object
     * @param startingRow       Starting row index to start the row
     * @param headers           Array of type String contains names of the column headers
     * @return Row                 Row object
     * @throws Exception General exception to catch multiple
     */
    public static Row setupSpreadsheetHeader(Workbook wb, CreationHelper excelWorkerObject, Sheet sheet, short startingRow, String[] headers) throws Exception {
        int index = 0;
        Row row;
        CellStyle style = wb.createCellStyle();
        style.setWrapText(true);
        try {
            row = sheet.createRow(startingRow);
            for (String header : headers) {
                row.createCell(index).setCellValue(excelWorkerObject.createRichTextString(header));
                index++;
            }
        } catch (Exception e) {
            throw new Exception("FAIL: There was an exception with creating new spreadsheet header (" + e + ")");
        }
        return row;
    }

    /**
     * Creates & returns a new excel workbook object
     *
     * @param cellRow   Row object of the spreadsheet
     * @param cellIndex cell starting index position
     * @param cellStyle Formatted Style object for Cell
     * @return cell           Object of Row's cell
     * @throws Exception General exception to catch multiple
     */
    public static Cell createCellWithStyleFormatting(Row cellRow, short cellIndex, CellStyle cellStyle) throws Exception {
        Cell cell;

        try {
            cell = cellRow.createCell(cellIndex);
            cell.setCellStyle(cellStyle);
        } catch (Exception e) {
            throw new Exception("FAIL: There was an exception with creating new cell object (" + e + ")");
        }
        return cell;
    }


    /**
     * Calls Save As action on excel spreadsheet & append time-stamp to the filename
     *
     * @param wb         Workbook object
     * @param fileOut    FileOutputStream object
     * @param fileName   Name of the file to saved as
     * @param folderName Name of the folder where to save the file
     * @throws Exception General exception to catch multiple
     */
    public static void saveAsExcelFile(Workbook wb, FileOutputStream fileOut, String fileName, String folderName) throws Exception {
        Path currentRelativePath = Paths.get("");
        String basePath = currentRelativePath.toAbsolutePath().toString();
        try {
            createDirIfNotExist(folderName);
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                    .format(new Timestamp(System.currentTimeMillis()));
            String spreadsheetName = fileName + "_" + timeStamp + ".xls";
            fileOut = new FileOutputStream(basePath + "\\" + folderName + "\\" + spreadsheetName);
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            throw new Exception("FAIL: There was an exception with saving the excel file (" + e + ")");
        }
    }

    /**
     * Creates directory if not already exist on the file system
     *
     * @param folderName Name for the directory
     * @throws Exception General exception to catch multiple
     */
    public static void createDirIfNotExist(String folderName) throws Exception {
        File theDir = new File(folderName);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            LOGGER.info("creating directory: " + theDir.getName());
            boolean result = false;
            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException e) {
                LOGGER.info(e.getMessage());
            }
            if (result) {
                LOGGER.info("DIR created");
            }
        }
    }
}
