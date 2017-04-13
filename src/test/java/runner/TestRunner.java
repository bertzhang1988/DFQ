package runner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TimeZone;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;
import utility.Function;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features/QuoteScenario.feature", glue = { "stepdefinitions" }, plugin = {
		"html:target/cucumber-html-report" }, tags = { "@case1,@case2" })
public class TestRunner {
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;

	@BeforeClass
	public static void generate_report() throws Throwable {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet();
		String[] TitleLine = { "Scenario", "Quote ID", " Pro number", "Pick Up preference Number" };
		Row titleRow = sheet.createRow(0);
		int ColumnOfFirstline = 0;
		for (String value : TitleLine) {
			Cell CellOfFirstRow = titleRow.createCell(ColumnOfFirstline);
			CellOfFirstRow.setCellValue(value);
			ColumnOfFirstline++;
		}
	}

	@AfterClass
	public static void generateReport() {
		String CDate = Function.GetTimeValue(TimeZone.getDefault().getID());
		File folder = new File("./CustomizedReport/" + CDate);
		folder.mkdir();
		File report = new File(folder, "DFQ.xlsx");
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(report);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			workbook.write(os);
			workbook.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
