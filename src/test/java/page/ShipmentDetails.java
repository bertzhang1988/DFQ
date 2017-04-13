package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShipmentDetails extends basePage {
	private WebDriverWait wait;

	public ShipmentDetails(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}

	@FindBy(name = "com.shipper.name")
	public WebElement FromCompanyName;

	@FindBy(name = "com.shipper.address")
	public WebElement FromAddress;

	@FindBy(name = "com.shipper.email")
	public WebElement FromEmail;

	@FindBy(name = "com.consignee.name")
	public WebElement ToCompanyName;

	@FindBy(name = "com.consignee.address")
	public WebElement ToAddress;

	@FindBy(name = "com.consignee.email")
	public WebElement ToEmail;

	@FindBy(name = "bol.commodities.pieces.0")
	public WebElement pieceCount;

	@FindBy(name = "bol.commodities.uom.0")
	public WebElement UnitOfMeasurement;

	@FindBy(name = "bol.commodities.description.0")
	public WebElement DescriptionOfShipment;

	@FindBy(name = "bol.pu.contactname")
	public WebElement PickUpContactName;

	@FindBy(name = "bol.pu.contactphone")
	public WebElement PickUpContactNo;

	public TermsCondition NextToTermsCondition() {
		driver.findElement(By.linkText("Next")).click();
		wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(
				driver.findElement(By.partialLinkText(" Terms & Conditions")),
				By.xpath("./span[text()='current step: ']")));
		return new TermsCondition(driver);

	}
}
