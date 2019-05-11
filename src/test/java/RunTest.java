
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "." },tags = {"@sample"}, dryRun = false, strict = false, monochrome = false)

public class RunTest {

}
