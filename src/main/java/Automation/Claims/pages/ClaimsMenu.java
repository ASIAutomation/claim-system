package Automation.Claims.pages;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import Automation.Claims.testBase.TestBase;


public class ClaimsMenu extends TestBase 
{
	public static final Logger log = Logger.getLogger(ClaimsMenu.class.getName());
	
	WebDriver driver;
	
	public ClaimsMenu(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;		
	}
	
	public void clickSearchClaim()
	{
		WebElement searchclaim = driver.findElement(By.xpath("//*[@id=\"wrap_DynamicMenu\"]/table/tbody/tr[3]/td"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", searchclaim);
	}
}
