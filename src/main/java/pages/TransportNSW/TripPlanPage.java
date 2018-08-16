package pages.TransportNSW;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import beans.Trip;
import cucumber.api.DataTable;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.WhenPageOpens;

@DefaultUrl("https://transportnsw.info/trip")
public class TripPlanPage extends PageObject {
	
	@FindBy(id="search-input-From") WebElementFacade fromInput;
	@FindBy(id="search-input-To") WebElementFacade toInput;
	@FindBy(xpath="//*[@id=\"tp-search-form\"]/div/span/a/span[1]") WebElementFacade departOrArrive;
	@FindBy(css="label.btn.btn-default.leaving.ng-scope") WebElementFacade leavingLabel;
	@FindBy(css="label.btn.btn-default.arriving.ng-scope") WebElementFacade arrivingLabel;
	@FindBy(id="search-select-date") WebElementFacade dateSelect;
	@FindBy(id="search-select-hour") WebElementFacade hourSelect;
	@FindBy(id="search-select-minute") WebElementFacade minSelect;
	@FindBy(id="search-button") WebElementFacade goButton;
	

	public TripPlanPage (WebDriver driver) {
		super(driver);
	}
	
	@WhenPageOpens
	public void waitUntilPageIsOpened() {
		fromInput.waitUntilVisible();
	}

	public void travelBetween(String from, String to) {
		typeInto(fromInput,from);
		typeInto(toInput,to);
	}

	public void departOrArrive(String time, String weekdayShortName) {
		departOrArrive.click();
		for (int i = 1; i < dateSelect.getSelectOptions().size()-1; i++) {
			if (dateSelect.getSelectOptions().get(i).toLowerCase().contains(weekdayShortName.toLowerCase())) {
				dateSelect.selectByVisibleText(dateSelect.getSelectOptions().get(i));
			}
		}
		String[] hrMin = time.split(":");
		hourSelect.selectByVisibleText(hrMin[0]);
		minSelect.selectByVisibleText(hrMin[1]);
	}

	public void searchTrips() {
		goButton.click();
	}
	
	
	
	public boolean verifyTripDetails (DataTable expectedTrips) {
		Trip actualTripDetails = new Trip();
		for (int i = 0; i < expectedTrips.raw().size() - 1; i++) {
			Trip expectedTripDetails = expectedTrips.asList(Trip.class).get(i);
			actualTripDetails.setDepartureTime(getDriver().findElement(By.xpath("//*[@id=\"tripAnchor" + i + "\"]/div/div/div[1]/div[1]/div/div/span[2]")).getText());
			actualTripDetails.setArrivalTime(getDriver().findElement(By.xpath("//*[@id=\"tripAnchor" + i + "\"]/div/div/div[1]/div[1]/div/div/span[5]")).getText());
			System.out.println("Expected,Actual Departure Time: " + expectedTripDetails.getDepartureTime() + "," + actualTripDetails.getDepartureTime());
			System.out.println("Expected,Actual Arrival Time: " + expectedTripDetails.getArrivalTime() + "," + actualTripDetails.getArrivalTime());

			if(!actualTripDetails.getDepartureTime().contentEquals(expectedTripDetails.getDepartureTime())) {
				return false;
			}
			if(!actualTripDetails.getArrivalTime().contentEquals(expectedTripDetails.getArrivalTime())) {
				return false;
			}
		}
		return true;

	}
	
	
	


}
