package Automation.Claims;

import java.awt.AWTException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import Automation.Claims.pages.ClaimActivity;
import Automation.Claims.pages.ClaimInfo;
import Automation.Claims.pages.ClaimsMenu;
import Automation.Claims.pages.Images;
import Automation.Claims.pages.LookupClaim;
import Automation.Claims.testBase.TestBase;

public class DummyTest extends TestBase {
	
	public static final Logger log = Logger.getLogger(DummyTest.class.getName());

	
	ClaimsMenu CM;
	LookupClaim LC;
	ClaimInfo CI;
	ClaimActivity CA;
	Images IM;
	
	
	/* HIIIIIIIIII JACK!!!! */
	/* Send it back */
	
	/*
	 * This method provides the data for the claim payment notes
	 * 
	 */
	/*@DataProvider(name="Claims_System_Master")
	public String[][] DummyData() {
		String[][] data = GetData("Checks_Cleared", "Claims_System_Master");
		return data;
	}*/
	
	@BeforeTest
	public void SetUp() {
		SetLog();
		Log("====================================== Starting Claims Checks Cleared Batch ======================================");
		InitClaims("Chrome");
		
		CM = new ClaimsMenu(driver);
		LC = new LookupClaim(driver);
		CI = new ClaimInfo(driver);
		CA = new ClaimActivity(driver);
		IM = new Images(driver);
	}
	
	@Test
	public void Dummy() throws AWTException, InterruptedException 
	{
		
		//Log("\n======= Starting Case: " + casenum + " =======");
		
		// Pulls up the URL of Claim Lookup page
		//GetURL("https://claims.americanstrategic.com/ClaimEntry/Search/?ClaimType=existing");
		
		
		String test1 = "549438-12345";
		if (test1.length() > 13)
		{
			test1 = test1.substring(0,13);
			System.out.println("Trimmed string: " + test1);
		}
		
		System.out.println("Untrimmed string: " + test1);
		//test1 = test1.substring(0,13);
		//System.out.println("Trimmed string: " + test1);
		
		//LC.TypeClaimNumber("549438");
		//LC.ClickLookup();
		//CI.ClickImages();
		//String yourmom = driver.findElement(By.xpath("//*[@id=\"jsGrid\"]/div[2]/table/tbody/tr[1]/td[4]")).getText();
		//System.out.println("BABY SHARK: " + yourmom + " DOO DOO DOO DOO DOO DOO");
		//IM.clickSelectFile();
		//IM.Select();
		//Pause(4000);
		//IM.ClickUpload();
		
		
	}
}
	
	
	
	
	
	
	
	
	
	
