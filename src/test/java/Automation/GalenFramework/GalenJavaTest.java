package Automation.GalenFramework;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;

public class GalenJavaTest {
	private WebDriver driver;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\surpraka\\eclipse-workspace\\practiceAutomation\\Resource\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.swtestacademy.com/");
	}

	@Test
	public void validateHomePageLayout() throws IOException {
		LayoutReport layoutReport = Galen.checkLayout(driver, "Resources/example.gspec", Arrays.asList("desktop"));
		List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();
		
		GalenTestInfo test = GalenTestInfo.fromString("homepage layout");
		test.getReport().layout(layoutReport, "check homepage layout");
		tests.add(test);
		
		HtmlReportBuilder htmlReportBuilder = new HtmlReportBuilder();
		htmlReportBuilder.build(tests, "Reports");
		if (layoutReport.errors() > 0) {
			Assert.fail("Layout test failed");
		}
	}

	@AfterMethod
	public void quit() {
		driver.quit();
	}

}
