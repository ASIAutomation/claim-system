package Automation.Claims.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.*;
import Automation.Claims.testBase.TestBase;

public class ClaimInfo extends TestBase {

	WebDriver driver;

	@FindBy(id="diaryDate")//Check Box
	WebElement didate;
	
	@FindBy(id="diaryNote")
	WebElement dinote;
	
	public ClaimInfo(WebDriver driver)	{
		PageFactory.initElements(driver, this);
		this.driver=driver;	
	}

	public void TypeHomePhone(String typehomephone)	{
		WebElement homephone = driver.findElement(By.id("txtHPhone"));
		homephone.clear();
		homephone.sendKeys(typehomephone);
			Log("Home Phone: " + typehomephone + " Typed in");
	}

	public void TypeWorkPhone(String typeworkphone)	{
		WebElement workphone = driver.findElement(By.id("txtWPhone"));
		workphone.clear();
		workphone.sendKeys(typeworkphone);
			Log("Work Phone: " + typeworkphone + " Typed in");
	}

	public void TypeCellPhone(String typecellphone)	{
		WebElement cellphone = driver.findElement(By.id("txtCell"));
		cellphone.clear();
		cellphone.sendKeys(typecellphone);
			Log("Cell Phone: " + typecellphone + " Typed in");
	}

	public void TypeFax(String fax_why_dont_you_send_it_over_on_a_dinosaur)	{
		WebElement faxnum = driver.findElement(By.id("txtFax"));
		faxnum.clear();
		faxnum.sendKeys(fax_why_dont_you_send_it_over_on_a_dinosaur);
			Log("Fax Number: " + fax_why_dont_you_send_it_over_on_a_dinosaur + " Typed in");
	}

	public void TypeContactName(String typecontactname)	{
		WebElement contactname = driver.findElement(By.id("txtFax"));
		contactname.clear();
		contactname.sendKeys(typecontactname);
			Log("Contact Name: " + typecontactname + " Typed in");
	}

	public void TypeEmailAddr(String typeemailaddr)	{
		WebElement emailaddr = driver.findElement(By.id("txtEmailAddr"));
		emailaddr.clear();
		emailaddr.sendKeys(typeemailaddr);
			Log("Email Address: " + typeemailaddr + " Typed in");
	}
	
	public void TypeDateofLoss(String typedateofloss) {
		WebElement dateofloss = driver.findElement(By.id("txtDOL"));
		dateofloss.clear();
		dateofloss.sendKeys(typedateofloss);
			Log("Date of Loss: " + typedateofloss + " Typed in");
	}

	public void TypeTimeofLoss(String typetimeofloss) {
		WebElement timeofloss = driver.findElement(By.id("txtTOL"));
		timeofloss.clear();
		timeofloss.sendKeys(typetimeofloss);
			Log("Time of Loss: " + typetimeofloss + " Typed in");
	}
	
	public void TypeReportedBy(String typereportedby) {
		WebElement reportedby = driver.findElement(By.id("txtReportedBy"));
		reportedby.clear();
		reportedby.sendKeys(typereportedby);
			Log("Reported By (Relationship to Insured: " + typereportedby + " Typed in");
	}
	
	public void TypePhone(String typephone) {
		WebElement phone = driver.findElement(By.id("txtLossPhone"));
		phone.clear();
		phone.sendKeys(typephone);
			Log("Phone " + typephone + " Typed in");
	}

	public void SelectLossLocation(String selectlosslocation) {
		WebElement losslocation = driver.findElement(By.id("ddlLossType"));
		new Select(losslocation).selectByVisibleText(selectlosslocation);
			Log("Peril: " + selectlosslocation + " Selected");
	}

	public void TypeAddressofLoss(String typeaddressofloss)	{
		WebElement addrofloss = driver.findElement(By.id("txtLossAddress"));
		addrofloss.clear();
		addrofloss.sendKeys(typeaddressofloss);
			Log("Address of loss: " + typeaddressofloss + " typed in");
	}

	public void TypeCityofLoss(String typecityofloss) {
		WebElement cityofloss = driver.findElement(By.id("txtLossCity"));
		cityofloss.clear();
		cityofloss.sendKeys(typecityofloss);
			Log("City of loss: " + typecityofloss + " typed in");
	}

	public void TypeStateofLoss(String typestateofloss)	{
		WebElement stateofloss = driver.findElement(By.id("txtLossState"));
		stateofloss.clear();
		stateofloss.sendKeys(typestateofloss);
			Log("State of loss: " + typestateofloss + " typed in");
	}
	
	public void TypeZipofLoss(String typezipofloss)	{
		WebElement zipofloss = driver.findElement(By.id("txtLossZip"));
		zipofloss.clear();
		zipofloss.sendKeys(typezipofloss);
			Log("Zip Code of loss: " + typezipofloss + " typed in");
	}
	
	public void SelectAuthoritiesContacted(String selectauthoritiescontacted) {
		if(selectauthoritiescontacted.equals("Yes")) {
			WebElement selectauthoritiescontactedyes = driver.findElement(By.id("rdoAuthContacted1"));
			selectauthoritiescontactedyes.click();
				Log("Selected authorities contacted: " + selectauthoritiescontacted);
		}
		else if(selectauthoritiescontacted.equals("No")) {
			WebElement selectauthoritiescontactedyes = driver.findElement(By.id("rdoAuthContacted0"));
			selectauthoritiescontactedyes.click();
				Log("Selected authorities contacted: " + selectauthoritiescontacted);
		}
	}

	public void TypeDepartmentContacted(String typedepartmentcontacted)	{
		WebElement departmentcontacted = driver.findElement(By.id("txtAuthDeptCont"));
		departmentcontacted.clear();
		departmentcontacted.sendKeys(typedepartmentcontacted);
			Log("Departed Contacted: " + typedepartmentcontacted + " Typed in");
	}

	public void TypeCaseNumber(String typecasenumber) {
		WebElement casenumber = driver.findElement(By.id("txtAuthCaseNum"));
		casenumber.clear();
		casenumber.sendKeys(typecasenumber);
			Log("Case Number: " + typecasenumber + " Typed in");
	}

	public void TypeOfficerName(String typeofficername)	{
		WebElement officername = driver.findElement(By.id("txtAuthOfficerName"));
		officername.clear();
		officername.sendKeys(typeofficername);
			Log("Officer Name: " + typeofficername + " Typed in");
	}

	public void TypeOfficerPhoneNumber(String typeofficerphonenumber) {
		WebElement officerphonenumber = driver.findElement(By.id("txtAuthOfficerNum"));
		officerphonenumber.clear();
		officerphonenumber.sendKeys(typeofficerphonenumber);
			Log("Officer Phone Number: " + typeofficerphonenumber + " Typed in");
	}

	public void SelectPeril(String selectperil) {
		WebElement peril = driver.findElement(By.id("cmboLossPeril"));
		new Select(peril).selectByVisibleText(selectperil);
			Log("Peril: " + selectperil + " Selected");
	}

	public void SelectCustomSubPeril(String selectcustomsubperil) {
		WebElement customersubperil = driver.findElement(By.id("cmboLossSubPeril"));
		new Select(customersubperil).selectByVisibleText(selectcustomsubperil);
			Log("Custom Sub-Peril: " + selectcustomsubperil + " Selected");
	}

	public void TypeSeverityLevel(String typeseveritylevel) {
		WebElement severitylevel = driver.findElement(By.id("txtEstimatedLoss"));
		severitylevel.clear();
		severitylevel.sendKeys(typeseveritylevel);
			Log("Severitylevel: " + typeseveritylevel + " Typed in");
	}

	public void TypeDescriptionOfLoss(String typedescriptionofloss) {
		WebElement descriptionofloss = driver.findElement(By.id("txtLossDescription"));
		descriptionofloss.clear();
		descriptionofloss.sendKeys(typedescriptionofloss);
			Log("Officer Phone Number: " + typedescriptionofloss + " Typed in");
	}
	
	public void SelectClaimStatus(String selectclaimstatus, String typereopenreason) {
		WebElement claimstat = driver.findElement(By.id("cmboStatus"));
		new Select(claimstat).selectByVisibleText(selectclaimstatus);
			Log("Claim Status: " + selectclaimstatus + " Selected");
		if(selectclaimstatus.equals("Closed")) {
			return;
		}		
		else {
			WebElement reopenreason = driver.findElement(By.id("txtReopenReason"));
			reopenreason.sendKeys(typereopenreason);
				Log("Reopen reason: " + typereopenreason + " typed in");
		}
	}
	
	public void SelectSpecialHandling(String selectspecialhandling)	{
		WebElement specialhandling = driver.findElement(By.id("ddSpecialHandling"));
		new Select(specialhandling).selectByVisibleText(selectspecialhandling);
			Log("Special Handling: " + selectspecialhandling + " Selected");
	}
	
	public void SelectSpecialHandlingStatus(String selectspecialhandlingstatus)	{
		WebElement specialhandlingstat = driver.findElement(By.id("ddlStatusIs"));
		new Select(specialhandlingstat).selectByVisibleText(selectspecialhandlingstatus);
			Log("Special Handling Status: " + selectspecialhandlingstatus + " Selected");
	}
	
	public void SelectClaimant(String selectclaimant)	{
		if(selectclaimant.equals("Yes")) {
			WebElement claimanty = driver.findElement(By.xpath("//*[@id=\"divSpecialHandling\"]/div[2]/div[2]/table/tbody/tr[8]/td[2]/input[1]"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", claimanty);
				Log("Special Handling Claimant: " + selectclaimant + " Selected");
		}
		else if(selectclaimant.equals("No")) {
			WebElement claimantn = driver.findElement(By.xpath("//*[@id=\"divSpecialHandling\"]/div[2]/div[2]/table/tbody/tr[8]/td[2]/input[2]"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", claimantn);
				Log("Special Handling Claimant: " + selectclaimant + " Selected");
		}
	}
	
	public void SelectAttorneyRep(String selectattorneyrep)	{
		if(selectattorneyrep.equals("Yes")) {
			WebElement attrepy = driver.findElement(By.xpath("//*[@id=\"divSpecialHandling\"]/div[2]/div[2]/table/tbody/tr[10]/td[2]/input[1]"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", attrepy);
				Log("Attorney Rep: " + selectattorneyrep + " Selected");
		}
		else if(selectattorneyrep.equals("No")) {
			WebElement attrepn = driver.findElement(By.xpath("//*[@id=\"divSpecialHandling\"]/div[2]/div[2]/table/tbody/tr[10]/td[2]/input[2]"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", attrepn);
				Log("Attorney Rep: " + selectattorneyrep + " Selected");
		}
	}
	
	public void SelectPublicAdj(String selectpublicadj)	{
		if(selectpublicadj.equals("Yes")) {
			WebElement pubadjy = driver.findElement(By.xpath("//*[@id=\"divSpecialHandling\"]/div[2]/div[2]/table/tbody/tr[12]/td[2]/input[1]"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", pubadjy);
				Log("Public Adj: " + selectpublicadj + " Selected");
		}		
		else if(selectpublicadj.equals("No")) {
			WebElement pubadjn = driver.findElement(By.xpath("//*[@id=\"divSpecialHandling\"]/div[2]/div[2]/table/tbody/tr[12]/td[2]/input[2]"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", pubadjn);
				Log("Public Adj: " + selectpublicadj + " Selected");
		}
	}
	
	public void SelectAOBReceived(String selectaboreceived) {
		if(selectaboreceived.equals("Yes")) {
			WebElement aoby = driver.findElement(By.xpath("//*[@id=\"divSpecialHandling\"]/div[2]/div[2]/table/tbody/tr[14]/td[2]/input[1]"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", aoby);
				Log("AOB Received: " + selectaboreceived + " Selected");
		}		
		else if(selectaboreceived.equals("No")) {
			WebElement aobn = driver.findElement(By.xpath("//*[@id=\"divSpecialHandling\"]/div[2]/div[2]/table/tbody/tr[14]/td[2]/input[2]"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", aobn);
				Log("AOB Received: " + selectaboreceived + " Selected");
		}
	}
	
	public void SelectStormCode(String selectstormcode)	{
		WebElement stormcode = driver.findElement(By.id("cmboCatastrophes"));
		new Select(stormcode).selectByVisibleText(selectstormcode);
			Log("Storm Code: " + selectstormcode + " Selected");
	}
	
	public String ReturnStormCode()	{
		WebElement stormcode = driver.findElement(By.id("cmboCatastrophes"));
		return new Select(stormcode).getFirstSelectedOption().getText();
	}
	
	public String ReturnContactAssist()	{
		WebElement contactassist = driver.findElement(By.id("cmboContactAssistance"));
		return new Select(contactassist).getFirstSelectedOption().getText();
	}
	
	public void SelectSeverityLevel(String selectseverity)	{
		WebElement severitylevel = driver.findElement(By.id("cmboSeverity"));
		new Select(severitylevel).selectByVisibleText(selectseverity);
			Log("Severity Level: " + selectseverity + " Selected");
	}

	public void SelectClaimAdjuster(String claimadj) {
		WebElement claimadju = driver.findElement(By.id("cmboLossHandler"));
		new Select(claimadju).selectByVisibleText(claimadj);
			Log("Claim Adjuster: " + claimadj + " Selected");
	}

	public void SelectSplitHandler1(String selectsplithandler1) {
		WebElement splithand1 = driver.findElement(By.id("cmboSplitHandler"));
		new Select(splithand1).selectByVisibleText(selectsplithandler1);
			Log("Split Handler 1: " + selectsplithandler1 + " Selected");
	}

	public void SelectSplitHandler2(String selectsplithandler2) {
		WebElement splithand2 = driver.findElement(By.id("cmboSplitHandler2"));
		new Select(splithand2).selectByVisibleText(selectsplithandler2);
			Log("Split Handler 2: " + selectsplithandler2 + " Selected");
	}

	public void SelectSplitHandler3(String selectsplithandler3) {
		WebElement splithand3 = driver.findElement(By.id("cmboSplitHandler3"));
		new Select(splithand3).selectByVisibleText(selectsplithandler3);
			Log("Split Handler 3: " + selectsplithandler3 + " Selected");
	}

	public void SelectContactAssistance(String selectcontactassistance) {
		WebElement splithand2 = driver.findElement(By.id("cmboContactAssistance"));
		try {
			new Select(splithand2).selectByVisibleText(selectcontactassistance);
				Log("Contact Assistance: " + selectcontactassistance + " Selected");
		}
		catch (NoSuchElementException e) {
				Log("=== CASE FAILED DUE TO NAME MISMATCH; NAME:  " + selectcontactassistance + " NOT FOUND ===");
		}
		catch (StaleElementReferenceException e) {
				Log("Stale Element caught");
		}
	}

	public void ClickClaimActivity() {	
		WebElement claimactivitytab = driver.findElement(By.xpath("//*[@id=\"wrap_ClaimEntryNavMenu\"]/table/tbody/tr[1]/td[2]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", claimactivitytab);
	}

	public String ReturnClaimNumber() {	
		WebElement claimnumber = driver.findElement(By.id("PageTitle"));
		return claimnumber.getText();		
	}
	
	public void ClickDiaryCodeOK() {
		WebElement diarycodeokbuutton = driver.findElement(By.xpath("(//button[contains(text(),'OK')])[last()]"));
		if(diarycodeokbuutton.isDisplayed()) {
			diarycodeokbuutton.click();
				Log("Clicked \"OK\" on the Diary Code");
		}
	}
	
	public void DiaryInfo(String diarydate, String diarynote)
	{
		
		boolean isPresent = driver.findElements(By.id("diaryDate")).size() > 0;
		if (isPresent == true)
		{
			didate.clear();
			didate.sendKeys(diarydate);
			dinote.clear();
			dinote.sendKeys(diarynote);
			ClickSave();
			ClickClaimInfo();
		}
		
		else
		{
			return;
		}
		
		Log("Entered Diary date: " + diarydate);
	}	
	
	public void TypeDiaryNote(String diarynote)
	{
		WebElement dinote = driver.findElement(By.id("diaryNote"));
		if (dinote.isDisplayed())
		{
			dinote.clear();
			dinote.sendKeys(diarynote);
			Log("Entered diary note");
		}
		else
		{
			Log("Element not found");
			return;
		}
		
	}
	
	public void ClickClaimInfo() {
		WebElement claiminfotab = driver.findElement(By.xpath("//*[@id=\"wrap_ClaimEntryNavMenu\"]/table/tbody/tr[1]/td[1]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", claiminfotab);
			Log("Clicked Claim Info");
	}	
	
	public void ClickClaimCoverage() {
		WebElement claimcovtab = driver.findElement(By.xpath("//*[@id=\"wrap_ClaimEntryNavMenu\"]/table/tbody/tr[1]/td[3]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", claimcovtab);
	    Log("Clicked Claim Coverage");
	}
	
	public void ClickImages() {
		WebElement imagelink = driver.findElement(By.xpath("//*[@id=\"wrap_ClaimEntryNavMenu\"]/table/tbody/tr[2]/td[3]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", imagelink);
	    Log("Clicked Policy Link");	
	}
	
	
	public void ClickPolicyLink() {
		WebElement polnumberlink = driver.findElement(By.xpath("//*[@id=\"wrap_dataHeader\"]/div[2]/div/table[1]/tbody/tr[2]/td[2]/a"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", polnumberlink);
	    Log("Clicked Policy Link");	
	}
	
	public String ReturnErrorText() {
		WebElement errortxt = driver.findElement(By.id("errorMessage"));
		return errortxt.getText().trim().replace("** ERROR ** The following errors were found on this page: ** ERROR **", "");
	}
	
	public void ClickSave()	{
		try {
			WebElement save = driver.findElement(By.xpath("(//input[@id='btnSave'])[last()]"));
			save.click();
				Log("Clicked \"Save\" on the Claim Info page");
		}
		catch (StaleElementReferenceException e) {
				Log("Stale Element caught");
		}	
	}	
}