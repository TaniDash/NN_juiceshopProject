package com.NN_project1.juiceShop;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {

	static WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
	System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().deleteAllCookies();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get(" https://juice-shop.herokuapp.com/#/register");

	}
	
	//@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	public int generateRandom() {
		Random rn=  new Random();
		int num= rn.nextInt(99);
		return num;
	}
	
	  public static String randomEmail() {
	        return "random-" + UUID.randomUUID().toString() + "@example.com";
	    }
	
}
