package commonUtils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

/**
 * Created & Updated by ankitarora on 02/07/18.
 */

public class ParseCucumberReport {

	private int totalTestCaseCount;
	private static ArrayList<String> TestCase_Name = new ArrayList<String>();
	private static ArrayList<String> TestCase_Duration = new ArrayList<String>();
	private static ArrayList<String> TestCase_Status = new ArrayList<String>();
	private static ArrayList<String> TestCase_StepsDetailed = new ArrayList<String>();
	private static ArrayList<String> TestFeature_Name = new ArrayList<String>();
	private static int totalRunDurationInSeconds;
	private static int totalFailures;

	public ParseCucumberReport(String reportPath) {
		parseXML(reportPath);
	}

    /**
     * Parse Cucumber report and generate key test results components as standalone entity
     * 
     * @param  reportPath   Path of the xml based cucumber file
     */	
	public void parseXML(String reportPath) {

		try {
			File fXmlFile = new File(System.getProperty("user.dir").concat(reportPath));
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();
			
			NodeList tSuite = doc.getElementsByTagName("testsuite");
			Node suiteNode = tSuite.item(0);
			Element suiteElement = (Element) suiteNode;
			
			totalRunDurationInSeconds = Integer.valueOf(suiteElement.getAttribute("time").split("\\.")[0]);
			totalFailures = Integer.valueOf(suiteElement.getAttribute("failures"));
			
			NodeList nList = doc.getElementsByTagName("testcase");
			totalTestCaseCount = nList.getLength();

			for (int temp = 0; temp < totalTestCaseCount; temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					TestFeature_Name.add(eElement.getAttribute("classname"));
					TestCase_Name.add(eElement.getAttribute("name"));
					TestCase_Duration.add(eElement.getAttribute("time"));
					if (eElement.getElementsByTagName("failure").getLength() > 0) {
						TestCase_Status.add("Failed");
						TestCase_StepsDetailed.add(eElement.getElementsByTagName("failure").item(0).getTextContent());
					} else {
						TestCase_Status.add("Passed");
						TestCase_StepsDetailed
								.add(eElement.getElementsByTagName("system-out").item(0).getTextContent());

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * Returns total test duration time in seconds
     * 
     * @return  totalRunDurationInSeconds   Test duration in seconds
     */	
	public int getTotalTestsDuration() {
		return totalRunDurationInSeconds;
	}
   
	/**
     * Returns total scenarios count
     * 
     * @return  totalTestCaseCount   Returns count of total test scenarios
     */		
	public int getTotalTestScenariosCount() {
		return totalTestCaseCount;
	}

	/**
     * Returns total failed scenarios count
     * 
     * @return  totalFailures   Returns count of total failed test scenarios
     */		
	public int getTotalFailuesCount() {
		return totalFailures;
	}

	/**
     * Returns total passed scenarios count
     * 
     * @return  Returns count of total passed test scenarios
     */		
	public int getTotalPassedCount() {
		return totalTestCaseCount - totalFailures;
	}

	/**
     * Returns test feature name based on index value
     * 
     * @param   index             Index value of the test feature name to return
     * @return  TestFeature_Name  Returns the test feature name
     */			
	public String getTestFeatureName(int index) {
		return TestFeature_Name.get(index).toString();
	}

	/**
     * Returns test case name based on index value
     * 
     * @param   index             Index value of the test case name to return
     * @return  TestCase_Name     Returns the test case name
     */		
	public String getTestCaseName(int index) {
		return TestCase_Name.get(index).toString();
	}

	/**
     * Returns test case execution duration in seconds based on index value
     * 
     * @param   index             Index value of the test case duration to return
     * @return  TestCase_Name     Returns the duration of test case execution in seconds
     */		
	public String getTestCaseDuration(int index) {
		return TestCase_Duration.get(index).split("\\.")[0].toString();
	}

	/**
     * Returns test case status based on index value
     * 
     * @param   index             Index value of the test case status to return
     * @return  TestCase_Name     Returns the test case status as Passed or Failed
     */	
	public String getTestCaseStatus(int index) {
		return TestCase_Status.get(index);
	}

    /**
     * Returns test case steps details based on index value
     * 
     * @param   index             Index value of the test case steps to return
     * @return  TestCase_Name     Returns the test case steps
     */
	public String getTestCaseStepsDetails(int index) {
		return TestCase_StepsDetailed.get(index).toString();
	}
}
