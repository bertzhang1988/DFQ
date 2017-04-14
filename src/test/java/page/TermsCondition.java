package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TermsCondition extends basePage {
	private WebDriverWait wait;

	public TermsCondition(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}

	@FindBy(name = "acceptAllTermsAndConditions")
	public WebElement CheckBox;

	public void acceptTermsAndCondition() {
		if (!CheckBox.isSelected())
			CheckBox.click();
	}

	public paymentPage NextToPaymentPage() {
		driver.findElement(By.linkText("Next")).click();
		wait.until(ExpectedConditions.urlToBe(
				"https://www.payconnexion.com/pconWeb/public/compressedPayment/enterPaymentInformation_input.action"));
		return new paymentPage(driver);

	}
}
