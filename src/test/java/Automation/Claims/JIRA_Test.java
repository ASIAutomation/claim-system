package Automation.Claims;

import org.apache.log4j.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import Automation.JIRA.pages.*;
import Automation.Claims.testBase.*;

public class JIRA_Test extends TestBase 
{
	
	public static final Logger log = Logger.getLogger(JIRA_Test.class.getName());

	
	Issue IS;
	
	@BeforeTest
	public void SetUp() throws InterruptedException 
	{
		SetLog();
		InitJIRA("Chrome");
		
		IS = new Issue(driver);
	}
	
	@Test
	public void jiratest() throws InterruptedException
	{
	
		WebElement clickpassnext = driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/content/span"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", clickpassnext);
		Pause(15000);
		GetURL("https://asicorp.atlassian.net/wiki/spaces/Home/blog/2019/02/15/94470325/BAPM+Friday+02+15+19");
		
		
		/*WebElement jiratask = driver.findElement(By.xpath("//a[@href='https://asicorp.atlassian.net/browse/ASD-683?src=confmacro']"));
		jiratask.click();
		Pause(5000);
		IS.clickInProgress();
		IS.clickconfirminprog();*/
		//IS.clickComment();
		//IS.commenttextbox();
		//IS.clickcommentInternally();
		//IS.clickEdit();
		//IS.clickfile();
		//IS.clickdownload();
	}
}
