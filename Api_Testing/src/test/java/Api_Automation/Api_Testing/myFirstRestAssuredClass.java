package Api_Automation.Api_Testing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class myFirstRestAssuredClass extends BaseTest {


	@Test
	public void test1() throws IOException {
		test = extent.createTest("testtt");
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\nm87\\eclipse-workspace\\Api_Testing\\Repository\\Login.properties");
		pro.load(fis);
		driver.get(pro.getProperty("url"));
		driver.findElement(By.id("ggg")).click();
		Assert.assertFalse(true);
	

	}
	@Test
	public void test2() throws IOException {
		test = extent.createTest("test2");		
		driver.get("https://www.guru99.com/take-screenshot-selenium-webdriver.html");
		Assert.assertFalse(false);
	}
}
