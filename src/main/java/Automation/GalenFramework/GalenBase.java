package Automation.GalenFramework;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;

public class GalenBase {
	private static final String URL = "https://www.mfs.com/en-us/";
	public static TestDevice t = null;
	public WebDriver driver;

	@BeforeMethod
	@Parameters(value = "devices")
	public WebDriver setUp(String device) {
		if (device.equals("desktop"))
			t = new TestDevice("desktop", new Dimension(1200, 800), asList("desktop"));
		else if (device.equals("mobile"))
			t = new TestDevice("mobile", new Dimension(450, 800), asList("mobile"));

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\surpraka\\eclipse-workspace\\practiceAutomation\\Resource\\chromedriver.exe");
		driver = new ChromeDriver();
		if (t != null) {
			if (t.getScreenSize() != null) {
				driver.manage().window().setSize(t.getScreenSize());
			}
		} else
			System.out.println("Test Device Object is Not Created");
		return driver;

	}

	public WebDriver getDriver() {
		return driver;
	}

	public TestDevice getDeviceInfo() {
		return t;
	}

	public void load(String uri) {
		getDriver().get(URL + uri);
	}

	public void scroll() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static class TestDevice {
		private final String name;
		private final Dimension screenSize;
		private final List<String> tags;

		public TestDevice(String name, Dimension screenSize, List<String> tags) {
			this.name = name;
			this.screenSize = screenSize;
			this.tags = tags;
		}

		public String getName() {
			return name;
		}

		public Dimension getScreenSize() {
			return screenSize;
		}

		public List<String> getTags() {
			return tags;
		}

		@Override
		public String toString() {
			return String.format("%s %dx%d", name, screenSize.width, screenSize.height);
		}
	}

	public void reporting(LayoutReport layoutReport, String reportName, String reportDevice) {

		List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();

		GalenTestInfo test = GalenTestInfo.fromString(reportName + "_" + reportDevice);

		test.getReport().layout(layoutReport, "check layout on " + reportDevice + " device");

		tests.add(test);

		try {
			new HtmlReportBuilder().build(tests, "Reports/galen-html-reports-3");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
