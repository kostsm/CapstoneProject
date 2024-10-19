package capstoneProject;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
	EnergySourceTest.class,
	ChargingStationTest.class,
	MultipleExceptionsTest.class
})

public class TestSuite {
}
