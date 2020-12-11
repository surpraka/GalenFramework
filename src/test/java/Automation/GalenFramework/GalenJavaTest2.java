package Automation.GalenFramework;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.galenframework.api.Galen;

public class GalenJavaTest2 extends GalenBase {

	@Test
	public void welComePage() throws IOException {
		load("/");
		scroll();
		
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[1];
		String methodName = e.getMethodName();

		reporting(Galen.checkLayout(getDriver(), "Resources/mfs.gspec", getDeviceInfo().getTags()), methodName,
				getDeviceInfo().toString());
	}
}
