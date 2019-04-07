/**
 * 
 */
package com.zoopla.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zoopla.qa.base.TestBase;

/**
 * @author swapnil
 *
 */
public class PropertyDetailsPage extends TestBase
{
	public PropertyDetailsPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id='dp-sticky-element']//child::p[@class='ui-pricing__main-price ui-text-t4']")
	WebElement price;
	
	@FindBy(xpath = "//div[@id='dp-sticky-element']//child::img")
	WebElement agencyLogo;
	
	@FindBy(xpath = "//div[@id='dp-sticky-element']//child::div[@class='ui-agent__text']//child::h4")
	WebElement agencyName;
	
	@FindBy(xpath = "//div[@id='dp-sticky-element']//child::p[@class='ui-agent__tel ui-agent__text']//child::a")
	WebElement agencyTelNo;
	
	
	
	
	public AgentDetailsPage gotoAgentDetailsPage()
	{
		waitForElement(agencyName);
		clickOnElement(agencyName);
		return new AgentDetailsPage();
		
	}
	
	
	public String getPrice()
	{
		waitForElement(price);
		String propPrice = price.getText().trim();
		return propPrice;
	}
	
	
	public boolean isLogoDisplay()
	{
		waitForElement(agencyLogo);
		boolean flag = agencyLogo.isDisplayed();
		return flag;
	}
	
	public String getAgencyName()
	{
		waitForElement(agencyName);
		String agName = agencyName.getText().trim();
		return agName;
	}
	
	public String getAgencyTelNo()
	{
		waitForElement(agencyTelNo);
		String telPhoneNo = agencyTelNo.getText().trim();
		
		String tel_PhoneNo = telPhoneNo.substring(6, 18);;
		
		return tel_PhoneNo;
	}
}
