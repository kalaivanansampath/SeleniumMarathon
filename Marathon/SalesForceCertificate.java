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

public class SalesForceCertificate {

	/*
	 * Testcase-2: Architect Certifications ========================== 1. Launch
	 * Salesforce application https://login.salesforce.com/ 2. Login with Provided
	 * Credentials 3. Click on Learn More link in Mobile Publisher 4. Click On
	 * Learning 5. And mouse hover on Learning On Trailhead 6. Clilck on Salesforce
	 * Certifications 7. Choose Your Role as Salesforce Architect 8. Get the
	 * Text(Summary) of Salesforce Architect 9. Get the List of Salesforce Architect
	 * Certifications Available 10.Click on Application Architect 11.Get the List of
	 * Certifications available 12.Take a snapshot of Ceritificate
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

		WebElement ele3 = sh.findElementByXPath("//h4/a[text()='Salesforce Certification']");

		// ele3.click();

		driver.executeScript("arguments[0].click();", ele3);

		driver.findElement(By.xpath("//div[text()='Architect']")).click();

		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());

		WebElement println = driver
				.findElement(By.xpath("//div[@class='cert-site_text slds-text-align--center Lh(1.5) Fz(16px) "
						+ "slds-container--center slds-p-bottom--large']"));

		System.out.println(println.getText());

		// Get the list of names of the bags and print it
		List<WebElement> Listofcertificate = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));

		System.out.println(" Size :" + Listofcertificate.size());

		for (WebElement webElement : Listofcertificate) {
			String text = webElement.getText();
			System.out.println(text);
		}

		WebElement ele5 = driver.findElement(By.xpath("//a[text()='Technical Architect']"));

		action.scrollToElement(ele5).perform();

		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/snap.jpg");
		FileUtils.copyFile(source, dest);

	}

}