package plymp;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


 
@RunWith(Cucumber.class)
@CucumberOptions(strict = false,
		plugin = {"pretty","html:target/rep", "json:target/cucumber.json"},
        tags="@tag1",   
        features = {"C:/Users/EZVSXHA/workspace/ATDD-Eric-Cucumber/target/de.feature"})


public class plympRunner {

}
