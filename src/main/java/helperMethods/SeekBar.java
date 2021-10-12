package helperMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import config.Constants;

public class SeekBar {
	
	WebElement seekBar;
	
	
	public void dragSeekBarTo(int perc,WebDriver driver,String seekBarPath) {
		
		seekBar	=driver.findElement(By.xpath(JsonUtils.getData(Constants.testData_Folder, seekBarPath)));
		
		
        // get location of seek bar from left
        int start=seekBar.getLocation().getX();
        System.out.println("Startpoint - " + start);
        
        //get location of seekbar from top
        int y=seekBar.getLocation().getY();
        System.out.println("Yaxis - "+ y);
        
        //Get total width of seekbar
        int end=start + seekBar.getSize().getWidth();
        System.out.println("End point - "+ end);
        
//        action = new TouchAction((PerformsTouchActions) MobileDriver.getDriver());
        
        //move slider to 70% of width
        int moveTo=(int)(end * ((float)perc/100));
        
//        action.longPress(PointOption.point(start,y))
//        	  .moveTo(PointOption.point(moveTo,y))
//        	  .release().perform();
	}
	
	

}
