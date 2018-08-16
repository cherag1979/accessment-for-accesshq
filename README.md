
# Sample Project using Java, Serenity, Cucumber BDD.



## Environment

* git version 2.10.1
* maven version 3.5.4
* Firefox version 57
* Serenity - version 1.5.8
* Serenity Cucumber version 1.1.8
* Platform - OSX - El Capitan 10.11.3


## Prerequisites

On Mac:
* Run below commands from terminal to check if you already have git and maven installed on mac
* git --version
* mvn -v
* If command not found, in order to install git and maven you will need a package manager tool such as homebrew
* From your terminal do brew -v
* If command not found then run command: ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
* After Installation successful!, run below commands to install git and maven
* brew install git
* brew install maven


On Windows:
* Refer to the below link for installing git on windows:
https://www.jamessturtevant.com/posts/5-Ways-to-install-git-on-Windows/
* Refer to the below link for installing maven on windows:
https://www.mkyong.com/maven/how-to-install-maven-in-windows/


* Download and install firefox from this link:
https://www.mozilla.org/en-US/firefox/57.0/releasenotes/


## Running the tests

* Clone this repository from GitHub or
* Copy the clone URL of this Github repository
* Open Terminal on Mac or cmd on windows and go into the folder where repository needs to be saved
* Do git clone <url>
* Goto the assessment folder and unzip the geckodriver.zip (outside the assessment folder, eg in Downloads)
* Open the Serenity.properties file and change the below property accordingly:
* webdriver.gecko.driver = /path/to/geckodriver
* From within the assessment folder run the following command
* mvn clean verify
* After the test execution is completed, navigate to the folder target/site/serenity
* Open the index.html to view the detailed test execution report (including screenshots)


Test Cases:
* The test cases are written in gherkin format in the form of Feature files (.feature) and scenarios within the feature file
* They are located in assessment/src/test/resources/Features
