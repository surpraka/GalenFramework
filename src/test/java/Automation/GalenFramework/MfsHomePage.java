package Automation.GalenFramework;

import java.io.IOException;

import org.testng.annotations.Test;

import com.galenframework.api.Galen;

public class MfsHomePage extends GalenBase {
	
	@Test(dataProvider = "devices")
	public void valdiateHomePage(TestDevice device) throws IOException {
		load("/mutual-funds.html?tabname=portfolioAnalysis");
	
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[1];
		String methodName = e.getMethodName();

		 reporting(Galen.checkLayout(getDriver(), "Resources/mfs.gspec", device.getTags()), methodName,
				device.toString());
	
	}

}
