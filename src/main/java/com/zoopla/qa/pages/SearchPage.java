/**
 * 
 */
package com.zoopla.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zoopla.qa.base.TestBase;

/**
 * @author swapnil
 *
 */

public class SearchPage extends TestBase
{


	public SearchPage()
	{
		PageFactory.initElements(driver, this);
	}


	@FindBy(css="span.search-input-location-placeholder-1")
	WebElement searchField;

	@FindBy(css="#search-submit")
	WebElement searchBtn;


	public PropertySalePage searchProperties()
	{
		waitForElement(searchField);
		try 
		{
			action = new Actions(driver);
			action.moveToElement(searchField);
			action.click();
			action.sendKeys("London");
			action.build().perform();

			//searchField.sendKeys("London");

			clickOnElement(searchBtn);

			

		}catch (Exception e) 
		{
			e.printStackTrace();		
		}
		return new PropertySalePage();
	}


}
