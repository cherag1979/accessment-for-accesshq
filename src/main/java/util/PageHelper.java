package util;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.components.HtmlTable;

public class PageHelper extends PageObject{

	public PageHelper (WebDriver driver) {
		super(driver);
	}

	
	public WebElementFacade findElementByClassNameWithMatchingText(String classname, String text) {
		List<WebElementFacade> elementList = findAll(By.className(classname));
		for(WebElementFacade element : elementList) {
			if(element.getText().contentEquals(text)) {
				return element;
			}
		}
		return null;
	}
	
	public WebElementFacade findElementByCSSSelectorWithMatchingText(String css, String text) {
		List<WebElementFacade> elementList = findAll(By.cssSelector(css));
		System.out.println("SIZE >>> " + elementList.size());
		for(WebElementFacade element : elementList) {
			if(element.getText().contentEquals(text)) {
				System.out.println("Element text>>> " + element.getText());
				return element;
			}
		}
		return null;
	}
	
	public WebElementFacade findElementByNameWithMatchingText(String name, String text) {
		List<WebElementFacade> elementList = findAll(By.name(name));
		for(WebElementFacade element : elementList) {
			if(element.getText().contentEquals(text)) {
				return element;
			}
		}
		return null;
	}
	
	public WebElementFacade findElementByNameWithMatchingValue(String name, String value) {
		
		List<WebElementFacade> elementList = findAll(By.name(name));
		System.out.println("SIZE >>> " + elementList.size());
		for(WebElementFacade element : elementList) {
			if(element.getValue().contentEquals(value)) {
				System.out.println("Element text>>> " + element.getText());
				System.out.println("Element value>>> " + element.getValue());
				return element;
			}
		}
		return null;
	}
	
	public WebElementFacade findElementByTagContainingText(String tagName, String text) {
		
		List<WebElementFacade> elementList = findAll(By.tagName(tagName));
		System.out.println("SIZE >>> " + elementList.size());
		for(WebElementFacade element : elementList) {
			if(element.getText().toLowerCase().contains(text.toLowerCase())) {
				System.out.println("Element text>>> " + element.getText());
				System.out.println("Element value>>> " + element.getValue());
				return element;
			}
		}
		return null;
	}
	
	public WebElementFacade findElementByCssAndIndex(String identifier, int index) {
		List<WebElementFacade> elementList = findAll(By.cssSelector(identifier));
		System.out.println("SIZE >>> " + elementList.size());
		return elementList.get(index);
	}
	
	
	public boolean clickAndVerify(WebElementFacade elementToClick, WebElementFacade elementToVerify) {
		for (int i = 0; i < 2; i++) {
			if(isClickSuccessful(elementToClick,elementToVerify)) {
				return true;
			}
		}
		Assert.fail(">>>COULD NOT FIND THE TARGET ELEMENT AFTER 2 ATTEMPTS");
		return false;
	}
	
	public boolean isClickSuccessful(WebElementFacade elementToClick, WebElementFacade elementToVerify) {
		try {
			elementToClick.click();
			System.out.println(">>>SOURCE ELEMENT WAS CLICKED. NOW LOOKING FOR TARGET ELEMENT...");
			return elementToVerify.isVisible();
		}
		catch(Exception e) {
			System.out.println(">>>SOMETHING WENT WRONG. Either source or target element not found");
			return false;
		}
	}
	
	public boolean clickAndVerify(WebElementFacade elementToClick, String elementToVerify) {
		for (int i = 0; i < 2; i++) {
			if(isClickSuccessful(elementToClick,elementToVerify)) {
				return true;
			}
		}
		Assert.fail(">>>COULD NOT FIND THE TARGET ELEMENT AFTER 2 ATTEMPTS");
		return false;
	}
	

	public boolean isClickSuccessful(WebElementFacade elementToClick, String elementToVerify) {
		try {
			elementToClick.click();
			System.out.println(">>>SOURCE ELEMENT WAS CLICKED. NOW LOOKING FOR TARGET ELEMENT...");
			return findButtonElementByText(elementToVerify).isVisible();
		}
		catch(Exception e) {
			System.out.println(">>>SOMETHING WENT WRONG. Either source or target element not found");
			return false;
		}
	}
	
	
	public boolean clickAndVerifyURL(WebElementFacade elementToClick, String urlText, int retryCountForClicks, int retryCountForVerify) {
		for (int i = 0; i < retryCountForClicks; i++) {
			if(containsURLTextAfterClick(elementToClick,urlText,retryCountForVerify)) {
				return true;
			}
		}
		Assert.fail(">>>CURRENT URL DID NOT CONTAIN SPECIFIED TEXT AFTER " + retryCountForClicks + " ATTEMPTS");
		return false;
	}
	

	public boolean containsURLTextAfterClick(WebElementFacade elementToClick, String urlText, int retryCountForVerify) {
		try {
			elementToClick.click();
			System.out.println(">>>SOURCE ELEMENT WAS CLICKED. NOW LOOKING FOR CURRENT URL TEXT >> " + urlText);
			return currentURLContainsText(urlText,retryCountForVerify);
		}
		catch(Exception e) {
			System.out.println(">>>SOMETHING WENT WRONG. Either source element or target URL text not found");
			return false;
		}
	}
	
	private boolean currentURLContainsText(String urlText, int retryCount) {
		for (int i = 0; i < retryCount; i++) {
			if (getDriver().getCurrentUrl().contains(urlText)) {
				return true;
			}
			try {Thread.sleep(1000);} catch (Exception e) {e.printStackTrace();}
		}
		System.out.println("TEXT " + urlText + "NOT FOUND IN URL AFTER TRYING " + retryCount + " TIMES");
		return false;
	}

	public boolean currentURLContainsText(String text) {
		return getDriver().getCurrentUrl().contains(text);
	}
	
	
	public void clickElementByLinkText(String text) {
		getDriver().findElement(By.linkText(text)).click();
	}
	
	
	public WebElementFacade findButtonElementByText(String text) {
		waitFor(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("button")));
		List<WebElementFacade> elementList = findAll(By.tagName("button"));
		System.out.println("SIZE >>> " + elementList.size());
		
		for(WebElementFacade element : elementList) {
			if(element.getText().contentEquals(text)) {
				System.out.println("Element text>>> " + element.getText());
				System.out.println("Element value>>> " + element.getValue());
				return element;
			}
		}
		return null;
	}
	
	
	public void clickElementByButtonText(String text) {
		findButtonElementByText(text).click();
	}
	
	public WebElement findElementByButtonText(String text) {
		return getDriver().findElement(By.buttonText(text));
	}
	
	
	public boolean isElementVisibleByLinkText(String text) {
		try {
			if(find(By.linkText(text)).isVisible()) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(">>>LINK TEXT : '" + text + "' NOT FOUND");
			return false;
		}
	}
	
	public boolean isTextInTableAfterWait(WebElementFacade table, String text, int waitInterval, int retryCount) throws IOException {
		
		HtmlTable htmlTable = new HtmlTable(table);
		for (int i = 0; i < retryCount; i++) {
			System.out.println(htmlTable.getRows());
			if(htmlTable.getRows().toString().contains(text)) {
				return true;
			}
			try {Thread.sleep(waitInterval);} catch (Exception e) {e.printStackTrace();}
//			getDriver().navigate().refresh();
		}
		return false;
	}
	
	public boolean isTextInPaginatedTable(WebElementFacade table, String text) throws IOException {
		
		if(isTextInTableAfterWait(table, text, 1000, 1)) {
			return true;
		}
		else {
			while(findButtonElementByText("Load More") != null) {
				clickElementByButtonText("Load More");
				if(isTextInTableAfterWait(table, text, 1000, 1)) {
					return true;
				}
			}
		}
		return false;

	}
	
	
	public boolean isTextInTable(WebElementFacade table, String text) throws IOException {
		return isTextInTableAfterWait(table, text, 1000, 2);
	}
	
	
	public boolean isTableEmpty(WebElementFacade table) {
		HtmlTable htmlTable = new HtmlTable(table);
		if(htmlTable.getRows().isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	



}
