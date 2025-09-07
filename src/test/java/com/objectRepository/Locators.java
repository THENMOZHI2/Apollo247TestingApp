package com.objectRepository;

import org.openqa.selenium.By;


public class Locators {

	 // 🔹 Login Page Locators
	public static By loginIcon = By.xpath("//span[normalize-space()='Login']");

    public static By mobileNumberInput = By.name("mobileNumber");
    public static By continueBtn = By.xpath("//button[contains(text(),'Continue')]");
    public static By otpInput = By.xpath("//input[@title='Please enter the otp']");
    public static By verifyOtpBtn = By.xpath("//button[contains(text(),'Verify')]");
    
    // 🔹 Home Page Locators
    public static By homeLogo = By.cssSelector("img[alt='Online Doctor Consultation & Medicines']");
    // 🔹 Buy Medicines Page Locators
    public static final By buyMedicineTab = By.xpath("//a[@href='https://www.apollopharmacy.in']");
    public static final By buyMedicineHeader = By.xpath("//h1[contains(text(),'Buy Medicines and Essentials')]");
    public static final By searchBox = By.xpath("//div[@data-placeholder='Search Medicines']");

    // Filters (dynamic safe locators)
    public static final By inStockFilter = By.xpath("//div[normalize-space()='In-stock']");
    public static final By doloBrandFilter = By.xpath("//label[@for='DOLO']");

    // Search result validation (generic, not hardcoded count/product)
    public static final By productResultHeader = By.xpath("//div[contains(@class,'ProductSortSearch_header')]/h3");

    // Product Actions
    public static final By addButton = By.xpath("//button[@aria-label='Add']//span[text()='Add']");
    public static final By increaseButton = By.xpath("//span[@aria-label='Increase button']");
    public static final By viewCartButton = By.xpath("//button//span[normalize-space()='View Cart']");
}

