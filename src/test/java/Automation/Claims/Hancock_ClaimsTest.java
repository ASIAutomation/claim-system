package Automation.Claims;

import java.awt.AWTException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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
import Automation.Claims.pages.Images;
import Automation.Claims.pages.LookupClaim;
import Automation.Claims.testBase.TestBase;

@SuppressWarnings("unused")
public class Hancock_ClaimsTest extends TestBase 
{

	public static final Logger log = Logger.getLogger(Hancock_ClaimsTest.class.getName());
	
	ClaimsMenu CM;
	LookupClaim LC;
	ClaimInfo CI;
	ClaimActivity CA;
	Images IM;
	ExtentReports extent = new ExtentReports();
	ExtentHtmlReporter reporter = new ExtentHtmlReporter("T:\\Automations\\ExtentReports\\Claims\\Claims_Checks_Cleared\\Claims_Checks_Cleared-" + GetDateTime() + ".html");
	
	/*
	 * This method provides the data for the claim payment notes
	 * 
	 */
	@DataProvider(name="Claims_System_Master")
	public String[][] GetHancockData() {
		String[][] data = GetData("Hancock_Data", "Claims_System_Master");
		return data;
	}
	
	@BeforeTest
	public void SetUp() {
		RunBatches();
		SetLog();
			Log("=================================== Starting Hancock Claims Batch ====================================");
		InitClaims("Chrome");
		
		CM = new ClaimsMenu(driver);
		LC = new LookupClaim(driver);
		CI = new ClaimInfo(driver);
		CA = new ClaimActivity(driver);
		IM = new Images(driver);
		GetURL("https://claims.americanstrategic.com/Dashboard/Analytics/Home");
	}
	
	/**
	 * 
	 * @param casenum : case number
	 * @param claimnumber : claim number
	 * @param note : note of the checks cleared
	 * @throws AWTException 
	 * @throws InterruptedException 
	 */
	@Test(dataProvider="Claims_System_Master")
	public void Run_Hancock_Data(String casenum, String claimnumber, String invoice, String pdffile) throws AWTException, InterruptedException 
	{
		reporter.config().setTheme(Theme.DARK);
		extent.attachReporter(reporter);
		ExtentTest logger=extent.createTest("Hancock_Data");
		//GetURL("https://claims.americanstrategic.com/Dashboard/Analytics/Home");
		GetURL("https://claims.americanstrategic.com/ClaimEntry/Search/?ClaimType=existing");
		Log("============================== Starting " + casenum + " ==============================");
		//CM.clickSearchClaim();
		
		if (claimnumber.length() > 13)
		{
			claimnumber = claimnumber.substring(0, 13);
		}
		
		
		LC.TypeClaimNumber(claimnumber);
		LC.ClickLookup();
		
		if(!CI.ReturnClaimNumber().contains(claimnumber)) 
		{
			Log(casenum + "-" + claimnumber + ": Not correct claim. Moving to next case.");
			logger.log(Status.SKIP, casenum + "-" + claimnumber + ": Not correct claim. Moving to next case.");
			return;
		}
		
		CI.ClickClaimActivity();
		
		String win = driver.getWindowHandle();
		
		CA.ClickNewEntry();
		
		
		
		// Switches control to the pop-up window
		for (String winHandle : driver.getWindowHandles()) 
		{
			driver.switchTo().window(winHandle);
		}

		CA.SelectSubjectMatter("Diary Notes");
		Pause(1000);
		CA.TypeHancockMessage(invoice, pdffile);
		Pause(1000);
		CA.ClickSave();
		
		// Switches control back to the main window
		driver.switchTo().window(win);
		
		CI.ClickImages();
		Pause(1000);
		IM.clickSelectFile();
		IM.Select(pdffile);
		Pause(3000);
		IM.ClickUpload();
		Pause(4000);
		String uploadfile = driver.findElement(By.xpath("//*[@id=\"jsGrid\"]/div[2]/table/tbody/tr[1]/td[4]")).getText();
	    if (uploadfile.equals(pdffile + ".PDF") || uploadfile.equals(pdffile + ".pdf"))
	    {
	    	Log("=== upload file name: " + uploadfile + "===");
	    	Log("=== Check Successful ===");
	    }
	    
	    else if (uploadfile.equals("1_" + pdffile + ".pdf") || uploadfile.equals("1_" + pdffile + ".PDF") ||
	    		uploadfile.equals("2_" + pdffile + ".pdf") || uploadfile.equals("2_" + pdffile + ".PDF"))
	    {
	    	Log("=== Duplicate pdf file: " + uploadfile + "===");
	    }
	    
	    else
	    {
	    	Log("=========== " + casenum + " failed to upload pdf file: " + pdffile + " ===========");
	    }
	    
	    Log("============================== Ending " + casenum + " ==============================");
	}
	
	@AfterTest
	public void EndBatch() {
		//QuitBrowser();
		extent.flush();
			Log("=================================== End Hancock Claims Batch =========================================");
	}
}

