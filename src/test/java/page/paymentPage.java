package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class paymentPage extends basePage {
	private WebDriverWait wait;

	public paymentPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
	}

	@FindBy(id = "savedAccountId")
	public WebElement SelectPayment;

	@FindBy(id = "formSubmitComp")
	public WebElement ContinueButton;

	@FindBy(id = "confirmBtn")
	public WebElement ComfirmationButton;

	public ConfirmationPage ConfirmPaymentMethod() {
		ComfirmationButton.click();
		wait.until(ExpectedConditions.titleIs("my.yrc.com: Dimensional Freight Quote | YRC"));
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(".//span[text()='Confirmation']")));

		return new ConfirmationPage(driver);

	}

}
