package com.zoopla.qa.testCases;
//first commit
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
// this is dev branch code
// this code is from feature_two
import com.zoopla.qa.base.TestBase;
import com.zoopla.qa.pages.AgentDetailsPage;
import com.zoopla.qa.pages.PropertyDetailsPage;
import com.zoopla.qa.pages.PropertySalePage;
import com.zoopla.qa.pages.SearchPage;

public class TestAgentDetailsofProperty extends TestBase
{
	SearchPage searchPage;
	PropertySalePage propertySalePage;
	PropertyDetailsPage propertyDetailsPage;
	AgentDetailsPage agentDetailsPage;
	
	
	public TestAgentDetailsofProperty()
	{
		super();
	}

	@Test
	public void testAgencyDetails() 
	{
		SoftAssert softAssert = new SoftAssert();
		
		propertySalePage = new PropertySalePage();
		propertySalePage.getPropertyPriceList();
	
		String propertPrice = propertySalePage.selectProperty();
		
		propertyDetailsPage = new PropertyDetailsPage();
		
		softAssert.assertTrue(propertyDetailsPage.isLogoDisplay());
	
		softAssert.assertEquals(propertPrice, propertyDetailsPage.getPrice());
		
		String agencyName = propertyDetailsPage.getAgencyName();
		String agencyTelephone = propertyDetailsPage.getAgencyTelNo();
		
		
		propertyDetailsPage.gotoAgentDetailsPage();
		
		agentDetailsPage = new AgentDetailsPage();
		
		softAssert.assertEquals(agencyName, agentDetailsPage.getAgentName());
		
		softAssert.assertEquals(agencyTelephone, agentDetailsPage.getAgentTelephone());
		
		softAssert.assertAll();
	}
	@BeforeMethod
	public void beforeMethod() 
	{
		initialization();
		searchPage = new SearchPage();
		searchPage.searchProperties();
			
	}

	@AfterMethod
	public void afterMethod() 
	{

	}

}
