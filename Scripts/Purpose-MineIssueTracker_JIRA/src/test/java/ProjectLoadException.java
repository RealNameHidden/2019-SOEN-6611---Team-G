import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ProjectLoadException implements ExpectedCondition<Boolean> {

	String projectdisplayed;
	String projectselected;
	
	
	
	
	public ProjectLoadException(String projectdisplayed, String projectselected) {
		
		this.projectdisplayed = projectdisplayed;
		this.projectselected = projectselected;
	}




	@Override
	public Boolean apply(WebDriver driver) {
				
		//driver.manage().timeouts().pageLoadTimeout(6000, TimeUnit.MILLISECONDS);
		return(projectdisplayed.equalsIgnoreCase(projectselected));
	}
	
}
