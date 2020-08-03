package Api_Automation.Api_Testing;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	ExtentHtmlReporter htmlreport;
	ExtentReports extent;
	ExtentTest test;
	public static WebDriver driver;

	@Parameters("browser")
	@BeforeSuite
	public void intializeDriver(String browser) {
		
		if(browser.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().version("83.0").setup();
		driver = new ChromeDriver();
		}
}	
	

	@BeforeClass
	public void reporter() {
		htmlreport = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\ExtentReportResults.html");
		extent = new ExtentReports();
		htmlreport.getSystemAttributeContext();
		extent.attachReporter(htmlreport);
		
	}

	@AfterMethod
	public void getResults(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.SUCCESS)
		{
			test.pass(MarkupHelper.createLabel(result.getName() + " test passed", ExtentColor.GREEN));
			test.addScreenCaptureFromPath(getScreenshotPath());
		} 
		else if (result.getStatus() == ITestResult.FAILURE)
		{
			test.fail(MarkupHelper.createLabel(result.getName() + " test filed", ExtentColor.RED));
			test.addScreenCaptureFromPath(getScreenshotPath());
			result.getThrowable();
		}

	}

	@AfterSuite
	public void tearDown() {
		extent.flush();
		driver.quit();
	}

	public String getScreenshotPath() throws IOException {

		 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 TakesScreenshot ts = (TakesScreenshot) driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		 //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		 String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+dateName+".png";
		 File finalDestination = new File(destination);
		 FileUtils.copyFile(source, finalDestination);
	 //Returns the captured file path
		 return destination;
		
	}

}
