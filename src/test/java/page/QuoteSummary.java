package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuoteSummary extends basePage {
	private WebDriverWait wait;

	public QuoteSummary(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}

	@FindBy(css = "input[value='Select Quote']")
	public List<WebElement> QuoteOptions;

	public ShipmentDetails SelectQuoteNextToShipmentDetails() {
		QuoteOptions.get(0).click();
		wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(
				driver.findElement(By.partialLinkText(" Shipment Details")),
				By.xpath("./span[text()='current step: ']")));
		return new ShipmentDetails(driver);
	}

}
