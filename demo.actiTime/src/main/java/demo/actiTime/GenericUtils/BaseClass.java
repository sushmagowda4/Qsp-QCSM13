package demo.actiTime.GenericUtils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import demo.actiTime.POM.HomePage;
import demo.actiTime.POM.LoginPage;

public class BaseClass {
	WebDriver driver=null;
	PropertyUtils pUtils=new PropertyUtils();


	@BeforeSuite
	public void configBS() {
		//database connection,extent report,loggers
	}
	@BeforeTest
	public void configBT() {
		//parallel testing codes
	}
	@BeforeClass
	public void configBC() {
		//open the browser
		String browserName = pUtils.getPropertyData(IPathConstant.PROFILE_PATH, "browser");
		String url = pUtils.getPropertyData(IPathConstant.PROFILE_PATH, "url");
		if(browserName.equals("chrome")) {
			System.setProperty(IPathConstant.CHROME_KEY,IPathConstant.CHROME_PATH);
			driver=new ChromeDriver();
		}else if(browserName.equals("firefox")) {
			System.setProperty(IPathConstant.FIREFOX_KEY,IPathConstant.FIREFOX_PATH);
			driver=new FirefoxDriver();
		}else {
			throw new SessionNotCreatedException("browser is not supported");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(IPathConstant.IMP_TIME, TimeUnit.SECONDS);
		driver.get(url);
	}
	@BeforeMethod
	public void configBM() {
		LoginPage loginPage = new LoginPage(driver);
		String username = pUtils.getPropertyData(IPathConstant.PROFILE_PATH,"username");
		String password = pUtils.getPropertyData(IPathConstant.PROFILE_PATH, "password");
		System.out.println(username);
		System.out.println(password);

		loginPage.login(username, password);
	}

	@AfterMethod
	public void configAM() {
		HomePage homePage = new HomePage(driver);
		homePage.logout();
	}
	@AfterClass
	public void configAC() {
		//close the browser
		driver.quit();
	}
	@AfterTest
	public void configAT() {
		//close all the parallel connection
	}
	@AfterSuite
	public void configAS() {
		//close database connection

	}

}
