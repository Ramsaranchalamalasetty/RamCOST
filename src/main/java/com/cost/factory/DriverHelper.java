package com.cost.factory;

import cucumber.api.Scenario;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverHelper extends Pages {
    Properties prop;
    public static WebDriver driver;

    public Properties loadPropertiesFiles(String file) throws IOException {
        prop = new Properties();
        InputStream propertyFile;
        propertyFile = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/config/" + file + ".properties");
        prop.load(propertyFile);
        return prop;
    }

    public void openBrowser(Scenario scenario) throws IOException {
        prop = loadPropertiesFiles("env");
        String browser = prop.getProperty("browser");
        String url = prop.getProperty("url");
        switch (browser) {
            case "chrome":
                ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("no-sandbox");
                System.setProperty("webdriver.chrome.silentOutput", "true");
                System.setProperty("parentLoaderPriority", "false");
                driver = (new ChromeDriver(options));
                break;
            case "ie":
                InternetExplorerDriverManager.getInstance(DriverManagerType.IEXPLORER).setup();
                driver = (new InternetExplorerDriver());
                break;
            case "safari":
                driver = (new SafariDriver());
                break;
            case "Firefox":
               driver= new FirefoxDriver(  );
                break;
        }
        driver.get(url);
    }

    public void closeBrowser() {
        driver.quit();
    }

    public DriverHelper logScenarioStatus(Scenario scenario) {
        if (scenario.isFailed()) {
            log.error((scenario.getSourceTagNames() + scenario.getName() + " ** STATUS **: "
                    + scenario.getStatus().toUpperCase() + System.lineSeparator()));
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshotFile,
                        new File(System.getProperty("user.dir") + "/screenShot/_" + scenario.getName() + ".png"));
                log.info("Took screen shot..." + System.lineSeparator());
            } catch (IOException e) {
                log.error("Problem in taking screen shots" + System.lineSeparator());
            }

        } else {
			log.info(((scenario.getSourceTagNames() + scenario.getName() + " ** STATUS **: "
					+ scenario.getStatus().toUpperCase() + System.lineSeparator())));
		}
        return this;
    }
    public void maximise(){
        driver.manage().window().maximize();
    }
}
