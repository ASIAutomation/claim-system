package Automation.Claims.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.awt.event.KeyEvent;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import Automation.Claims.testBase.TestBase;

//@SuppressWarnings("unused")
public class Images extends TestBase 
{
	
	public static final Logger log = Logger.getLogger(Images.class.getName());
	
	WebDriver driver;
	
	@FindBy(xpath="//*[@id=\"dZUpload\"]/div[2]/label")//Button
	WebElement select;
	
	@FindBy(xpath="//*[@id=\"btnUpload\"]")//Button
	WebElement upload;
	
	public Images(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;		
	}
	
	public void clickSelectFile()
	{
		select.click();
		Log("Clicked Select File on 'Images' Page");

	}
	public void Select(String pdfname) throws AWTException 
	{
	
		Robot robo = new Robot();
		
		robo.setAutoDelay(2000);
		//StringSelection str = new StringSelection("T:\\Jmilkey\\" + pdfname + ".pdf");
		//StringSelection str = new StringSelection("H:\\Claims Support\\Bulk Invoices\\Hancock\\Invoices\\Test Batch\\" + pdfname + ".pdf");
		//StringSelection str = new StringSelection("C:\\Users\\jmilkey\\Hancock\\" + pdfname + ".pdf");
		StringSelection str = new StringSelection("H:\\Claims Support\\Bulk Invoices\\Seregeti\\Invoices\\" + pdfname + ".pdf");
		//StringSelection str = new StringSelection("C:\\Users\\jmilkey\\Desktop\\Bp\\" + pdfname + ".pdf");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		
		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_V);
		
		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyRelease(KeyEvent.VK_V);
		
		robo.keyPress(KeyEvent.VK_ENTER);
		robo.keyRelease(KeyEvent.VK_ENTER);
		
	}
	
	public void ClickUpload()
	{
		upload.click();
		Log("Clicked Upload on 'Images' page");
	}
	
}

