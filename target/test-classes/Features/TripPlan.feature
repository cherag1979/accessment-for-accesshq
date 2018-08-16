Feature: As a user I should be able to plan my trip by providing trip plan criteria and obtaining appropriate trip plan results

@TripPlanScenario1
Scenario: A trip request can be executed and results returned

Given Phileas is planning a trip
And wants to travel between 'North Sydney Station' and 'Town Hall Station'
And wants to leave after 11:20 on a Monday
When the trip plan is requested
Then a list of trips should be provided as below
|	DepartureTime	|	ArrivalTime	|
|	11:25			|	11:35		|
|	11:31			|	11:41		|
|	11:40			|	11:50		|
|	11:46			|	11:56		|
