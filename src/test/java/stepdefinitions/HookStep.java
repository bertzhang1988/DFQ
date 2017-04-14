package stepdefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;

public class HookStep extends base {

	@After("@f1")
	public void closebrowser(Scenario s) {
		driver.quit();

	}

}
