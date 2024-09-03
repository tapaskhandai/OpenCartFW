package testcases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	Random ran = new Random();
	public Logger logger;
	public Properties pro;
	public FileReader file;

	public String generateRandomText() {
		String a = "Text";
		for (int i = 1; i <= 4; i++) {
			int n = ran.nextInt(9);
			a = a + n;
		}
		return a;
	}

	public String randomNumber() {

		return RandomStringUtils.randomNumeric(10);
	}

	public void config_properties() {
		try {
			file = new FileReader(
					"C:\\Users\\tapas\\eclipse-workspace\\OpenCartFW\\src\\test\\resources\\config.properties");
			pro = new Properties();
			pro.load(file);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@BeforeClass(groups = { "sanity", "Regression", "smoke" })
	@Parameters({ "os", "browser" })
	public void setUp(String os, String browser) {

		config_properties();
		logger = LogManager.getLogger(this.getClass());

		if (pro.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities dcap = new DesiredCapabilities();

			// os
			if (os.equalsIgnoreCase("windows")) {
				dcap.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				dcap.setPlatform(Platform.MAC);
			} else if (os.equalsIgnoreCase("linux")) {
				dcap.setPlatform(Platform.LINUX);
			} else {
				logger.info("No matching os");
				return;
			}

			// browser
			switch (browser.toLowerCase()) {

			case "chrome":
				dcap.setBrowserName("chrome");
				break;
			case "edge":
				dcap.setBrowserName("MicrooftEdge");
				break;
			default:
				logger.info("No matching browser");
				return;

			}

			try {
				try {
					driver = new RemoteWebDriver(new URI("http://localhost:4444/wd/hub").toURL(), dcap);
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} // wd/hub

		} else {

			switch (browser.toLowerCase()) {
			case "chrome":
				ChromeOptions chrome_opt = new ChromeOptions();
				chrome_opt.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
				driver = new ChromeDriver(chrome_opt);
				logger.info("Chrome Browser Launched");
				break;

			case "edge":
				EdgeOptions edge_opt = new EdgeOptions();
				edge_opt.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
				driver = new EdgeDriver(edge_opt);
				logger.info("Edge Browser Launched");
				break;

			default:
				System.out.println("Invalid Browser provided");
				return;
			}
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(pro.getProperty("app_url"));
		logger.info("Navigated to url");

	}

	@AfterClass(groups = { "sanity", "Regression", "smoke" })
	public void tearDown() {
		driver.quit();
		logger.info("Browser Closed");
	}

	public String captureScreenshot(String tc_name) {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tc_name + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		srcFile.renameTo(targetFile);

		return targetFilePath;
	}

}
