package com.NN_project1.juiceShop;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.XLUtility;

public class TC_createNewCustomer extends BaseClass {
	String ranEmail;

	
//	@Test(dataProvider= "RegisterData")
	public void createUser(String em, String pwd, String cpwd, String secAns) throws Exception {
		
		WebElement dismissEle=driver.findElement(By.xpath("//span[contains(text(), 'Dismiss')]"));
		dismissEle.click();
		
//		ranEmail= randomEmail();
		
		WebElement email=driver.findElement(By.xpath("//input[@id='emailControl']"));
		email.sendKeys(em);
		
		WebElement password=driver.findElement(By.xpath("//input[@id='passwordControl']"));
		password.sendKeys(pwd);
		
		WebElement rePassword=driver.findElement(By.xpath("//input[@id='repeatPasswordControl']"));
		rePassword.sendKeys(cpwd);
		Thread.sleep(3000);
		
		WebElement secQuesDD=driver.findElement(By.xpath("//*[@id='registration-form']/div//div//div[1]"));
		secQuesDD.click();
		Thread.sleep(3000);
		WebElement option1=driver.findElement(By.xpath("//*[@id='mat-option-0']/span"));
		option1.click();
		Thread.sleep(3000);
		
		WebElement secAn=driver.findElement(By.xpath("//div[@class='security-container']/mat-form-field[2]/div"));
		secAn.click();
		Thread.sleep(3000);
		WebElement secInput=driver.findElement(By.xpath("//div[@class='security-container']/mat-form-field[2]/div//input"));
		secInput.sendKeys(secAns);
		
		WebElement register=driver.findElement(By.xpath("//*[@id=\"registerButton\"]/span[1]"));
		register.click();
		
	}
	
	
	@Test(priority=1)
	public void addToBasketAndVerify() throws Exception {
		WebElement dismissEle=driver.findElement(By.xpath("//span[contains(text(), 'Dismiss')]"));
		dismissEle.click();
	
		WebElement accountEle=driver.findElement(By.xpath("//span[contains(text(),'Account')]"));
		accountEle.click();
		Thread.sleep(2000);		
		WebElement loginEle=driver.findElement(By.xpath("//div[@class='mat-menu-content ng-tns-c255-2']//span"));
		loginEle.click();
		Thread.sleep(2000);		
		WebElement emailEle=driver.findElement(By.xpath("	//input[@id='email']"));
		emailEle.sendKeys("abc@gmail.com");
		Thread.sleep(2000);
		WebElement passEle=driver.findElement(By.xpath("	//input[@id='password']"));
		passEle.sendKeys("abc124//");
		Thread.sleep(2000);
		WebElement login2Ele=driver.findElement(By.xpath("//button[@id='loginButton']"));
		login2Ele.click();
		
		List<WebElement>basketList=driver.findElements(By.xpath("//mat-grid-list[@class='mat-grid-list']//mat-grid-tile"));
		
		for (int j=1; j<basketList.size(); j++) {
		WebElement ele= driver.findElement(By.xpath("//mat-grid-list[@class='mat-grid-list']//mat-grid-tile["+j+"]//button"));
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
		}
		
		WebElement yourBasketEle=driver.findElement(By.xpath("//span[contains(text(),'Your Basket')]"));
		yourBasketEle.click();
		
		List<WebElement>addedItems=driver.findElements(By.xpath("//mat-table[@class='mat-table cdk-table']/mat-row/mat-cell[3]//button[2]"));
		
		for (int i=1; i<addedItems.size(); i++) {
				if(yourBasketEle!=null) {
					WebElement item= driver.findElement(By.xpath("//mat-table[@class='mat-table cdk-table']/mat-row["+ i+"]/mat-cell[3]//button[2]"));
					Thread.sleep(2000);
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", item);
			}
				}
			
			

		
		
				
	}
	
	

	
	@DataProvider(name = "RegisterData")
	public String[][] getData() throws IOException {
		String path = "src\\test\\java\\testData\\Registration.xlsx";

		XLUtility xlutil = new XLUtility(path);
		int totalrows = xlutil.getRowCount("Registration");
		int totalcols = xlutil.getCellCount("Registration", 1);

		String registerData[][] = new String[totalrows][totalcols];

		for (int i = 1; i <= totalrows; i++) {
			for (int j = 0; j < totalcols; j++) {
				registerData[i - 1][j] = xlutil.getCellData("Registration", i, j);
			}
		}

		return registerData;

	}
}
