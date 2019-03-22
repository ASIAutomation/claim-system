package Automation.Claims;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Automation.Claims.pages.*;
import Automation.Claims.testBase.TestBase;

public class Claims_AdjusterTest extends TestBase 
{

	public static final Logger log = Logger.getLogger(Claims_AdjusterTest.class.getName());

	ClaimsMenu CM;
	LookupClaim LC;
	ClaimInfo CI;
	ExtentReports extent = new ExtentReports();
	ExtentHtmlReporter reporter = new ExtentHtmlReporter("T:\\Automations\\ExtentReports\\Claims\\Claim_Adjuster\\Claim_Adjuster-" + GetDateTime() + ".html");
	
	@DataProvider(name="Claims_System_Master")
	public String[][] GetClaimAdjusterData() {
		String[][] data = GetData("Claim_Adjuster", "Claims_System_Master");
		return data;
	}
	
	@BeforeTest
	public void SetUp() 
	{
		SetLog();
		Log("====================================== Starting Claim Adjuster Batch ======================================");
		InitClaims("Chrome");
		
		CM = new ClaimsMenu(driver);
		LC = new LookupClaim(driver);
		CI = new ClaimInfo(driver);
		
	}
	
	@Test(dataProvider="Claims_System_Master")
	public void Run_Claim_Adjuster(String casenum, String claimnumber, String adjuster)	{
		reporter.config().setTheme(Theme.DARK);
		extent.attachReporter(reporter);
		ExtentTest logger=extent.createTest("Claim_Adjuster");
		try {
			Log("\n======= Starting Case: " + casenum + " =======");
			
			GetURL("https://claims.americanstrategic.com/ClaimEntry/Search/?ClaimType=existing");
			LC.TypeClaimNumber(claimnumber);
			LC.ClickLookup();
			
			if(!CI.ReturnClaimNumber().contains(claimnumber)) 
			{
				Log(casenum + "-" + claimnumber + ": Not correct claim. Moving to next case.");
				logger.log(Status.SKIP, casenum + "-" + claimnumber + ": Not correct claim. Moving to next case.");
				return;
			}
			
			//CI.TypeSeverityLevel("3");
			//CI.SelectSeverityLevel("Select Severity Level");
			
			CI.DiaryInfo("9/20/2019", "Diary opened for automation purposes. OK to close");
			
			String sevlvl = driver.findElement(By.id("txtEstimatedLoss")).getAttribute("value");
			Log("Sev Level Test: " + sevlvl);
			if (sevlvl.equals("")) 
			{
				CI.TypeSeverityLevel("1");
				
			}
			
			String sevlevel = driver.findElement(By.id("cmboSeverity")).getAttribute("value");
			Log("sevlevel: " + sevlevel);
			
			if (sevlevel.equals("0"))
			{
				CI.SelectSeverityLevel("Moderate......$3,501 - $10,000");
			}
			
			try
			{
				CI.SelectClaimAdjuster(adjuster);
				Pause(1000);
			}
			catch(Exception e) 
			{
				e.printStackTrace();
					Log(casenum + "-" + adjuster + ": Adjuster Name Not Found");
					logger.log(Status.SKIP, casenum + "-" + adjuster + ": Adjuster Name Not Found");
				return;
			}
			
			CI.ClickSave();
			Log("-- Clicked Save --");
			boolean isPresent = driver.findElements(By.xpath("//*[@id=\"errorMessage\"]/table[1]/tbody/tr/td[2]")).size() > 0;
			
			if (isPresent == true)
			{
				Log("Casenumber: " + casenum + " -- Failed to update due to Errors on the claim: " + claimnumber + " -- ");
				logger.log(Status.SKIP, "Casenumber: " + casenum + " -- Failed to update due to Errors on the claim: " + claimnumber + " -- ");
				return;
			}
			
			else
			{
				Log("======= Case: " + casenum + " successfully executed =======");
				logger.log(Status.PASS, "======= Case: " + casenum + " successfully executed =======");
	
			}
		}
		catch(Exception e) {
				logger.log(Status.FAIL, casenum + ": Script has failed. Please rerun.");
				Log("======= Case: " + casenum + " failed to executed =======");			
		}
	}
	
	@AfterTest
	public void EndBatch() 
	{
		//QuitBrowser();
		extent.flush();
		Log("\n====================================== End Claim Claim Adjuster Batch ======================================");
	}
}
