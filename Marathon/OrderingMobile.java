package com.Leaf.Marathon;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.sukgu.Shadow;

public class OrderingMobile {
	/*
	 * Ordering mobile ============ Instance URL: https://dev57553.service-now.com/
	 * Username: admin Password: 5$dJ*EFdhhW0
	 * 
	 * 1. Launch ServiceNow application 2. Login with valid credentials username as
	 * admin and password as 5$dJ*EFdhhW0 3. Click-All Enter Service catalog in
	 * filter navigator and press enter or Click the ServiceCatalog 4. Click on
	 * mobiles 5. Select Apple iphone6s 6. Update color field to gold and storage
	 * field to 128GB 7. Select Order now option 8. Verify order is placed and copy
	 * the request number" 9. Take a snapshot of Order Status
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();

		driver.get("https://dev57553.service-now.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("5$dJ*EFdhhW0");
		driver.findElement(By.xpath("//button[text()='Log in']")).click();

		Shadow sh = new Shadow(driver);

		sh.setImplicitWait(10);

		sh.findElementByXPath("//div[@id='all']").click();

		sh.setImplicitWait(20);

		sh.findElementByXPath("//span[text()='Service Catalog']").click();

		WebElement frameEle = sh.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frameEle);

		driver.findElement(By.xpath("//a[text()='Mobiles']")).click();

		driver.findElement(By.xpath("//a[@id='item_link_bd6fa75a4f334200086eeed18110c79e']")).click();

		WebElement SourceDrop = driver.findElement(By.xpath("//select[@class='form-control cat_item_option ']"));

		Select Drop1 = new Select(SourceDrop);

		Drop1.selectByVisibleText("Gold");

		WebElement SourceDrop1 = driver.findElement(By.xpath("(//select[@class='form-control cat_item_option '])[2]"));

		// By.xpath("(//span[text()='Show'])[2]"))

		Select Drop2 = new Select(SourceDrop1);

		Drop2.selectByVisibleText("128");

		driver.findElement(By.xpath("//button[@id='oi_order_now_button']")).click();

		WebElement findElement1 = driver
				.findElement(By.xpath("//span[text()='Thank you, your request has been submitted']"));
		System.out.println(findElement1.getText());

		WebElement findElement2 = driver.findElement(By.xpath("//a[@id='requesturl']"));

		System.out.println(findElement2.getText());

		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/snap.jpg");
		FileUtils.copyFile(source, dest);

	}

}