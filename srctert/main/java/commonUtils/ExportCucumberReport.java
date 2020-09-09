package commonUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.util.Calendar;

/**
 * Created by ankitarora on 02/07/18.
 */

public class ExportCucumberReport {

	private static String reportPathArg;
	private static String testRunName;
	public static String[] Spreadsheet_Headers = { "Feature", "Test Scenario", "Status", "Duration(secs)",
			"Test Steps" };
	private static FileOutputStream fileOut;
	private static CreationHelper createHelper;
	private static Sheet sheet1;
	private static Row row;
	private static final String REPORT_DIR_NAME = "Cucumber_Reports";
	private static final String TOTAL_TESTS_COUNT_LABEL = "Total Tests  Count : ";
	private static final String TOTAL_PASSED_COUNT_LABEL = "Total Passed  Count : ";
	private static final String TOTAL_FAILED_COUNT_LABEL = "Total Failed  Count : ";
	private static final String TOTAL_DURATION_LABEL = "Total Duration : ";
	private static short index = 5;
	private static String reportName;

	static ParseCucumberReport parseCucumberReport;

	public static void main(String[] args) throws Exception {
		if (args.length > 0) {
			reportPathArg = args[0];
			testRunName = args[1];
		} else {
			System.err.println("CLI argument not present or null");
			System.exit(1);
		}
		Workbook excelSpreadsheet = ExcelUtils.createNewExcelObject();
		createHelper = excelSpreadsheet.getCreationHelper();
		sheet1 = ExcelUtils.createNewSheetObject(excelSpreadsheet, testRunName);
		row = ExcelUtils.setupSpreadsheetHeader(excelSpreadsheet, createHelper, sheet1, index, Spreadsheet_Headers);
		CellStyle style = excelSpreadsheet.createCellStyle();
		style.setWrapText(true);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		parseCucumberReport = new ParseCucumberReport(reportPathArg);

		row = sheet1.createRow((short) 0);
		createCellAndSetValueWithStyle(row, (short) 0, style, TOTAL_TESTS_COUNT_LABEL);
		createCellAndSetValueWithStyle(row, (short) 1, style,
				(String.valueOf(parseCucumberReport.getTotalTestScenariosCount())));
		row = sheet1.createRow((short) 1);
		createCellAndSetValueWithStyle(row, (short) 0, style, TOTAL_PASSED_COUNT_LABEL);
		createCellAndSetValueWithStyle(row, (short) 1, style,
				((String.valueOf(parseCucumberReport.getTotalPassedCount()))));
		row = sheet1.createRow((short) 2);
		createCellAndSetValueWithStyle(row, (short) 0, style, TOTAL_FAILED_COUNT_LABEL);
		createCellAndSetValueWithStyle(row, (short) 1, style,
				((String.valueOf(parseCucumberReport.getTotalFailuesCount()))));
		row = sheet1.createRow((short) 3);
		createCellAndSetValueWithStyle(row, (short) 0, style, TOTAL_DURATION_LABEL);
		createCellAndSetValueWithStyle(row, (short) 1, style,
				((String.valueOf(parseCucumberReport.getTotalTestsDuration() / 60) + " minutes")));
		row = sheet1.createRow((short) 4);

		for (int i = 0; i < parseCucumberReport.getTotalTestScenariosCount(); i++) {
			row = sheet1.createRow((short) index + 1);
			createCellAndSetValueWithStyle(row, (short) 0, style, parseCucumberReport.getTestFeatureName(i));
			createCellAndSetValueWithStyle(row, (short) 1, style, parseCucumberReport.getTestCaseName(i));
			createCellAndSetValueWithStyle(row, (short) 2, style, parseCucumberReport.getTestCaseStatus(i));
			createCellAndSetValueWithStyle(row, (short) 3, style, parseCucumberReport.getTestCaseDuration(i));
			createCellAndSetValueWithStyle(row, (short) 4, style, parseCucumberReport.getTestCaseStepsDetails(i));
			index++;
		}
		int j = 0;
		for (String header : Spreadsheet_Headers) {
			sheet1.autoSizeColumn(j);
			j++;
		}
		// Calculated the column width size and passing a fixed number below
		// instead of auto-size for better formatting
		sheet1.setColumnWidth(1, 16000);
		String currentTimetamp = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
		reportName = (currentTimetamp.split(",")[0].trim().concat("_" + testRunName + "_")).replaceAll("\\s+", "");
		ExcelUtils.saveAsExcelFile(excelSpreadsheet, fileOut, reportName, REPORT_DIR_NAME);
	}

	/**
	 * Creates & returns a new excel workbook object
	 * 
	 * @param cellRow    Row object of the spreadsheet
	 * @param cellIndex  Cell starting index position
	 * @param cellStyle  Formatted Style object for Cell
	 * @param value      Value to be set in Cell
	 * @throws Exception General exception to catch multiple
	 */
	public static void createCellAndSetValueWithStyle(Row cellRow, short cellIndex, CellStyle cellStyle, String value)
			throws Exception {
		Cell cell;
		try {
			cell = ExcelUtils.createCellWithStyleFormatting(cellRow, cellIndex, cellStyle);
			cell.setCellValue(createHelper.createRichTextString(value));
		} catch (Exception e) {
			throw new Exception("FAIL: There was an exception with creating new cell object (" + e + ")");
		}
	}

}
