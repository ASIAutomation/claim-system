package Automation.Claims.Claimatic.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.Claims.testBase.TestBase;

public class Claimatic_CompanySnapshot extends TestBase {

	public static final Logger log = Logger.getLogger(Claimatic_CompanySnapshot.class.getName());

	WebDriver driver;

	@FindBy(xpath="//*[@id=\"top_nav\"]/ul/li[1]/a")//Button
	WebElement companysnapshot;

	@FindBy(xpath="//*[@id=\"top_nav\"]/ul/li[3]/a")//Button
	WebElement resourcemanager;

	@FindBy(xpath="//*[@id=\"top_nav\"]/ul/li[4]/a")//Button
	WebElement reassign;

	@FindBy(xpath="//*[@id=\"top_nav\"]/ul/li[5]/a")//Button
	WebElement reports;

	@FindBy(xpath="//*[@id=\"top_nav\"]/ul/div/li/a")//Button
	WebElement claimintake;


	public Claimatic_CompanySnapshot(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;		
	}

	public void ClickCompanySnapshot() {
		companysnapshot.click();
		Log("Clicked the \"Company Snapshot\" button on the Claimatic Company Snapshot/Home page");
	}

	public void ClickResourceManager() {
		resourcemanager.click();
		Log("Clicked the \"Resource Manager\" button on the Claimatic Company Snapshot/Home page");
	}

	public void ClickReassign() {
		reassign.click();
		Log("Clicked the \"Reassign\" button on the Claimatic Company Snapshot/Home page");
	}

	public void ClickReports() {
		reports.click();
		Log("Clicked the \"Reports\" button on the Claimatic Company Snapshot/Home page");
	}

	public void ClickClaimIntake() {
		claimintake.click();
		Log("Clicked the \"ClaimIntake\" button on the Claimatic Company Snapshot/Home page");
	}
}