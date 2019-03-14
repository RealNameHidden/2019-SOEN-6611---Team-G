import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;


public class DefectCountLoad implements ExpectedCondition<Boolean> {
	
	String TotalDefectCount;
	String CurrentDefectCount;
	String PreviousdefectCount;
	
	
	public DefectCountLoad(String totalDefectCount, String currentDefectCount,String PreviousdefectCount) {
		this.TotalDefectCount = totalDefectCount;
		this.CurrentDefectCount = currentDefectCount;
		this.PreviousdefectCount=PreviousdefectCount;
	}
	
	
	@Override
	public Boolean apply(WebDriver driver) {
		
		boolean condition1=!TotalDefectCount.equalsIgnoreCase(CurrentDefectCount);
		boolean condition2=!CurrentDefectCount.equalsIgnoreCase(PreviousdefectCount);
		
		
		return(condition1 && condition2);
	}
	
	
}
