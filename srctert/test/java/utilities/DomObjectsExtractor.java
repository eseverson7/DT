package utilities;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.Constants;
import commonUtils.ExcelUtils;

/**
 * Created by aarora on 7/07/17.
 * 
 */
public class DomObjectsExtractor {
	private Driver driver;
	private WebDriver webDriver;
	private final static Logger LOGGER = Logger.getLogger(DomObjectsExtractor.class.getName());
	HttpUtils httpUtils = new HttpUtils();

	public DomObjectsExtractor(Driver driver) throws IOException {
        this.driver = driver;
        webDriver = driver.getDriver();
        PageFactory.initElements(webDriver, this);
	}

	private static List<String> elementName;
	private static List<String> tagName;
	private static List<String> id;
	private static List<String> className;
	private static List<String> xpath;
	private static List<String> cssSelector;
	private static List<String> title;
	private static List<String> href;
	private static List<String> name;
	private static List<String> attributes;
	public static String[] Spreadsheet_Headers = { "Object#", "Element Name", "Tag Name", "Id", "Class Name", "href",
			"Name", "Title", "xPath", "Css Selector", "Attributes" };
	private static FileOutputStream fileOut;
	private static CreationHelper createHelper;
	private static Sheet sheet1;
	private static Row row;
	private static final String reportDirName = "domReports";

	/**
	 * Asserts that an element with specific text is visible a page with the
	 * path
	 *
	 * @param pageName   Page name to set the worksheet name inside excel
	 * @param excelName   Name of the excel spreadsheet
	 * @throws Exception 
	 */
	public void parseDomCreateExcel(String pageName, String excelName) throws Exception {
		LOGGER.info("parseDomCreateExcel started");
		
		Elements totalElements;
		elementName = new ArrayList<String>();
		tagName = new ArrayList<String>();
		id = new ArrayList<String>();
		className = new ArrayList<String>();
		xpath = new ArrayList<String>();
		cssSelector = new ArrayList<String>();
		title = new ArrayList<String>();
		href = new ArrayList<String>();
		name = new ArrayList<String>();
		attributes = new ArrayList<String>();

		int index = 0;
		String url = webDriver.getCurrentUrl();
		Document doc = Jsoup.connect(url).timeout(Constants.DEFAULT_MILLISEC_WAIT).validateTLSCertificates(false).get();

		Workbook excelSpreadsheet = ExcelUtils.createNewExcelObject();
		createHelper = excelSpreadsheet.getCreationHelper();
		sheet1 = ExcelUtils.createNewSheetObject(excelSpreadsheet, pageName);
		row = ExcelUtils.setupSpreadsheetHeader(excelSpreadsheet, createHelper,sheet1,Spreadsheet_Headers);
		totalElements = doc.getAllElements();
		LOGGER.info("Total Elements Identified: " + totalElements.size());

		for (int i = 0; i < totalElements.size(); i++) {
			if ((totalElements.get(i).hasAttr(Constants.CLASS) || totalElements.get(i).hasAttr(Constants.ID))
					&& (totalElements.get(i).hasText() && totalElements.get(i).childNodeSize() < 5
							&& !totalElements.get(i).tagName().contains("li"))) {

				elementName.add(index, totalElements.get(i).text());
				tagName.add(index, totalElements.get(i).tagName());
				id.add(index, totalElements.get(i).id());
				className.add(index, totalElements.get(i).className());
				xpath.add(index,
						"//" + totalElements.get(i).tagName() + "[@class='" + totalElements.get(i).className() + "']");
				title.add(index, totalElements.get(i).attr(Constants.TITLE));
				href.add(index, totalElements.get(i).attr(Constants.ATTRIBUTE_HREF));
				name.add(index, totalElements.get(i).text());
				attributes.add(index, totalElements.get(i).attributes().toString());
				try {
					if (!totalElements.get(i).className().contains("/")
							&& !totalElements.get(i).className().contains("{")
							&& !totalElements.get(i).className().contains("...")) {
						if (totalElements.get(i).cssSelector().split(">").length == 1) {
							cssSelector.add(index, totalElements.get(i).cssSelector().split(">")[0]);
						} else {
							cssSelector.add(index, totalElements.get(i).cssSelector()
									.split(">")[totalElements.get(i).cssSelector().split(">").length - 1]);
						}
					} else {
						continue;
					}
				} catch (Exception e) {
					index++;
					LOGGER.info(e.getMessage());
					continue;
				}
				row = sheet1.createRow((short) index + 1);
				row.createCell(0).setCellValue(createHelper.createRichTextString(Integer.toString(index + 1)));
				row.createCell(1).setCellValue(createHelper.createRichTextString(elementName.get(index).toString()));
				row.createCell(2).setCellValue(createHelper.createRichTextString(tagName.get(index).toString()));
				row.createCell(3).setCellValue(createHelper.createRichTextString(id.get(index).toString()));
				row.createCell(4).setCellValue(createHelper.createRichTextString(className.get(index).toString()));
				row.createCell(5).setCellValue(createHelper.createRichTextString(href.get(index).toString()));
				row.createCell(6).setCellValue(createHelper.createRichTextString(name.get(index).toString()));
				row.createCell(7).setCellValue(createHelper.createRichTextString(title.get(index).toString()));
				row.createCell(8).setCellValue(createHelper.createRichTextString(xpath.get(index).toString()));
				row.createCell(9).setCellValue(createHelper.createRichTextString(cssSelector.get(index).toString()));
				row.createCell(10).setCellValue(createHelper.createRichTextString(attributes.get(index).toString()));

				index++;
			}
		}
		ExcelUtils.saveAsExcelFile(excelSpreadsheet, fileOut, excelName, reportDirName);
		LOGGER.info("parseDomCreateExcel completed");
	}

}