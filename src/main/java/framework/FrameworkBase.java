package framework;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import util.PropertiesLoader;

public class FrameworkBase {

	final static Logger LOGGER = Logger.getLogger(FrameworkBase.class);

	protected WebDriver driver = null;

	@Before
	public void setup() {
		LOGGER.info("Test starting...");
		startDriver();
	}

	@After
	public void teardown() {

		if (driver != null) {
			LOGGER.info("Closing the driver");
			driver.close();
		} else {
			LOGGER.debug("Could not close driver - was null!");
		}

		LOGGER.info("==========================================");
	}

	private void startDriver() {

		String message = null;

		PropertiesLoader props = new PropertiesLoader();
		String browser = props.getProperty("browser");

		LOGGER.info("Starting driver...");
		
		LOGGER.trace("browser from properties: " + browser);

		switch (browser) {
		case "firefox":
			message = "Firefox driver requested";
			driver = new FirefoxDriver();
			break;

		case "chrome":
			message = "Chrome driver requested";
			driver = new ChromeDriver();
			break;

		default:
			message = "Unknown browser requested " + browser;
			LOGGER.fatal(message);
			throw new RuntimeException(message);
		}

		LOGGER.info(message);
		driver.manage().window().maximize();
	}
}
