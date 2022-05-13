package Pages;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;
import com.google.common.io.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import Base.Base;

public class ErrorCapture extends Base {
public static String date(){
		
		Date date = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat(" E_dd_MM_yyyy_hh_mm_ss_a");
		String stringDate= DateFor.format(date);
		return stringDate;

	}
	public static String[] data;
	By opt = By.xpath("//a[text()='For Enterprise']");
	By campus = By.xpath("/html/body/div[3]/div[1]/div[2]/ul[1]/li[12]/a");
	By fname = By.id("FirstName");
	By lname = By.id("LastName");
	By func = By.id("C4C_Job_Title__c");
	By title = By.id("Title");
	By email = By.id("Email");
	By phone = By.id("Phone");
	By comp = By.id("Company");
	By type = By.id("Institution_Type__c");
	By disc = By.id("Primary_Discipline__c");
	By country = By.id("Country");
	By submit = By.xpath("//button[text()='Submit']");

	public void error() throws Exception{
		
			data=ExcelRead.readExcelData("form data");
		
		exttest = report.createTest("Display error during invalid Email Address. ");
		wait(30, opt);
		driver.findElement(opt).click();
		wait(30, campus);
		driver.findElement(campus).click();
		exttest.log(Status.PASS, "Moved to For Campus Successfully");
		//Filling the ready to transform form as per excel data
		driver.findElement(By.id("FirstName")).sendKeys(data[0]);
		driver.findElement(By.id("LastName")).sendKeys(data[1]);
		driver.findElement(By.id("Title")).sendKeys(data[2]);
		driver.findElement(By.id("Email")).sendKeys(data[3]);
		driver.findElement(By.id("Phone")).sendKeys(data[4]);
		driver.findElement(By.id("Company")).sendKeys(data[5]);

		Select select=new Select(driver.findElement(By.id("Institution_Type__c")));
		select.selectByVisibleText(data[6]);

		Select select1=new Select(driver.findElement(By.id("Primary_Discipline__c")));
		select1.selectByVisibleText(data[7]);

		Select select2=new Select(driver.findElement(By.id("Country")));
		select2.selectByVisibleText(data[8]);

		Select select3=new Select(driver.findElement(By.id("State")));
		select3.selectByVisibleText(data[9]);
		driver.findElement(submit).click();
		Thread.sleep(2000);
		exttest.log(Status.PASS, "Data entered and submitted Successfully");
		TakesScreenshot capture = (TakesScreenshot) driver;
		File srcFile = capture.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "/Screenshot/Error"+date()+".png");
		Files.copy(srcFile, destFile);
		exttest.log(Status.PASS, "Screenshot taken Successfully");
		driver.close();
		System.out.println("Automation Completed Successfully");
		Thread.sleep(3000);
	}
}
