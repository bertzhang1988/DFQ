package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class basePage {

	protected WebDriver driver;

	public basePage(WebDriver driver) {
		this.driver = driver;

	}

	public void NavigateToLogin(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	public String selectDropDownBox(WebElement box, String value) {
		Select s = new Select(box);
		s.selectByVisibleText(value);
		return value;
	}

	public void Closebrowser() {
		driver.close();
	}
}
