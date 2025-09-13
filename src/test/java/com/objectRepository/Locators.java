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
		public static By apolloLogo = By.xpath("//*[contains(@title,'Online Doctor Consultation & Medicines')]");

		
		//mobile field
		public static By mobilefield = By.xpath("//*[@id=\"fixedHeaderCT\"]/div/div[1]/div[2]/ul/li/div/div/div[2]/div[2]/div[2]/div/p[2]");
		
		// invalid message
		// Invalid Mobile Error
		public static By invalidMobileErrorMsg = By.xpath("//div[contains(@class,'SignIn_helperText') and contains(@class,'SignIn_errorText')]");

		// Invalid OTP Error
		public static By invalidOtpErrorMsg = By.xpath("//div[contains(@class,'SignIn_helpText') and contains(@class,'SignIn_errorText')]");
		public static By resendbtn=By.xpath("//button[@title='Request resend otp' and .//span[text()='Resend OTP']]");

		// close button
		public static By closebtn = By.xpath("//span[@class='Rb']");
		// Login/Popup overlays
		public static final By modalOverlay = By.cssSelector("div.ProfileNew_modalBackground__tCWPu");

		// Buy Medicines page validation
	    public static By buyMedicinesTab = By.xpath("//a[contains(text(),'Buy Medicines')]");
	    public static By buyMedicinesTitle = By.xpath("//h1[contains(@class,'buyMedicineTitle') and contains(normalize-space(),'Buy Medicines')]");


	    // Search box
	    public static By searchBox = By.xpath("//div[@data-placeholder='Search Medicines']");
	    public static By searchInput = By.id("searchProduct");
 // after clicking search box actual input opens

	    // Filters
	    public static final By FILTER_CHIPS = By.xpath("//div[contains(@class,'FilterSearchMedicine_chipsUI')]");

	    // Product validation
	    public static By productSearchResult = By.xpath("//h3[contains(@class,'ProductSortSearch_title__aAuhf') and contains(text(),'Paracip-650 Tablet')]");

	    // Add button & Quantity increment
	    public static By addProductButton = By.xpath("(//span[text()='Add'])[1]");
	    public static By increaseProductQuantity = By.xpath("(//span[@role='button' and @aria-label='Increase button'])[1]");

	    // View cart button
	    //public static By viewCartButton = By.xpath("//button[@aria-label='Button']//span[text()='View Cart']");
	    public static By myCartPageTitle = By.xpath("//li[contains(@class,'CartFlowBreadcrumbs_active') and normalize-space()='MY CART']");


	 // 1. Change button
	    public static By changeAddressBtn = By.xpath("//span[contains(@class,'CartAddress_actionBtn__') and text()='Change']");

	    // 2. Deliver to validation (header)
	    public static By deliverToHeader = By.xpath("//h2[normalize-space()='Deliver to']");

	    // 3. Add New Address button
	    public static By addNewAddressBtn = By.xpath("//button[contains(@class,'AddNewAddressRevamped_locationBtn__')]//span[text()='Add New Address']");
        
	    // 4. Deliver to tab validation again
	    public static By deliverToTab = By.xpath("//h2[normalize-space()='Deliver to']");


	    // 5. Pincode search box
	    public static By pincodeSearchBox = By.xpath("//input[@placeholder='Search for society, locality, pincode...']");

	    // 6. Select pincode (dynamic – here selecting “56789A”)
	    public static By selectPincode = By.xpath("//div[contains(@class,'NewSearchLocationSuggestor_searchItemList')]//h3[text()='56789A']");

	    // 7. Address details tab validation
	    // static By addressDetailsHeader = By.xpath("//div[contains(@class,'NewAddressForm_tab') and normalize-space(text())='Address Details']");
	 // In your Locators class
	    public static By addressDetailsHeader = By.xpath("//h2[text()='Enter Address Details']");
	    


	    // 8. House number text area
	    public static By houseNumberField = By.xpath("//textarea[@name='address1' and @placeholder='Type here']");

	    // 9. Save & Next button
	 // Locator for Save & Next button
	    public static By saveAndNextBtn = By.xpath("//button[.//span[normalize-space(text())='Save & Next']]");


	 // Recipient details section
	    public static final By someoneElseRadio = By.xpath("//input[@type='radio' and @name='orderingFor']");


	    // Home button (custom button, not radio)
	    public static final By homeButton =
	            By.cssSelector("button#HOME[name='addressType']");

	    // Recipient Name field
	    public static final By recipientNameField =
	            By.cssSelector("input[name='recipientName'][placeholder='Type Here']");

	    // Save Address button
	    public static final By saveAddressBtn =
	            By.xpath("//button[span[normalize-space(text())='Save Address']]");

	 // Proceed buttons (after address or prescription steps)
	 // Proceed and Skip Savings buttons
	    public static By firstProceedBtn = By.xpath("//button[@aria-label='Button']//span[text()='PROCEED']");
	    public static By secondProceedBtn = By.xpath("//button[@aria-label='Button']//span[text()='Proceed']");
	    public static By skipSavingsBtn =By.xpath("//button[.//span[normalize-space()='Skip Savings']]");
	    public static By ThirdProceedBtn = By.xpath("//button[.//span[text()='PROCEED']]");

	    // 2. Upload Prescription tab validation
	    public static final By uploadPrescriptionHeader = By.xpath("//h2[contains(@class,'CartPrescriptionReviewMain_pageTitle__') and text()='Upload Prescription']");

	    // 3. Continue without Prescription (selected by default)
	    public static By continueWithoutPrescriptionSelected = By.xpath("//header[contains(@class,'WithoutPrescription_selected__')]");

	    // 4. Add Patient button
	    public static By addPatientBtn =By.xpath("//button//span[contains(.,'Add Patient Name')]");

	    // 5. Add New Family Member form title
	    public static By addNewFamilyMemberHeader = By.xpath("//h2[contains(text(),'Add New Family Member')]");

	    // 6. First name input
	    public static By firstNameField = By.xpath("//input[@placeholder='First name']");

	    // 7. Last name input
	    public static By lastNameField = By.xpath("//input[@placeholder='Last name']");

	    // 8. Date of birth inputs
	    public static By dobDayField = By.xpath("//input[@placeholder='dd' and @maxlength='2']");
	    public static By dobMonthField = By.xpath("//input[@placeholder='mm' and @maxlength='2']");
	    public static By dobYearField = By.xpath("//input[@placeholder='yyyy' and @maxlength='4']");

	    // 9. Gender button (Female here, can parameterize later)
	    public static By genderFemaleBtn = By.xpath("//button[span[text()='Female']]");

	    // 10. Relation dropdown button
	    public static By relationDropdown = By.xpath("//button[contains(@id,'headlessui-listbox-button')]");

	    // 11. Relation option - Daughter
	    public static By relationDaughterOption = By.xpath("//li[.//span[text()='DAUGHTER']]");

	    // 12. Save button
	    public static By saveBtn = By.xpath("//button[span[normalize-space(text())='Save']]");

	    // 13. Confirm button
	    public static By confirmBtn = By.xpath("//button[span[normalize-space(text())='CONFIRM']]");

	    // 14. Proceed button (first one)
	    public static By proceedBtn1 = By.xpath("(//button[normalize-space(span)='Proceed'])[1]");

	    // 15. Proceed button (second one – has extra class 'L__ bB')
	    public static By proceedBtn2 = By.xpath("//button[span[normalize-space(text())='Proceed']]");

	    // 16. Payments tab title validation
	    public static By paymentsTab = By.xpath("//div[text()='Payments']");

		public static By suggestedArea;



  


}


