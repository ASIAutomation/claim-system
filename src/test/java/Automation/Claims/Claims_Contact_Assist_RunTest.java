package Automation.Claims;

import Automation.Claims.pages.*;
import Automation.Claims.testBase.TestBase;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.Logger;

public class Claims_Contact_Assist_RunTest extends TestBase {

public static final Logger log = Logger.getLogger(Claims_Contact_Assist_RunTest.class.getName());

	
	ClaimsMenu CM;
	LookupClaim LC;
	ClaimInfo CI;
	ExtentReports extent = new ExtentReports();
	ExtentHtmlReporter reporter = new ExtentHtmlReporter("T:\\Automations\\ExtentReports\\Claims\\Contact_Assist\\Contact_Assist-" + GetDateTime() + ".html");

	@DataProvider(name="Claims_System_Master")
	public String[][] GetData() {
		String[][] data = GetData("Contact_Assignments", "Claims_System_Master");
		return data;
	}
	
	@BeforeTest
	public void SetUp() {
			SetLog();
			Log("====================================== Starting Contact Assist Batch ======================================");
			
			InitClaims("remote");
			CM = new ClaimsMenu(driver);
			LC = new LookupClaim(driver);
			CI = new ClaimInfo(driver);
	}

	/**
	 * 
	 * @param casenum : case number
	 * @param claimnumber : claim number from excel file
	 * @param contactassistname : contact assistant name
	 * @throws InterruptedException
	 */
	
	@Test(dataProvider="Claims_System_Master", threadPoolSize= 2, invocationCount= 2, timeOut= 3000)
	public void RunClaims_Contact_Assist(String casenum, String claimnumber, String contactassistname) throws InterruptedException	{
		reporter.config().setTheme(Theme.DARK);
		extent.attachReporter(reporter);
		ExtentTest logger=extent.createTest("CAT_Assist");
		try {
			Log("======= Starting " + casenum + " =======");	
			Log("Running on thread: " + Thread.currentThread().getId());
			if(claimnumber.length() > 13) {
				claimnumber = claimnumber.substring(0, 13);
			}			
			GetURL("https://claims.americanstrategic.com/ClaimEntry/Search/?ClaimType=existing");	
			LC.TypeClaimNumber(claimnumber);
			LC.ClickLookup();		
			if(!CI.ReturnClaimNumber().contains(claimnumber)) {
				Log(casenum + "-" + claimnumber + ": Not correct claim. Moving to next case.");
				logger.log(Status.SKIP, casenum + "-" + claimnumber + ": Not correct claim. Moving to next case.");
				return;
			}			
			CI.SelectContactAssistance(contactassistname);
			CI.ClickSave();			
			if(CI.ReturnContactAssist().equals(contactassistname) && driver.getCurrentUrl().equals("https://claims.americanstrategic.com/ClaimEntry/Claim?ClaimID=" + claimnumber)) {
					Log(casenum + "-" + claimnumber + ": CAT Assist has been updated to "+ contactassistname);	
					logger.log(Status.PASS, casenum + "-" + claimnumber + ": CAT Assist has been updated to "+ contactassistname);
			}
			else {
					Log(casenum + "-" + claimnumber + ": CAT Assist had issues saving. Please manually check file for missing info: " + CI.ReturnErrorText());
					logger.log(Status.FAIL, casenum + "-" + claimnumber + ": CAT Assist had issues saving. Please manually check file for missing info: " + CI.ReturnErrorText());
			}			
				Log("======= Ending " + casenum + " =========");
		}
		catch(Exception e) {
				Log(casenum + ": Script has failed. Please rerun.");
				logger.log(Status.FAIL, casenum + ": Script has failed. Please rerun.");
				Log("======= Ending " + casenum + " =========");
		}		
	}
	
	@AfterTest
	public void EndBatch() {
		QuitBrowser();
		extent.flush();
		Log("====================================== End Contact Assist Batch ======================================");
	}
}
