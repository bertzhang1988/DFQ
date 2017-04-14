package stepdefinitions;

import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import page.ConfirmationPage;
import page.QuoteEntry;
import page.QuoteSummary;
import page.ShipmentDetails;
import page.TermsCondition;
import page.loginPage;
import page.paymentPage;
import runner.TestRunner;

public class StepDefinition extends base {

	private WebDriverWait wait;
	private loginPage lp;
	private QuoteEntry QE;
	private QuoteSummary QS;
	private ShipmentDetails SD;
	private TermsCondition TC;
	private paymentPage p;
	private ConfirmationPage CP;
	private int R;

	@Given("^setup browser \"([^\"]*)\" and navigate \"([^\"]*)\"$")
	public void setup_browser_and_navigate(String browser, String url) throws Throwable {
		driver = setupbrowser(browser);
		wait = new WebDriverWait(driver, 10);
		lp = new loginPage(driver);
		lp.NavigateToLogin(url);

	}

	@When("^Login through username\"([^\"]*)\" and password\"([^\"]*)\" and navigate to DFQ page$")
	public void login_through_username_and_password_and_navigate_to_DFQ_page(String username, String password)
			throws Throwable {
		QE = lp.LoginAndNavigateToDFQ(username, password);

	}

	@Then("^fill requester information with your location ([^\"]*)$")
	public void fill_requester_information(String location) throws Throwable {
		QE.Customerlocation.sendKeys(location);
		Thread.sleep(2000);
		driver.findElement(By.linkText(location)).click();
	}

	@Then("^select role of ([^\"]*)$")
	public void select_role(String role) throws Throwable {
		QE.SelectRole(role);
	}

	@Then("^select pick up date$")
	public void select_pick_up_date() throws Throwable {
		QE.SelectPickupDate();
	}

	@Then("^enter ship from location type ([^\"]*) and enter zip ([^\"]*)$")
	public void enter_ship_from_location_type_and_enter_zip(String type, String zip) throws Throwable {
		QE.selectDropDownBox(QE.ShipFromType, type);
		QE.ShipFromZip.sendKeys(zip);
		Thread.sleep(2000);
	}

	@Then("^enter ship to location type ([^\"]*) and enter zip ([^\"]*)$")
	public void enter_ship_to_location_type_and_enter_zip(String type, String zip) throws Throwable {
		QE.selectDropDownBox(QE.ShipToType, type);
		if (QE.ShipToZip.isEnabled())
			QE.ShipToZip.sendKeys(zip);
	}

	@Then("^set handling unit \"([^\"]*)\" and shipment size with L \"([^\"]*)\" W \"([^\"]*)\" H \"([^\"]*)\" # \"([^\"]*)\" WEIGHT \"([^\"]*)\"$")
	public void set_handling_unit_and_shipment_size_with_L_W_H_WEIGHT(String unitType, String length, String width,
			String height, String quantity, String weight) throws Throwable {
		QE.FillshipmentDetail("1", unitType, length, width, height, quantity, weight);
	}

	@Then("^click next and kick off quote summary page$")
	public void click_next_and_kick_off_quote_summary_page() throws Throwable {
		QS = QE.NextToQuoteSummary();

	}

	@Then("^select the quote and kick off shipment detail page$")
	public void select_the_quote() throws Throwable {
		SD = QS.SelectQuoteNextToShipmentDetails();
	}

	@Then("^fill the shipper information with ([^\"]*) and ([^\"]*) and ([^\"]*)$")
	public void fill_the_shipper_information_with_and(String companyname, String companyaddr, String email)
			throws Throwable {
		SD.FromCompanyName.sendKeys(companyname);
		SD.FromAddress.sendKeys(companyaddr);
		SD.FromEmail.sendKeys(email);
	}

	@Then("^fill the consignee information ([^\"]*) and ([^\"]*) and ([^\"]*)$")
	public void fill_the_consignee_information_and(String companyname, String companyaddr, String email)
			throws Throwable {
		SD.ToCompanyName.sendKeys(companyname);
		SD.ToAddress.sendKeys(companyaddr);
		SD.ToEmail.sendKeys(email);
	}

	@Then("^fill shipment product information with piece of count ([^\"]*) select Unit of measurement ([^\"]*) descroption \"([^\"]*)\"$")
	public void fill_shipment_product_information_with_descroption(String count, String unit, String description)
			throws Throwable {
		SD.pieceCount.sendKeys(count);
		SD.selectDropDownBox(SD.UnitOfMeasurement, unit);
		new Actions(driver).sendKeys(Keys.TAB).sendKeys(description).build().perform();
		// new Actions(driver).sendKeys(description).build().perform();
		// wait.until(ExpectedConditions.visibilityOf(SD.DescriptionOfShipment));
		// SD.DescriptionOfShipment.sendKeys(description);
	}

	@Then("^fill pick up information with contact name ([^\"]*) number ([^\"]*)$")
	public void fill_pick_up_information_with_contact_name_number(String contactname, String contactnum)
			throws Throwable {
		SD.PickUpContactName.sendKeys(contactname);
		SD.PickUpContactNo.sendKeys(contactnum);
	}

	@Then("^click next and kick off terms&condition page$")
	public void click_next_and_kick_off_terms_condition_page() throws Throwable {
		TC = SD.NextToTermsCondition();
	}

	@Then("^accept terms and condition and switch to payment window$")
	public void accept_terms_and_condition_and_switch_to_payment_window() throws Throwable {
		TC.acceptTermsAndCondition();
		p = TC.NextToPaymentPage();
	}

	@Then("^select payment card AND confirm payment and kick off the confirmation page$")
	public void select_payment_card_AND_confirm_payment_and_kick_off_the_confirmation_page() throws Throwable {
		p.selectDropDownBox(p.SelectPayment, "Card: Visa - 4242");
		wait.until(ExpectedConditions.elementToBeClickable(p.ContinueButton));
		p.ContinueButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(p.ComfirmationButton));
		CP = p.ConfirmPaymentMethod();
	}

	@Then("^wrap the quote id, pro number and pick up reference number to the excel report$")
	public void wrap_the_quote_id_pro_number_and_pick_up_reference_number_to_the_excel_report(Scenario s)
			throws Throwable {
		Row contentRow = TestRunner.sheet.createRow(++R);
		contentRow.createCell(0).setCellValue(s.getName());
		contentRow.createCell(1).setCellValue(CP.GetquoteID());
		contentRow.createCell(2).setCellValue(CP.GetProNum());
		contentRow.createCell(3).setCellValue(CP.GetPickupReferenceNum());
		TestRunner.sheet.createRow(++R);

	}

}
