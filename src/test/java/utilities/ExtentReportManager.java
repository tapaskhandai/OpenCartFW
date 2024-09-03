package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testcases.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;

	public void onStart(ITestContext testContext) {

		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report" + timestamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // specify location of the spark reporter

		sparkReporter.config().setDocumentTitle("OpenCart Automation report");
		sparkReporter.config().setReportName("Open Cart Tests");
		sparkReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Env", "QA");
		extent.setSystemInfo("Author", System.getProperty("user.name"));

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		test.log(Status.PASS, result.getName() + " got successfully executed");

	}

	public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report

		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imgPath = new BaseClass().captureScreenshot(result.getName()); //here an object of BaseClass will be again created.(2 objects gets created,to avoid that we need to make driver as static) 
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());

	}

	public void onFinish(ITestContext testContext) {

		extent.flush();
		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		File extentreport = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentreport.toURI());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
