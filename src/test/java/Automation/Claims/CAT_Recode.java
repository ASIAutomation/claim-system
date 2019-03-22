package Automation.Claims;

import Automation.Claims.pages.ClaimInfo;
import Automation.Claims.pages.LookupClaim;
import Automation.Claims.testBase.TestBase;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

@Listeners(Automation.Claims.customListeners.CustomListeners.class)
public class CAT_Recode extends TestBase {
	
	public static final Logger log = Logger.getLogger(CAT_Recode.class.getName());

	LookupClaim LC;
	ClaimInfo CI;
	ExtentReports extent = new ExtentReports();
	ExtentHtmlReporter reporter = new ExtentHtmlReporter("T:\\Automations\\ExtentReports\\Claims\\CAT_Recode\\CAT_Recode-" + GetDateTime() + ".html");
	
	@DataProvider(name="CAT_Recode")
	public String[][] GetData() {
		String[][] data = GetDataFromAnyPath("T:/CAT Automation/CAT_Recode.xlsx", "Data", "CAT_Recode");
		return data;
	}
	
	@BeforeTest
	public void SetUp() {
		SetLog();
			Log("====================================== Starting CAT_Recode Batch ======================================");
		InitClaims("Chrome");
		//Initialize Drivers
		LC = new LookupClaim(driver);
		CI = new ClaimInfo(driver);
	}

	@Test(dataProvider="CAT_Recode")
	public void RunCAT_Recode(String casenum, String claimid, String currentcatcode, String suggestedcatcode) throws InterruptedException {
		reporter.config().setTheme(Theme.DARK);
		extent.attachReporter(reporter);
		ExtentTest logger=extent.createTest("CAT_Recode");
		try {
				Log("============================== Starting " + casenum + " ==============================");	
				//Log(casenum +  "-" + claimid + "-" + currentcatcode + "-" + suggestedcatcode);
			//Needs to go to this URL and not directly to Claims Info page so we can verify correctly at the end
			GetURL("https://claims.americanstrategic.com/ClaimEntry/Search/?ClaimType=existing");		
			LC.TypeClaimNumber(claimid);
			LC.ClickLookup();
			if(!CI.ReturnClaimNumber().contains(claimid)) {
					Log(casenum + "-" + claimid + ": Not correct claim. Moving to next case.");
					logger.log(Status.SKIP, casenum + "-" + claimid + ": Not correct claim. Moving to next case.");
					Log("============================== End " + casenum + " ===================================");
				return;
			}
			driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
			try {
				CI.SelectStormCode(suggestedcatcode);	
			}
			catch(Exception e) {
					Log(casenum + "-" + claimid + ": Suggested CAT Code is not found in the system for this claim");
					logger.log(Status.SKIP, casenum + "-" + claimid + ": Suggested CAT Code is not found in the system for this claim");
					Log("============================== End " + casenum + " ===================================");
				return;
			}
			if(currentcatcode.toUpperCase().equals("N/A")) {
				CI.SelectSeverityLevel("Moderate......$3,501 - $10,000");
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			CI.ClickSave();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			try {
				CI.ClickDiaryCodeOK();
			}
			catch(Exception e) {
				Log("No diary code");
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if(CI.ReturnStormCode().equals(suggestedcatcode) && driver.getCurrentUrl().equals("https://claims.americanstrategic.com/ClaimEntry/Claim?ClaimID=" + claimid)) {
					Log(casenum + "-" + claimid + ": CAT Code has been updated to "+ suggestedcatcode);	
					logger.log(Status.PASS, casenum + "-" + claimid + ": CAT Code has been updated to "+ suggestedcatcode);
			}
			else {
					Log(casenum + "-" + claimid + ": CAT Code had issues saving. Please manually check file for missing info: " + CI.ReturnErrorText());
					logger.log(Status.FAIL, casenum + "-" + claimid + ": CAT Code had issues saving. Please manually check file for missing info: " + CI.ReturnErrorText());
			}
				Log("============================== End " + casenum + " ===================================");
		}
		catch(Exception e) {
				logger.log(Status.FAIL, casenum + ": Script has failed. Please rerun.");
				Log("============================== End " + casenum + " ===================================");			
		}
	}
	
	@AfterTest
	public void EndBatch() {
		QuitBrowser();
		extent.flush();
			Log("====================================== End CAT_Recode Batch ======================================");
	}
}
