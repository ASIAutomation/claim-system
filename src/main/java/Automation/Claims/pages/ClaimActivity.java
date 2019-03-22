package Automation.Claims.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Automation.Claims.testBase.TestBase;

public class ClaimActivity extends TestBase {

	
	WebDriver driver;
	
	public ClaimActivity(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;	
	}
	
	public void ClickNewEntry()
	{	
		WebElement newentrybutton = driver.findElement(By.xpath("//*[@id=\"frmMain\"]/div[3]/input[2]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", newentrybutton);
		Log("Clicked New Entry");
	}
	
	public void SelectSubjectMatter(String selectsubjectmatter)
	{
		WebElement submatter = driver.findElement(By.id("SubjectID"));
		new Select(submatter).selectByVisibleText(selectsubjectmatter);
		Log("Subject Matter: " + selectsubjectmatter + " Selected");
	}
	
	public void TypePaymentNoteDescription(String company, String invoicenum, String invoiceamt)
	{
		WebElement paynotedescription = driver.findElement(By.id("Comment"));
		paynotedescription.clear();
		paynotedescription.sendKeys("Received Invoice #" + invoicenum + " in the amount of $" + invoiceamt + ", issued payment to "
									+ company + " per adjusters request." );
		Log("\nEntered Company: " + company + "\nInvoice #: " + invoicenum + " \nInvoice Amount: " + invoiceamt);
	}
	
	public void TypeClearedChecksNoteDescription(String message)
	{
		WebElement clearedchecks = driver.findElement(By.id("Comment"));
		clearedchecks.clear();
		clearedchecks.sendKeys(message);
		Log("Entered Clear Checks note: " + message);
	}
	
	public void TypeSerengetMessage(String invoice, String amount, String pdffile)
	{
		WebElement serengetmsg = driver.findElement(By.id("Comment"));
		serengetmsg.clear();
		serengetmsg.sendKeys("Received Serengeti Invoice # " + invoice + " in the amount of $" + amount + ". Uploaded pdf file: " + pdffile + " in Images");
	}
	
	public void ClickSave()
	{	
		WebElement savebtn = driver.findElement(By.id("btnSave"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", savebtn);
		Log("Clicked Save");
	}
	
	public void ClickClose()
	{	
		WebElement closebtn = driver.findElement(By.xpath("//*[@id=\"pageScript\"]/form/div[5]/input[2]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", closebtn);
	}

	public void TypeAccountingNotationNoteDescription(String checknumber, String date, String amount) {
		
		WebElement accountingnotedescription = driver.findElement(By.id("Comment"));
		accountingnotedescription.clear();
		accountingnotedescription.sendKeys("Mailed unclaimed property due diligence letter to customer regarding CK #" + checknumber + " dated: " + date
				+ " in the amount of $" + amount + ". Please notify unclaimedprop@asicorp.org if customer calls or sends response on letter.");
	}

	public String ReturnClaimNumber() {	
		WebElement claimnumber = driver.findElement(By.id("PageTitle"));
		return claimnumber.getText();		
	}

	public void TypeHancockMessage(String invoicenum, String pdffile) 
	{
	
		WebElement Hancockmessage = driver.findElement(By.id("Comment"));
		Hancockmessage.clear();
		Hancockmessage.sendKeys("Received Hancock Invoice: " + invoicenum + " and uploaded pdf: " + pdffile);
		Log("Entered Hancock message");
		
	}
	
	/*public void ClickMenu()
	{	
		WebElement menubtn = driver.findElement(By.xpath("//*[@id=\"set_navMenu\"]/table/tbody/tr/td[3]/a"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", menubtn);
	}*/
}
