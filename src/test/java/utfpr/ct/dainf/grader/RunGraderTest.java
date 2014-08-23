package utfpr.ct.dainf.grader;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import utfpr.ct.dainf.grader.support.TestSecurityManager;

@CucumberOptions(format = "json:target/cucumber-report.json")
public class RunGraderTest extends AbstractTestNGCucumberTests {
    
    public RunGraderTest() {
        System.setSecurityManager(new TestSecurityManager());
    }
    
}
