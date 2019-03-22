package Automation.Claims;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Automation.Claims.pages.*;
import Automation.Claims.testBase.TestBase;

public class Coverage_ValidationTest extends TestBase 
{
	public static final Logger log = Logger.getLogger(Coverage_ValidationTest.class.getName());

	ClaimsMenu CM;
	LookupClaim LC;
	ClaimInfo CI;
	
	@DataProvider(name="Claims_System_Master")
	public String[][] GetCoverageValidationData() 
	{
		String[][] data = GetData("", "Claims_System_Master");
		return data;
	}
	
	@BeforeTest
	public void SetUp() {
		SetLog();
		Log("====================================== Starting Coverage Validation Batch ======================================");
		InitClaims("Chrome");
		
		CM = new ClaimsMenu(driver);
		LC = new LookupClaim(driver);
		CI = new ClaimInfo(driver);
	}


	@Test//(dataProvider="Claims_System_Master")
	public void Run_Coverage_Validation(/*String casenum, String claimnumber*/) 
	{
		
		//Log("\n======= Starting Case: " + casenum + " =======");
		
		GetURL("https://claims.americanstrategic.com/ClaimEntry/Search/?ClaimType=existing");
		LC.TypeClaimNumber("549438");
		LC.ClickLookup();
		CI.ClickClaimCoverage();
		
		String dwellingcov = driver.findElement(By.id("A-Dwelling-val")).getText();
		String adebrisremovalcov = driver.findElement(By.id("A-DebrisRemoval-val")).getText();
		String ircdwellingcov = driver.findElement(By.id("A-IRC-Dwelling-val")).getText();
		String botherstructurescov = driver.findElement(By.id("B-OtherStructures-val")).getText();
		String bdebrisremovalcov = driver.findElement(By.id("B-DebrisRemoval-val")).getText();
		String bircotherstructurescov = driver.findElement(By.id("B-IRC-OtherStructures-val")).getText();
		String personalpropcov = driver.findElement(By.id("C-PersonalProperty-val")).getText();
		String debrisremovalcov = driver.findElement(By.id("C-DebrisRemoval-val")).getText();
		String lossofusecov = driver.findElement(By.id("D-LossofUse-val")).getText();
		String liabilitycov = driver.findElement(By.id("E-Liability-val")).getText();
		String medicalpaymentcov = driver.findElement(By.id("F-MedicalPayment-val")).getText();
		String ordlawcov = driver.findElement(By.id("G-OrdinanceOrLaw-val")).getText();
		String sreeandshrubscov = driver.findElement(By.id("I-TreesandShrubs-val")).getText();
		String idebrisremovalcov = driver.findElement(By.id("I-DebrisRemoval-val")).getText();
		String firedepartmentcov = driver.findElement(By.id("J-FireDepartment-val")).getText();		
		String klossassessment = driver.findElement(By.id("K-LossAssessment-val")).getText();
		String moneycov = driver.findElement(By.id("L-CreditCard,EFTCard,andCounterfeitMoney-val")).getText();		
		String scheduledpropertycov = driver.findElement(By.id("M-ScheduledProperty-val")).getText();
		String singlephadeductible = driver.findElement(By.id("Z-SinglePHADeductible-val")).getText();
		
		System.out.println("A - Dwelling: " + dwellingcov + "\nA - Debris Removal: " + adebrisremovalcov + "\nA - IRC-Dwelling: " + ircdwellingcov);
		System.out.println("B - Other Structures: " + botherstructurescov + "\nB - Debris Removal: " + bdebrisremovalcov + "\nB - IRC-Other Structures: " + bircotherstructurescov);
		System.out.println("C - Personal Property: " + personalpropcov + "\nC - Debris Removal: " + debrisremovalcov + "\nD - Loss of Use: " + lossofusecov);
		System.out.println("E - Liability: " + liabilitycov + "\nF - Medical Payment: " + medicalpaymentcov + "\nG - Ordinance Or Law: " + ordlawcov); 
		System.out.println("I - Trees and Shrubs: " + sreeandshrubscov + "\nI - Debris Removal: " + idebrisremovalcov + "\nJ - Fire Department: " + firedepartmentcov);
		System.out.println("K - Loss Assessment: " + klossassessment + "\nL - Credit Card, EFT Card, and Counterfeit Money: " + moneycov);	
		System.out.println("M - Scheduled Property: " + scheduledpropertycov + "\nZ - Single PHA Deductible: " + singlephadeductible);
	
		String policynum = driver.findElement(By.xpath("//*[@id=\"wrap_dataHeader\"]/div[2]/div/table[1]/tbody/tr[2]/td[2]/a")).getText();
		String inceptiondate = driver.findElement(By.xpath("//*[@id=\"wrap_dataHeader\"]/div[2]/div/table[1]/tbody/tr[4]/td[2]")).getText();
		CI.ClickPolicyLink();
		SwitchTab("1");
		GetURL("https://policy.americanstrategic.com/Policy/History.aspx?PolicyID=" + policynum + "&InceptionDate=" + inceptiondate);
	
		String psdwellingcov = driver.findElement(By.xpath("//*[@id=\"dgCoverages\"]/tbody/tr[2]/td[2]")).getText();
		psdwellingcov = psdwellingcov.replace(",", "");
		double parsedwellingcov = Double.parseDouble(psdwellingcov);
		System.out.println("parsed dwelling coverage: " + parsedwellingcov);
		double psdebrisremoval = 0;
		double fiveper = 0.05;
		psdebrisremoval = (parsedwellingcov * fiveper);
		System.out.println("Result: " + psdebrisremoval);
		// A - debris removal: 5% of Coverage A (dwelling coverage)
		
		// irc dwelling coverage: Default to $0
		
		//String psotherstructures = driver.findElement(By.xpath("//*[@id=\"dgCoverages\"]/tbody/tr[5]/td[2]")).getText();
		
		// B - debris removal: 5% of Other Structures
		
		// B - IRC other structures: Default to $0
		
		//String pspersonalproperty = driver.findElement(By.xpath("//*[@id=\"dgCoverages\"]/tbody/tr[6]/td[2]")).getText();
		
		// C - debris removal: 5% of Personal Property
		
		//String pslossofuse = driver.findElement(By.xpath("//*[@id=\"dgCoverages\"]/tbody/tr[7]/td[2]")).getText();
		
		//String personalliability = driver.findElement(By.xpath("//*[@id=\"dgCoverages\"]/tbody/tr[8]/td[2]")).getText();
		
		//String medicalpayment = driver.findElement(By.xpath("//*[@id=\"dgCoverages\"]/tbody/tr[10]/td[2]")).getText();
		
		//String ordandlaw = driver.findElement(By.xpath("//*[@id=\"dgCoverages\"]/tbody/tr[9]/td[2]")).getText();
		
		// I - Sree and Shrubs: 5% of Dwelling Coverage
		
		// I - Debris Removal: 5% of Sree and Shrubs
		
		// J - Fire Department: Default to $500
		
		// K - Loss Assessment: Default to $1,000
		
		// L - Credit Card, EFT Card, and Counterfeit Money: Default to $500 / $1,000
		
		// M - Scheduled Property
		
		// Z - Single PHA Deductible
		
		System.out.println("Dwelling Coverage: $" + psdwellingcov);
		//
		
		
		//Log("======= Case: " + casenum + " successfully executed =======");
	}
	
	@AfterTest
	public void EndBatch() {
		//QuitBrowser();
		Log("\n====================================== End Coverage Validation Batch ======================================");
	}
}
