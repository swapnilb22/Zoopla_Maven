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
public class AgentDetailsPage extends TestBase
{
	public AgentDetailsPage() 
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='content']//child::b[1]")
	WebElement agentName;
	
	@FindBy(xpath = "//div[@id='content']//child::span[@class='agent_phone']//child::a")
	WebElement agentNo;

	
	public String getAgentName()
	{
		waitForElement(agentName);
		String agencyName = agentName.getText().trim();
		return agencyName;
	}

	public String getAgentTelephone()
	{
		waitForElement(agentNo);
		String tele = agentNo.getText().trim();
		String [] ph = tele.split("44");
		String telephone = ph[1].trim();
		return telephone;	
	}


}
