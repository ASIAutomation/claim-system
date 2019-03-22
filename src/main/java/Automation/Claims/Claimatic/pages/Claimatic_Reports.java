package Automation.Claims.Claimatic.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.Claims.testBase.TestBase;

public class Claimatic_Reports extends TestBase {

	public static final Logger log = Logger.getLogger(Claimatic_Reports.class.getName());

	WebDriver driver;

	@FindBy(xpath="//*[@id=\"tab-current\"]/dl/dt[1]/a")//Link
	WebElement licensereport;

	@FindBy(xpath="//*[@id=\"tab-current\"]/dl/dt[2]/a")//Link
	WebElement resourceutilizationreport;

	@FindBy(xpath="//*[@id=\"tab-current\"]/dl/dt[3]/a")//Link
	WebElement assignmentcounts;

	@FindBy(xpath="//*[@id=\"tab-current\"]/dl/dt[4]/a")//Link
	WebElement usercalendar;

	@FindBy(xpath="//*[@id=\"tab-current\"]/dl/dt[5]/a")//Link
	WebElement assignmentdetailsreport;


	public Claimatic_Reports(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;		
	}

	public void ClickLicenseReport() {
		licensereport.click();
		Log("Clicked the \"License Report\" link on the Claimatic Reporting page");
	}

	public void ClickResourceUtilizationReport() {
		resourceutilizationreport.click();
		Log("Clicked the \"Resource Utilization Report\" link on the Claimatic Reporting page");
	}

	public void ClickAssignmentCounts() {
		assignmentcounts.click();
		Log("Clicked the \"Assignment Counts by Resource Monthly\" link on the Claimatic Reporting page");
	}

	public void ClickUserCalendar() {
		usercalendar.click();
		Log("Clicked the \"User Calendar\" link on the Claimatic Reporting page");
	}

	public void ClickAssignmentDetailsReport() {
		assignmentdetailsreport.click();
		Log("Clicked the \"Assignment Details Report\" link on the Claimatic Reporting page");
	}
}