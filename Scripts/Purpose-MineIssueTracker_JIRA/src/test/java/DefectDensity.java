import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import junit.framework.Assert;



public class DefectDensity {

	
	
	private int row,i;
	
	private XSSFWorkbook workbook = new XSSFWorkbook();
	private XSSFSheet sheet = workbook.createSheet("DefectList");
    private static final String DefectCount = "./DataCollection/DefectCountPerProject.xlsx",DefectWithNoVersion = "./DataCollection/DefectWithNoVersion.xlsx",
    		DefectWithVersionUnspecified = "./DataCollection/DefectWithVersionUnspecified.xlsx"
    		,ProjectVersions_deltaspike = "./DataCollection/ProjectVersions_deltaspike.xlsx",
    				ProjectVersions_mavendoxia = "./DataCollection/ProjectVersions_mavendoxia.xlsx",
    						ProjectVersions_mavenshade = "./DataCollection/ProjectVersions_mavenshade.xlsx",
    								ProjectVersions_commonscodec = "./DataCollection/ProjectVersions_commonscodec.xlsx",
    										ProjectVersions_commonsconfig = "./DataCollection/ProjectVersions_commonsconfig.xlsx";   		
    		
    		
    		
    		
    		
   
    private String defectcount,project,version,bugversiondisplayed;
   // private ArrayList<WebElement> projectNamesinPicklist=new ArrayList<>(),VersionNamesinPicklist=new ArrayList<>();//,ExceptionOccurances=new ArrayList<>();
	
	
	By picklist_version;
	By picklist_project;
	By picklist_addfields;
	By by_defectcount;
	By projectName;
	By versionpicklist_Noversion;
	By projectpicklist_valuesList;
	By addfields_version;
	By clickedProject;
	By issue_searchbox;
	By versionpicklist_unspecified;
	By create_issue;
	By versionpicklist_valuelist;
	By selectedproject;
	By clickedversion;
	By by_bugversionDisplayed;
	By by_projectNameinDetailpage;
	
	
	WebDriver driver;
	WebDriverWait wait;
	Robot rob;
	JavascriptExecutor js;
		
	
		
	public void DriverSetup() throws AWTException {
    	DriverFactory.getinstance().setchromeDriver();
		driver=DriverFactory.getinstance().getDriver().get();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,10);
		js=(JavascriptExecutor)driver;
		rob=new Robot();
	//	System.out.println("Driversetup "+Thread.currentThread().getId()+"driver "+driver);
				
    }
	
	
	public void highlight(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.backgroundColor = 'rgb(0,200,0)'", element);
		
	}

	public void InitialiseObjects() {
		System.out.println(" Intialise "+Thread.currentThread().getId());
		picklist_version=By.cssSelector("button[data-id='version']");
		by_defectcount=By.cssSelector("li.showing");
		projectName=By.cssSelector("a[id='project-name-val']");
		versionpicklist_Noversion=By.cssSelector("label[title='No Version']");
		picklist_project=By.cssSelector("button[data-id='project']");
		projectpicklist_valuesList=By.cssSelector("ul#all-projects>li>label");
		picklist_addfields=By.cssSelector("button.add-criteria.aui-button.aui-button-subtle.drop-arrow");
		addfields_version=By.cssSelector("label[title='Affects Version']");
		issue_searchbox=By.cssSelector("form#issue-filter input[placeholder='Search']");
		versionpicklist_unspecified=By.cssSelector("label[title='unspecified']");
		create_issue=By.cssSelector("a.create-issue");
		versionpicklist_valuelist=By.cssSelector("ul#released-versions li.check-list-item>label");
		by_bugversionDisplayed=By.xpath("//span[@id='versions-val']/span/span");
		by_projectNameinDetailpage=By.cssSelector("a#project-name-val");
		//selectedproject=By.cssSelector("//label[title='Struts 1']");
		
		
	}
	
	public String getAffectedVersionList(List<WebElement> lists) {
		String result="";
		for(int i=0;i<lists.size();i++)
			result=result+lists.get(i).getText();
		return result;
			
	}
	
	public void WriteData(String data1,String data2,String filename) {
		int colnum=0;
		Cell cell;
		Row row1=sheet.createRow(row);
		cell=row1.createCell(colnum);
		cell.setCellValue(data1);
		cell=row1.createCell(++colnum);
		cell.setCellValue(data2);
		
		row++;
			
		 try {
	         FileOutputStream outputStream = new FileOutputStream(filename);
	         workbook.write(outputStream);
	         
	     } catch (FileNotFoundException e) {
	         e.printStackTrace();
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
		
		
	}
	
	
	public void AddVersionField() {
		
		driver.findElement(picklist_addfields).click();
		driver.findElement(addfields_version).click();
		//Thread.sleep(1000);
		rob.keyPress(KeyEvent.VK_ESCAPE);
		rob.keyRelease(KeyEvent.VK_ESCAPE);
		
		
	}
	
	
	
	
	public void LoopProjects(String filename) {
		ArrayList<WebElement> projectNamesinPicklist=new ArrayList<>();
		
		String totaldefects=driver.findElement(by_defectcount).getText().substring(5);
		String previousdefect=null;
		
		
		System.out.println(totaldefects);
		js.executeScript("arguments[0].click();", driver.findElement(picklist_project));
		projectNamesinPicklist=(ArrayList<WebElement>) driver.findElements(projectpicklist_valuesList);
		for(i=0;i<projectNamesinPicklist.size();i++)
		{
			try{
			
			project=driver.findElements(projectpicklist_valuesList).get(i).getAttribute("title");
			clickedProject=By.cssSelector("label[title='"+project+"']");
			driver.findElements(projectpicklist_valuesList).get(i).click();
			rob.keyPress(KeyEvent.VK_ESCAPE);
			rob.keyRelease(KeyEvent.VK_ESCAPE);
			//Thread.sleep(1000);
			try {
			
			wait.until(new ProjectLoadException(project,driver.findElement(by_projectNameinDetailpage).getText()));
			
			}
			catch(TimeoutException e) {
				wait.until(new ProjectLoadException(project,driver.findElement(by_projectNameinDetailpage).getText()));
			}
			highlight(driver.findElement(by_defectcount));
			defectcount=driver.findElement(by_defectcount).getText().substring(5);
			System.out.println(project+" "+driver.findElement(by_projectNameinDetailpage).getText());
								
			//previousdefect=defectcount;
			WriteData(project,defectcount,filename);
			driver.findElement(picklist_project).click();
			//Thread.sleep(1000);
			driver.findElement(clickedProject).click();
			System.out.println(i+"Project Name "+project);
				
		
		}
			catch(Exception ex) {
	
				WriteData("Exception "+project,null,filename);
				driver.findElement(picklist_project).click();
				driver.findElement(clickedProject).click();
				
		}
	}
	}

	

	public void NoVersionDefects() {
	
		AddVersionField();
		driver.findElement(picklist_version).click();
		driver.findElement(versionpicklist_Noversion).click();
		LoopProjects(DefectWithNoVersion);
		
	}
	
	public void UnspecifiedVersionDefects() throws InterruptedException {
		ArrayList<WebElement> projectNamesinPicklist=new ArrayList<>();
		
		
		AddVersionField();
		driver.findElement(picklist_version).click();
		driver.findElement(issue_searchbox).sendKeys("unspecified");
		driver.findElement(versionpicklist_unspecified).click();
		
		js.executeScript("arguments[0].click();", driver.findElement(picklist_project));
		projectNamesinPicklist=(ArrayList<WebElement>) driver.findElements(projectpicklist_valuesList);
		
		for(i=0;i<projectNamesinPicklist.size();i++)
		{
			try{
			
			project=driver.findElements(projectpicklist_valuesList).get(i).getAttribute("title");
			clickedProject=By.cssSelector("label[title='"+project+"']");
		
			try {
				driver.findElements(projectpicklist_valuesList).get(i).click();
			}
			catch(Exception ex)
			{
				driver.findElements(projectpicklist_valuesList).get(i).click();
			}
			
			rob.keyPress(KeyEvent.VK_ESCAPE);
			rob.keyRelease(KeyEvent.VK_ESCAPE);
			Thread.sleep(1000);
			Assert.assertEquals(driver.findElement(create_issue).isDisplayed(),true);
			driver.findElement(picklist_project).click();
			driver.findElement(clickedProject).click();
				}
			catch(AssertionError  | NoSuchElementException ex) {
			
				highlight(driver.findElement(by_defectcount));
				defectcount=driver.findElement(by_defectcount).getText().substring(5);
				WriteData(project,defectcount,DefectWithVersionUnspecified);
				driver.findElement(picklist_project).click();
				driver.findElement(clickedProject).click();
			
		
		}
		}
	
		}
	
public void FindVersions(String filename,By selectedproject) throws InterruptedException {
	ArrayList<WebElement> VersionNamesinPicklist=new ArrayList<>();
	String AffectedVersionperDefect;
	
	
	driver.get("https://issues.apache.org/jira/browse/INFRA-14271?jql=issuetype%20%3D%20Bug%20ORDER%20BY%20cf%5B12312823%5D%20ASC%2C%20created%20ASC");
	driver.findElement(picklist_project).click();
	Thread.sleep(1000);
	driver.findElement(selectedproject).click();
	AddVersionField();
	Thread.sleep(1000);
	driver.findElement(picklist_version).click();
	
	/*Handle glitch in fetching version values*/
	VersionNamesinPicklist=(ArrayList<WebElement>) driver.findElements(versionpicklist_valuelist);
	if(VersionNamesinPicklist.size()==0) {
		rob.keyPress(KeyEvent.VK_ESCAPE);
		rob.keyRelease(KeyEvent.VK_ESCAPE);
		driver.findElement(picklist_version).click();
		VersionNamesinPicklist=(ArrayList<WebElement>) driver.findElements(versionpicklist_valuelist);
	}

	for(int i=0;i<VersionNamesinPicklist.size();i++) {
		boolean flag=false;
		try {
		if(i==VersionNamesinPicklist.size()-1) /* To include version listed at index position 1 in the loop*/
		{
			i=1;flag=true;
			
		}
		version=(String) driver.findElements(versionpicklist_valuelist).get(i).getAttribute("title");
		clickedversion=By.cssSelector("label[title='"+version+"']");
		driver.findElements(versionpicklist_valuelist).get(i).click();
		rob.keyPress(KeyEvent.VK_ESCAPE);
		rob.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(1000);
			
		
		AffectedVersionperDefect=(driver.findElements(by_bugversionDisplayed).size()>1)?(getAffectedVersionList(driver.findElements(by_bugversionDisplayed))):(driver.findElement(by_bugversionDisplayed).getText());
		
		
		wait.until(new VersionLoadException(AffectedVersionperDefect,version));
		
		highlight(driver.findElement(by_defectcount));
		defectcount=driver.findElement(by_defectcount).getText().substring(5);
		
		WriteData(version,defectcount,filename);
		driver.findElement(picklist_version).click();
		//Thread.sleep(1000);
		driver.findElement(clickedversion).click();
	//	System.out.println(i+"Version Name "+version);
		i=(flag==true)?(VersionNamesinPicklist.size()-1):(i);
			
	
	}
		catch(Exception ex) {

			WriteData("Exception "+ex.toString(),null,filename);
			//System.out.println(ex.toString());;
			ex.printStackTrace();
			driver.findElement(picklist_version).click();
			driver.findElement(clickedversion).click();
			
	}
	
	}
	
	}
	
	
	@BeforeMethod
	public void InitialSetup() throws AWTException {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("DefectList");
		row=0;
		DriverSetup();
		InitialiseObjects();
		driver.get("https://issues.apache.org/jira/browse/INFRA-14271?jql=issuetype%20%3D%20Bug%20ORDER%20BY%20cf%5B12312823%5D%20ASC%2C%20created%20ASC");
		System.out.println("BeforeTest "+Thread.currentThread().getId());
		
		
		
	}
	
	@Test
	public void ProjectList() throws IOException {
		try {
		LoopProjects(DefectCount);
		workbook.close();
	}
		catch(Exception ex)
		{
			
		}
		}
	
	
	@Test
	public void DefectwithoutVersion() throws IOException {
		try {
		NoVersionDefects();
		
		workbook.close();
		}
		catch(Exception ex)
		{
			
		}
	}
	
	
	@Test
	public void UnspecifiedVersion() throws IOException, InterruptedException
	{
		try {
		UnspecifiedVersionDefects();
		workbook.close();
		}
		catch(Exception ex)
		{
			
		}
	}
	@Test
	public void IdentifyProjects() throws AWTException, InterruptedException, IOException {
		
		
		selectedproject=By.cssSelector("label[title='Maven Doxia']");
		FindVersions(ProjectVersions_mavendoxia,selectedproject);
		workbook.close();
	
		
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("DefectList");
		row=0;
		selectedproject=By.cssSelector("label[title='Commons Codec']");
		FindVersions(ProjectVersions_commonscodec,selectedproject);
		workbook.close();
	
		
		
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("DefectList");
		row=0;
		selectedproject=By.cssSelector("label[title='Commons Configuration']");
		FindVersions(ProjectVersions_commonsconfig,selectedproject);
		workbook.close();
	
		
		
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("DefectList");
		row=0;
		selectedproject=By.cssSelector("label[title='Maven Shade Plugin']");
		FindVersions(ProjectVersions_mavenshade,selectedproject);
		workbook.close();

		
		
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("DefectList");
		row=0;
		selectedproject=By.cssSelector("label[title='DeltaSpike']");
		FindVersions(ProjectVersions_deltaspike,selectedproject);
		
		
	}
	
	
	@AfterMethod
	public void TearDown() throws IOException {
		
		driver.close();
	}
	
	
	
	
	
}
