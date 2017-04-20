package stepdefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class HookStep extends base {
public static String ScenarioName;
	
	@Before("@f1")
	public void GetScenarioInfo(Scenario s) {
		ScenarioName=s.getName();

	}
	
	@After("@f1")
	public void closebrowser(Scenario s) {
		driver.quit();

	}

}
