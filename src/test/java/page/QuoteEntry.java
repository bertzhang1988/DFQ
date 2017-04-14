package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuoteEntry extends basePage {
	private WebDriverWait wait;

	public QuoteEntry(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 3);
	}

	@FindBy(id = "customerLocation")
	public WebElement Customerlocation;

	@FindBy(name = "rq.location.shipdate")
	public WebElement PickupDateInput;

	@FindBy(id = "ui-datepicker-div")
	public WebElement PickupDateBox;

	@FindBy(name = "rq.shipment.from.location.type")
	public WebElement ShipFromType;

	@FindBy(name = "rq.shipment.to.location.type")
	public WebElement ShipToType;

	@FindBy(name = "rq.shipment.from.zippostalcode")
	public WebElement ShipFromZip;

	@FindBy(name = "rq.shipment.to.zippostalcode")
	public WebElement ShipToZip;

	@FindBy(css = "#commodities>tbody>tr")
	public List<WebElement> shipmentList;

	@FindBy(name = "rq.items.piecetype")
	public List<WebElement> handlingUnitsList;

	public String SelectRole(String role) {
		driver.findElement(By.cssSelector("input[value='" + role + "']")).click();
		return role;
	}

	public String SelectPickupDate() {
		PickupDateInput.click();
		wait.until(ExpectedConditions.visibilityOf(PickupDateBox));
		List<WebElement> validD = PickupDateBox.findElements(By.xpath("//td[@data-handler='selectDay']"));
		validD.get(1).click();
		return PickupDateInput.getAttribute("value");
	}



	public void FillshipmentDetail(String lineNum, String handlingUnitValue, String LengthValue,String width,String height,String unitsNumber, String weightPerUnit) {
		int index = Integer.valueOf(lineNum) - 1;
		this.selectDropDownBox(
				shipmentList.get(index).findElement(By.cssSelector(" select[name='rq.items.piecetype']")),
				handlingUnitValue);
		shipmentList.get(index).findElement(By.xpath("//input[@name='rq.items.length']")).sendKeys(LengthValue);
		shipmentList.get(index).findElement(By.xpath("//input[@name='rq.items.width']")).sendKeys(width);
		shipmentList.get(index).findElement(By.xpath("//input[@name='rq.items.height']")).sendKeys(height);
		shipmentList.get(index).findElement(By.xpath("//input[@name='rq.items.width']")).sendKeys(width);
		shipmentList.get(index).findElement(By.xpath("//input[@name='rq.items.pieces']")).sendKeys(unitsNumber);
		shipmentList.get(index).findElement(By.xpath("//input[@name='weightPerUnit']")).sendKeys(weightPerUnit);
	
	}

	public QuoteSummary NextToQuoteSummary() {
		driver.findElement(By.linkText("Next")).click();
		wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(driver.findElement(By.partialLinkText(" Quote Summary")), By.xpath("./span[text()='current step: ']")));
		return new QuoteSummary(driver);
	}

}