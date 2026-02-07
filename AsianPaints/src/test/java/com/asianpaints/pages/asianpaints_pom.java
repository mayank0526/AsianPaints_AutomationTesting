package com.asianpaints.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.asianpaints.base.BaseTest;

public class asianpaints_pom {
	
	WebDriver driver;
	WebDriverWait wait;
	
	 By productmenu = By.xpath("//*[@id=\"box-1\"]/a"); // 
	 By colour = By.xpath("//*[@id=\"box-1\"]/ul/li[1]/ul/li[2]/a");
	 By popup = By.xpath("/html/body/div[4]/div/div[2]/div/div/div/div/button");
	 By select = By.xpath("//*[@id=\"colorCatalogueListForm-apRvm\"]/li[1]/ul/li[1]/div/div[1]");
	 By shopnow = By.xpath("//*[@id=\"js-shade-palette-append\"]/div/div[1]/div[2]/div/a");
	 By addcart = By.xpath("//*[@id=\"add-to-cart-click\"]");
	 By removecart = By.xpath("//*[@id=\"CartLogo\"]");
	 By removed = By.xpath("//*[@id=\"cart-quantity-minus0\"]");
	 By cook = By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]");
	 
//	    By popup1 = By.xpath("/html/body/div[4]/div/div[2]/div/div/div/div/button/span");
//		By productmenu1 = By.xpath("//*[@id=\"box-2\"]/a");
//		By texture1 = By.xpath("//*[@id=\"box-2\"]/ul/li[1]/ul/li[2]/a");
//		By viewtexture1 = By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div/div[1]/div/div[2]/div/a");
//		By textureslection1 = By.xpath("//*[@id=\"collectionListing\"]/section/div/div/div[2]/ul/li[1]/a/div/picture/img");
//		By addcart1 = By.xpath("//*[@id=\"add-to-cart-click\"]");
//		By viewcart1 = By.xpath("//*[@id=\"CartLogo\"]/picture/img");
//		By removecart1 = By.xpath("//*[@id=\"CartLogo\"]/div/div[2]/ul/li/div/div[1]/span");
//		By startshopping1 = By.xpath("//*[@id=\"CartLogo\"]/div/div/a");
		  
		 public asianpaints_pom(WebDriver driver) {
			 this.driver= driver;	 
			 this.wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		 }
	 

//		
	 
		
	 public void removecookie() {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(cook));
		 wait.until(ExpectedConditions.elementToBeClickable(cook)).click();
	 }
	 
	 public void openmenu() {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(productmenu));
		 wait.until(ExpectedConditions.elementToBeClickable(productmenu)).click();
	 }
	 
	 public void crosspop() {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(popup));
		 wait.until(ExpectedConditions.elementToBeClickable(popup)).click();
	 }
	 public void selectcolour() {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(colour));
		 wait.until(ExpectedConditions.elementToBeClickable(colour)).click();
	 }
	 public void chooseshade() {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(select));
		 wait.until(ExpectedConditions.elementToBeClickable(select)).click();
	 }
	 
	 public void shop() {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(shopnow));
		 wait.until(ExpectedConditions.elementToBeClickable(shopnow)).click();
	 }

	 public void cart() {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(addcart));
		 wait.until(ExpectedConditions.elementToBeClickable(addcart)).click();
	 }
	 public void remove() {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(removecart));
		 wait.until(ExpectedConditions.elementToBeClickable(removecart)).click();
	 }

	 public void clearcart() {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(removed));
		 wait.until(ExpectedConditions.elementToBeClickable(removed)).click();
	 }

	//////////////////////DAKSH//////////////////////////////////////////////////
	 By logo = By.cssSelector("a[aria-label*='Asian Paints'], img[alt*='Asian Paints']");
	 
	    public void check_title_and_logo(String expectedTitleContains) {
	 
	        String title = driver.getTitle();
	        System.out.println("Page Title: " + title);
	 
	        if (!title.toLowerCase().contains(expectedTitleContains.toLowerCase())) {
	            throw new AssertionError("❌ Title not matching. Actual: " + title);
	        }
	 
	        if (!driver.findElement(logo).isDisplayed()) {
	            throw new AssertionError("❌ Logo not visible");
	        }

}
}
