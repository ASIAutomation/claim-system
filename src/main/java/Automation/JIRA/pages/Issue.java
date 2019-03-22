package Automation.JIRA.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Automation.Claims.testBase.TestBase;

public class Issue extends TestBase 
{

	WebDriver driver;
	
	public Issue(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;	
	}
	
	
	// ===== EDIT TAB ===== //
	
	public void clickEdit()
	{
		WebElement editbtn = driver.findElement(By.xpath("//*[@id=\"edit-issue\"]/span[2]"));
		editbtn.click();
		Log("Clicked Edit on issue");
	}
	
	public void editsummary()
	{
		WebElement editsumtxt = driver.findElement(By.id("summary"));
		editsumtxt.sendKeys("Test Summary 1");
		Log("Entered new summary");
	}
	
	public void editdate()
	{
		WebElement editdate = driver.findElement(By.id("duedate"));
		editdate.sendKeys("05/10/2019");
		Log("Entered new date");
	}
	
	public void clickEditCancel()
	{
		WebElement editcancelbtn = driver.findElement(By.xpath("//*[@id=\"edit-issue-dialog\"]/div[2]/div[1]/div/form/div[2]/div/a"));
		editcancelbtn.click();
		Log("Clicked Edit cancel on issue");
	}
	
	public void clickEditUpdate() 
	{
		WebElement editupdatebtn = driver.findElement(By.xpath("//*[@id=\"edit-issue-dialog\"]/div[2]/div[1]/div/form/div[2]/div/a"));
		editupdatebtn.click();
		Log("Clicked Edit update on issue");
	}
	
	// ===== COMMENT TAB ===== //
	
	public void clickComment()
	{
		WebElement commentbtn = driver.findElement(By.xpath("//*[@id=\"comment-issue\"]/span[2]"));
		commentbtn.click();
		Log("Clicked Comment on issue");
	}
	
	public void commenttextbox()
	{
		WebElement commenttxt = driver.findElement(By.id("comment"));
		commenttxt.sendKeys("Test Comment 1");
		Log("Clicked Comment on issue");
	}
	
	public void clicksharewithcustomercomment()
	{
		WebElement clicksharewithcustomer = driver.findElement(By.xpath("//*[@id=\"addcomment\"]/sd-comment/div/form/div[10]/span[1]/button"));
		clicksharewithcustomer.click();
		Log("Clicked share with customer on comment");
	}
	
	public void clickcommentInternally()
	{
		WebElement clickinternalcomment = driver.findElement(By.xpath("//*[@id=\"addcomment\"]/sd-comment/div/form/div[10]/span[2]/button"));
		clickinternalcomment.click();
		Log("Clicked comment internally on comment");
	}

	public void clickAssign()
	{
		WebElement assignbtn = driver.findElement(By.xpath("//*[@id=\"assign-issue\"]/span"));
		assignbtn.click();
		Log("Clicked assign on issue");
	}
	
	public void clickRespondtoCust()
	{
		WebElement rtcbtn = driver.findElement(By.xpath("//*[@id=\"action_id_851\"]/span"));
		rtcbtn.click();
		Log("Clicked respond to customer on issue");
	}
	
	public void clickInProgress()
	{
		WebElement ainprogbtn = driver.findElement(By.xpath("//*[@id=\"action_id_891\"]/span"));
		ainprogbtn.click();
		Log("Clicked in progress on issue");
	}
	
	public void clickconfirminprog()
	{
		WebElement confirminprog = driver.findElement(By.id("issue-workflow-transition-submit"));
		confirminprog.click();
		Log("Clicked update in progress on issue");
	}
	
	public void clickfile()
	{
		WebElement confirminprog = driver.findElement(By.xpath("//*[@id=\"attachment_thumbnails\"]/li/dl/dt/a"));
		confirminprog.click();
		Log("Clicked file on issue");
	}
	
	public void clickdownload()
	{
		WebElement clickdownload = driver.findElement(By.id("cp-control-panel-download"));
		clickdownload.click();
		Log("Clicked download on issue");
	}
	
	
	
	
	
}
