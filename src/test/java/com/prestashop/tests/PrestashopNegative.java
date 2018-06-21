package com.prestashop.tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrestashopNegative {
	
	WebDriver driver;
	
	
	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver=new ChromeDriver();
		driver.get(" http://automationpractice.com");
		driver.manage().window().fullscreen();
		//Sign in page
		driver.findElement(By.xpath("//div[@class='header_user_info']")).click();
	}
	
	@Test
	public void wrongCredential() throws InterruptedException {
		
		driver.findElement(By.id("email")).sendKeys("cybertek@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("12345");
		driver.findElement(By.name("SubmitLogin")).click();
		
		String actual = driver.findElement(By.xpath("(//li)[15]")).getText();
		
		//*[@id="center_column"]/div[1]/ol/li
		System.out.println(actual);
		Assert.assertTrue(actual.contains("Authentication failed"));
		Thread.sleep(1000);
	}
	
	@Test
	public void invalidEmail() throws InterruptedException {
		driver.findElement(By.id("email")).sendKeys("cybertek1gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("12345");
		driver.findElement(By.name("SubmitLogin")).click();
		String actual = driver.findElement(By.xpath("(//li)[15]")).getText();
		System.out.println(actual);
		Assert.assertTrue(actual.contains("Invalid email address."));
		Thread.sleep(1000);
	}
	
	@Test
	public void blankEmail() throws InterruptedException {
	driver.findElement(By.id("email")).sendKeys();
	driver.findElement(By.id("passwd")).sendKeys("12345");
	driver.findElement(By.name("SubmitLogin")).click();
	String actual = driver.findElement(By.xpath("(//li)[15]")).getText();
	System.out.println(actual);
	Assert.assertTrue(actual.contains("An email address required."));
	Thread.sleep(1000);
		
	}
	
	@Test
	public void blankPassword() throws InterruptedException {
	driver.findElement(By.id("email")).sendKeys("cybertek@gmail.com");
	driver.findElement(By.id("passwd")).sendKeys();
	driver.findElement(By.name("SubmitLogin")).click();
	String actual = driver.findElement(By.xpath("(//li)[15]")).getText();
	System.out.println(actual);
	Assert.assertTrue(actual.contains("Password is required.")); 
	Thread.sleep(1000);
		
	}
	
	

}
