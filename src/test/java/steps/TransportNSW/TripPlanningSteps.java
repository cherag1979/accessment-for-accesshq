package steps.TransportNSW;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import net.thucydides.core.annotations.Step;
import pages.TransportNSW.TripPlanPage;

public class TripPlanningSteps {
	
	TripPlanPage tripPlanPage;

	
	@Step
	@Given("^(.*) is planning a trip$")
    public void iPlanTrip(String user) {
		tripPlanPage.open();
    }
	
	@Step
	@Given("^wants to travel between '(.*)' and '(.*)'$")
    public void iTravelBetween(String from, String to) {
		tripPlanPage.travelBetween(from, to);
    }
	
	@Step
	@Given("^wants to leave after (.*) on a (.*)day$")
    public void iChooseToDepOrArr(String time, String weekdayShortName) {
		tripPlanPage.departOrArrive(time,weekdayShortName);
    }
	
	@Step
	@Given("^the trip plan is requested$")
	public void iSearchForTrips() {
		tripPlanPage.searchTrips();
	}
	
	@Step
	@Given("^a list of trips should be provided as below$")
	public void iAssertTripData(DataTable expectedTrips) {
		Assert.assertTrue(tripPlanPage.verifyTripDetails(expectedTrips));
	}
	
	
}
