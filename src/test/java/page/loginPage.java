package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loginPage extends basePage {
	private WebDriverWait wait;

	public loginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 3);
	}

	@FindBy(id = "user_name")
	public WebElement UsernameInput;

	@FindBy(id = "pass")
	public WebElement PasswordInput;

	@FindBy(css = "input[value='Login']")
	public WebElement loginButton;

	public QuoteEntry LoginAndNavigateToDFQ(String username, String password) {
		UsernameInput.sendKeys(username);
		PasswordInput.sendKeys(password);
		loginButton.click();
		headerPage hp = new headerPage();
		return hp.NavigateToOldQuotePage().navigateToDFQ();
	}

	public class headerPage {
		public headerPage() {
			PageFactory.initElements(driver, this);
		}

		@FindBy(linkText = "SHIP")
		public WebElement SHIPbutton;

		@FindBy(linkText = "Rate Quote")
		public WebElement RateQuoteButton;

		public oldQuotePage NavigateToOldQuotePage() {

			wait.until(ExpectedConditions.visibilityOf(SHIPbutton));
			SHIPbutton.click();
			wait.until(ExpectedConditions.visibilityOf(RateQuoteButton));
			RateQuoteButton.click();
			return new oldQuotePage();

		}

		public class oldQuotePage {
			public oldQuotePage() {
				PageFactory.initElements(driver, this);
			}

			@FindBy(id = "dfqLink")
			public WebElement DFQbutton;

			public QuoteEntry navigateToDFQ() {
				wait.until(ExpectedConditions.visibilityOf(DFQbutton));
				DFQbutton.click();
				return new QuoteEntry(driver);
			}
		}
	}

}
