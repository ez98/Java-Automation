package automate;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
/*
 * Unfinished automation script to purchase Playstation 5.
 */
public class AutomateTest {

	public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.target.com/p/playstation-5-digital-edition-console/-/A-81114596#lnk=sametab");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            try {
            	WebElement shipIt = driver.findElement(By.xpath("/html/body/div[1]/div/div[5]/div/div[2]/div[3]/div[1]/div/div[3]/div[1]/div[2]/button"));
            	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            	if (shipIt.isDisplayed()){
            		shipIt.click();
            		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            		WebElement denyCoverage = driver.findElement(By.xpath("/html/body/div[15]/div/div/div/div/div/div/div/div[2]/div[2]/button"));
            		denyCoverage.click();
            		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            		WebElement purchase = driver.findElement(By.xpath("/html/body/div[15]/div/div/div/div/div/div/div[2]/div[3]/button"));
            		purchase.click();
            		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            	}
            } catch(Exception e) {
            	System.out.println("Not Yet Available. Trying Again Later.");
            }
            WebElement status = driver.findElement(By.xpath("/html/body/div[1]/div/div[5]/div/div[2]/div[3]/div[1]/div/div/div"));
            System.out.println("Status: " + status.getText());
            
        } finally {
            driver.quit();
            }

	}

}
