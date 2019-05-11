
import com.google.inject.Inject;
import com.cost.factory.DriverHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.io.IOException;

public class Hooks {
    DriverHelper driverHelper;
    Scenario scenario;

    @Inject
    public Hooks(DriverHelper diHelper) {
        this.driverHelper = diHelper;
    }

    @Before("@web")
    public void setUp(Scenario _scenario) throws IOException {
        this.scenario = _scenario;
        driverHelper.openBrowser( scenario );
        driverHelper.maximise();
    }

    @After("@web")
    public void tearDown() {
        driverHelper.logScenarioStatus( scenario ).closeBrowser();
    }

}
