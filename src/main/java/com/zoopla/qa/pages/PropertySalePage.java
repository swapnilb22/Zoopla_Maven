/**
 * 
 */
package com.zoopla.qa.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.zoopla.qa.base.TestBase;

/**
 * @author swapnil
 *
 */
public class PropertySalePage extends TestBase
{
	public PropertySalePage()
	{
		PageFactory.initElements(driver, this);
	}	


	List <Integer> a_PropPrice =  new ArrayList<Integer>(); 


	public void getPropertyPriceList()
	{
		try
		{
			List<WebElement> propertyPriceList = driver.findElements(By.xpath("//ul[@data-role='listview']//child::a[@class = 'listing-results-price text-price']"));
			for(WebElement propertyPrice: propertyPriceList)
			{
				String s_Price = propertyPrice.getText().trim();

				String [] a_Price = s_Price.split("£");
				String actPrice = a_Price[1].trim();

				String [] a_SPrice = actPrice.split(" ");
				String finalPrice = a_SPrice[0];
				String propPrice = finalPrice.replaceAll(",", "");

				int i_Price = 0;
				try 
				{
					i_Price = Integer.parseInt(propPrice);
				}
				catch (NumberFormatException e)
				{
					e.printStackTrace();
				}

				a_PropPrice.add(i_Price);

			}
		}catch (NoSuchElementException e)
		{
			e.printStackTrace();
		}



		Object[] a_Price = new Object [25];
		a_Price = a_PropPrice.toArray();



		for(int i=0; i<25; i++)
		{
			for(int j =i+1; j<25; j++)
			{
				if(((Integer) a_Price[i]) <= ((Integer) a_Price[j]))
				{
					int temp = (Integer) a_Price[i];
					a_Price[i] = a_Price[j];
					a_Price[j]  = temp;
				}
			}


		}
		
		System.out.println("**************************************************"
								+ "****************************");
		
		System.out.println("PROPERTY PRICES IN DESCENDING ORDER");
		for(int i=0; i<25; i++)
		{
			System.out.println(a_Price[i]);
		}

		System.out.println("**************************************************"
				+ "****************************");

	}


	public String selectProperty()
	{
		String s_Price1 ="";
		byte count =0;
		
		try
		{
			List<WebElement> propertyList = driver.findElements(By.xpath("//ul[@data-role='listview']//child::a[@class = 'listing-results-price text-price']"));
			for(WebElement property: propertyList)
			{
				
				count++;
				if(count==5)
				{
					String s_Price = property.getText().trim();
					String [] act_Price = s_Price.split(" ");
					s_Price1 = act_Price[0];
					clickOnElement(property);
					break;
				}

			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return s_Price1;

	}

}
