package Automation.Claims;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Automation.Claims.pages.ClaimActivity;
import Automation.Claims.pages.ClaimInfo;
import Automation.Claims.pages.ClaimsMenu;
import Automation.Claims.pages.LookupClaim;
import Automation.Claims.testBase.TestBase;

public class Claims_Checks_Cleared_RunTest extends TestBase  {

public static final Logger log = Logger.getLogger(Claims_Checks_Cleared_RunTest.class.getName());
	
	ClaimsMenu CM;
	LookupClaim LC;
	ClaimInfo CI;
	ClaimActivity CA;
	ExtentReports extent = new ExtentReports();
	ExtentHtmlReporter reporter = new ExtentHtmlReporter("T:\\Automations\\ExtentReports\\Claims\\Claims_Checks_Cleared\\Claims_Checks_Cleared-" + GetDateTime() + ".html");
	
	/*
	 * This method provides the data for the claim payment notes
	 * 
	 */
	@DataProvider(name="Claims_System_Master")
	public String[][] GetClaimPaymentNotesData() {
		String[][] data = GetData("Checks_Cleared", "Claims_System_Master");
		return data;
	}
	
	@BeforeTest
	public void SetUp() {
		RunBatches();
		SetLog();
			Log("=================================== Starting Claims Checks Cleared Batch ====================================");
		InitClaims("Chrome");
		
		CM = new ClaimsMenu(driver);
		LC = new LookupClaim(driver);
		CI = new ClaimInfo(driver);
		CA = new ClaimActivity(driver);
	}
	
	/**
	 * 
	 * @param casenum : case number
	 * @param claimnumber : claim number
	 * @param note : note of the checks cleared
	 */
	@Test(dataProvider="Claims_System_Master")
	public void Run_Claims_Checks_Cleared(String casenum, String claimnumber, String note) 	{
		reporter.config().setTheme(Theme.DARK);
		extent.attachReporter(reporter);
		ExtentTest logger=extent.createTest("Claims_Checks_Cleared");
		try {
			Log("============================== Starting " + casenum + " ==============================");
			
			// Pulls up the URL of specific Claim Activity page
			GetURL("https://claims.americanstrategic.com/ClaimEntry/Activity/?ClaimID=" + claimnumber + "&SortOrder=desc");
			GetURL("https://claims.americanstrategic.com/ClaimEntry/Activity/?ClaimID=" + claimnumber + "&SortOrder=desc");			
			if(!CA.ReturnClaimNumber().contains(claimnumber)) {
					Log(casenum + "-" + claimnumber + ": Not correct claim. Moving to next case.");
					logger.log(Status.SKIP, casenum + "-" + claimnumber + ": Not correct claim. Moving to next case.");
					Log("============================== End " + casenum + " ===================================");
				return;
			}			
			CA.ClickNewEntry();
			
			// Main window variable
			String win = driver.getWindowHandle();
			
			// Switches control to the pop-up window
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
			
			// Adds and saves the Payment Note for a particular claim
			CA.SelectSubjectMatter("Cleared Checks");
			CA.TypeClearedChecksNoteDescription(note);
			CA.ClickSave();			
				Log(casenum + "-" + claimnumber + ": completed and note added: " + note);
				logger.log(Status.PASS, casenum + "-" + claimnumber + ": completed and note added: " + note);
				
			// Switches control back to the main window
			driver.switchTo().window(win);
				Log("============================== End " + casenum + " ===================================");
		}
		catch(Exception e) {
				Log(casenum + "-" + claimnumber + ": Script has failed. Please rerun.");
				logger.log(Status.FAIL, casenum + "-" + claimnumber + ": Script has failed. Please rerun.");
				Log("============================== End " + casenum + " ===================================");
			QuitBrowser();
			InitClaims("Chrome");			
			CM = new ClaimsMenu(driver);
			LC = new LookupClaim(driver);
			CI = new ClaimInfo(driver);
			CA = new ClaimActivity(driver);
		}
	}
	
	@AfterTest
	public void EndBatch() {
		QuitBrowser();
		extent.flush();
			Log("=================================== End Claims Checks Cleared Batch =========================================");
	}
}