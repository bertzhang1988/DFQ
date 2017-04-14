package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmationPage extends basePage {
	private WebDriverWait wait;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}

	public String GetquoteID() {
		return driver.findElement(By.name("quoteId")).getText();
	}

	public String GetPickupReferenceNum() {
		return driver.findElement(By.name("pickupReferenceNumber")).getText();
	}

	public String GetProNum() {
		return driver.findElement(By.name("proNumber")).getText();
	}
}
