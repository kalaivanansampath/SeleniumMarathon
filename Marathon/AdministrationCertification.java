package com.Leaf.Marathon;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.sukgu.Shadow;

public class AdministrationCertification {

	/*
	 * Testcase-1: Administrator Certifications ============================== 1.
	 * Launch Salesforce application https://login.salesforce.com/ 2. Login with
	 * username as "hari.radhakrishnan@qeagle.com" and password as "Testleaf$321" 3.
	 * Click on Learn More link in Mobile Publisher 4. Click confirm on Confirm
	 * redirect 5. Click Learning 6. And mouse hover on Learning On Trailhead 7.
	 * Clilck on Salesforce Certifications 8. Click on Ceritification Administrator
	 * 9. verify - Administrator Overview window 10.Print the Certifications
	 * available for Administrator Certifications (List) 11.Take a snapshot of
	 * Ceritificate
	 * 
	 */

	public static void main(String[] args) throws IOException {

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();

		driver.get("https://login.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		WebElement Userlo = driver.findElement(By.id("username"));
		Userlo.sendKeys("hari.radhakrishnan@qeagle.com");
		WebElement UserPs = driver.findElement(By.id("password"));
		UserPs.sendKeys("Testleaf$321");
		WebElement Logo = driver.findElement(By.id("Login"));
		Logo.click();

		driver.findElement(By.xpath("//span[text()='Learn More']")).click();

		Set<String> windowHandles = driver.getWindowHandles();

		List<String> listofhandles = new ArrayList<String>(windowHandles);

		driver.switchTo().window(listofhandles.get(1));

		driver.findElement(By.xpath("//button[text()='Confirm']")).click();

		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());

		Shadow sh = new Shadow(driver);

		sh.findElementByXPath("//span[text()='Learning']").click();

		// Creating object of an Actions class
		Actions action = new Actions(driver);

		WebElement ele = sh.findElementByXPath("//span[text()='Learning on Trailhead']");

		// Performing the mouse hover action on the target element.

		action.moveToElement(ele).perform();

		sh.setImplicitWait(10);

		WebElement ele2 = sh.findElementByXPath("//h4/a[text()='Career Paths']");

		action.scrollToElement(ele2).perform();

		sh.setImplicitWait(10);

		WebElement ele3 = sh.findElementByXPath("//h4/a[text()='Salesforce Certification']");

		// ele3.click();

		driver.executeScript("arguments[0].click();", ele3);

		driver.findElement(By.xpath("//a[text()='Administrator']")).click();

		WebElement ele5 = driver.findElement(By.xpath("//a[text()='Business Administration Specialist']"));

		action.scrollToElement(ele5).perform();

		System.out.println(driver.getCurrentUrl());

		// Get the list of names of the bags and print it
		List<WebElement> Listofcertificate = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));

		System.out.println(" Size :" + Listofcertificate.size());

		for (WebElement webElement : Listofcertificate) {
			String text = webElement.getText();
			System.out.println(text);
		}

		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/snap.jpg");
		FileUtils.copyFile(source, dest);

	}

}
