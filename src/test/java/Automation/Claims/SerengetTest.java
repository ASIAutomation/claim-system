package Automation.Claims;
import java.awt.AWTException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import Automation.Claims.pages.*;
import Automation.Claims.testBase.TestBase;

public class SerengetTest extends TestBase
{

	public static final Logger log = Logger.getLogger(SerengetTest.class.getName());
	
	ClaimsMenu CM;
	LookupClaim LC;
	ClaimInfo CI;
	ClaimActivity CA;
	Images IM;
	ExtentReports extent = new ExtentReports();
	ExtentHtmlReporter reporter = new ExtentHtmlReporter("T:\\Automations\\ExtentReports\\Claims\\Claims_Checks_Cleared\\Claims_Checks_Cleared-" + GetDateTime() + ".html");

	@DataProvider(name="Claims_System_Master")
	public String[][] GetSerengetData() {
		String[][] data = GetData("Serenget_Data", "Claims_System_Master");
		return data;
	}
	
	@BeforeTest
	public void SetUp() 
	{
		
		RunBatches();
		SetLog();
			Log("=================================== Starting Serenget Claims Batch ====================================");
		InitClaims("Chrome");
		
		CM = new ClaimsMenu(driver);
		LC = new LookupClaim(driver);
		CI = new ClaimInfo(driver);
		CA = new ClaimActivity(driver);
		IM = new Images(driver);
		GetURL("https://claims.americanstrategic.com/Dashboard/Analytics/Home");
	}

	@Test(dataProvider="Claims_System_Master")
	public void Run_Hancock_Data(String casenum, String claimnumber, String invoice, String amount, String pdffile) throws AWTException, InterruptedException 
	{
		reporter.config().setTheme(Theme.DARK);
		extent.attachReporter(reporter);
		ExtentTest logger=extent.createTest("Serenget_Data");
		GetURL("https://claims.americanstrategic.com/ClaimEntry/Search/?ClaimType=existing");
		Log("============================== Starting " + casenum + " ==============================");
		
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
		for (String winHandle : driver.getWindowHandles()) 
		{
			driver.switchTo().window(winHandle);
		}
		CA.SelectSubjectMatter("Legal Invoices Recd");
		CA.TypeSerengetMessage(invoice, amount, pdffile);
		Pause(1000);
		CA.ClickSave();
		
		// Switches control back to the main window
		driver.switchTo().window(win);
	
		CI.ClickImages();
		IM.clickSelectFile();
		IM.Select(pdffile);
		Pause(3000);
		IM.ClickUpload();
		Pause(4000);
		String uploadfile = driver.findElement(By.xpath("//*[@id=\"jsGrid\"]/div[2]/table/tbody/tr[1]/td[4]")).getText();
	    Pause(1000);
		if (uploadfile.equals(pdffile + ".PDF") || uploadfile.equals(pdffile + ".pdf"))
	    {
	    	Pause(1000);
	    	Log("=== uploaded file name: " + uploadfile + "===");
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
	}
	
	@AfterTest
	public void EndBatch() {
		//QuitBrowser();
		extent.flush();
			Log("=================================== End Hancock Claims Batch =========================================");
	}
}
