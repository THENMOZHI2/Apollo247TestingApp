package com.objectRepository;

import org.openqa.selenium.By;

public class Locators {
		
		// login button
		public static By login = By.xpath("//span[text()='Login']");
		
		// Mobile Number entry
		public static By mobileNumberEntry = By.xpath("//input[@name='mobileNumber']");
		
		// continue button
		public static By continueButton = By.xpath("//button[contains(text(),'Continue')]");

		// OTP entry
		public static By otpentry = By.xpath("//input[@title='Please enter the otp']");
		
		// verify button
		public static By verifybutton = By.xpath("//button[contains(text(), 'Verify')]");
		
		//validate login
		public static By validatelogin = By.xpath("//*[@id=\"loginPopup\"]/img");
		
		//mobile field
		public static By mobilefield = By.xpath("//*[@id=\"fixedHeaderCT\"]/div/div[1]/div[2]/ul/li/div/div/div[2]/div[2]/div[2]/div/p[2]");
		
		// invalid message
		public static By invalidmobileerrormsg = By.xpath("//div[text()='This seems like a wrong number']");
		
		// close button
		public static By closebtn = By.xpath("//span[@class='Rb']");
		// Login/Popup overlays
		public static final By modalOverlay = By.cssSelector("div.ProfileNew_modalBackground__tCWPu");

		// Buy Medicines page validation
	    public static By buyMedicinesTab = By.linkText("Buy Medicines");
	    public static By buyMedicinesTitle = By.xpath("//h1[@class='buyMedicineTitle' and text()='Buy Medicines and Essentials']");

	    // Search box
	    public static By searchBox = By.xpath("//div[@data-placeholder='Search Medicines']");
	    public static By searchInput = By.id("searchProduct");
 // after clicking search box actual input opens

	    // Filters
	    public static final By FILTER_CHIPS = By.xpath("//div[contains(@class,'FilterSearchMedicine_chipsUI')]");

	    // Product validation
	    public static By productSearchResult = By.xpath("//h3[contains(@class,'ProductSortSearch_title__aAuhf') and contains(text(),'Paracip-650 Tablet 10')]");

	    // Add button & Quantity increment
	    public static By addProductButton = By.xpath("(//span[text()='Add'])[1]");
	    public static By increaseProductQuantity = By.xpath("(//span[@role='button' and @aria-label='Increase button'])[1]");

	    // View cart button
	    public static By viewCartButton = By.xpath("//button[@aria-label='Button']//span[text()='View Cart']");

  


}


