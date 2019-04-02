import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class VersionLoadException implements ExpectedCondition<Boolean> {

	String versiondisplayed;
	String versionselected;
	
	
	
	
	public VersionLoadException(String versiondisplayed, String versionselected) {
		
		this.versiondisplayed = versiondisplayed;
		this.versionselected = versionselected;
	}




	@Override
	public Boolean apply(WebDriver driver) {
				
		//driver.manage().timeouts().pageLoadTimeout(6000, TimeUnit.MILLISECONDS);
		return(versiondisplayed.contains(versionselected));
	}
	
}
