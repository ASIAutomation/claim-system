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
import Automation.Claims.pages.*;
import Automation.Claims.pages.LookupClaim;
import Automation.Claims.testBase.TestBase;

public class Unclaimed_ChecksTest extends TestBase 
{

	public static final Logger log = Logger.getLogger(Unclaimed_ChecksTest.class.getName());

	ClaimsMenu CM;
	LookupClaim LC;
	ClaimInfo CI;
	ClaimActivity CA;
	ExtentReports extent = new ExtentReports();
	ExtentHtmlReporter reporter = new ExtentHtmlReporter("T:\\Automations\\ExtentReports\\Claims\\Claim_Note\\Claim_Note-" + GetDateTime() + ".html");

	/*
	 * This method provides the data for the claim payment notes
	 * 
	 */
	@DataProvider(name="Claims_System_Master")
	public String[][] GetClaimNotesData() {
		String[][] data = GetData("Claims_Check", "Claims_System_Master");
		return data;
	}

	@BeforeTest
	public void SetUp() {
		SetLog();
		Log("====================================== Starting Claim Note Batch ======================================");
		InitClaims("Chrome");

		CM = new ClaimsMenu(driver);
		LC = new LookupClaim(driver);
		CI = new ClaimInfo(driver);
		CA = new ClaimActivity(driver);
	}


	/**
	 * 
	 * This method runs the test of the Claim Payment Notes. It reads in data from and excel file,
	 * parses the values and runs through the ASI claims system adding payment notes to claims
	 * 
	 * @param casenum : case number
	 * @param company : the company which the payment is made to
	 * @param invoicenumber : the invoice number of the note
	 * @param invoiceamt : The invoice amount in $
	 * @param claimnumber : The claim number for which the payment is made
	 * 
	 */
	@Test(dataProvider="Claims_System_Master")
	public void Run_Claim_Notes(String casenum, String claimnumber, String checknum, String date, String amount) 	{
		reporter.config().setTheme(Theme.DARK);
		extent.attachReporter(reporter);
		ExtentTest logger=extent.createTest("Claim_Note");
		try {
			Log("\n======= Starting Case: " + casenum + " =======");
	
			// Pulls up the URL of Claim Lookup page
			GetURL("https://claims.americanstrategic.com/ClaimEntry/Search/?ClaimType=existing");
	
			// Format Check
			//System.out.println(" Parsed Case Number: " + casenum + "\n Parsed Claim Number: " + claimnumber + "\n Parsed Company: " + company + "\n Parsed Invoice Number: " + invoicenumber +
				//	"\n Parsed Invoice Amount: " + amount);
	
			// Looks up claim number and clicks 'add new entry'
			LC.TypeClaimNumber(claimnumber);
			LC.ClickLookup();
	
			if(!CI.ReturnClaimNumber().contains(claimnumber)) 
			{
				Log(casenum + "-" + claimnumber + ": Not correct claim. Moving to next case.");
				logger.log(Status.SKIP, casenum + "-" + claimnumber + ": Not correct claim. Moving to next case.");
				return;
			}
	
			CI.ClickClaimActivity();
			CA.ClickNewEntry();
			// Main window variable
			String win = driver.getWindowHandle();
	
			// Switches control to the pop-up window
			for (String winHandle : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle);
			}
	
			// Adds and saves the Payment Note for a particular claim
			CA.SelectSubjectMatter("Accounting Notation");
			CA.TypeAccountingNotationNoteDescription(checknum, date, amount);
			CA.ClickSave();
			// Switches control back to the main window
			driver.switchTo().window(win);
	
			Log("======= Case: " + casenum + " successfully executed =======");
			logger.log(Status.PASS, "======= Case: " + casenum + " successfully executed =======");
		}
		catch(Exception e) {
				logger.log(Status.FAIL, casenum + ": Script has failed. Please rerun.");
				Log("======= Case: " + casenum + " failed to executed =======");			
		}
	}

	@AfterTest
	public void EndBatch() {
		//QuitBrowser();
		Log("\n====================================== End Claim Note Batch ======================================");
	}
}
