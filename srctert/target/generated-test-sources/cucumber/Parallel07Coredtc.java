import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"C:/src/tapqa/projects/discount tire/hybris-automation/src/test/resources/dtc/features/Core.feature"},
        plugin = {"json:C:/src/tapqa/projects/discount tire/hybris-automation/target/cucumber-parallel/7.json"},
        monochrome = false,
        tags = {},
        glue = {"dtc.steps", "visualtesting.steps"})
public class Parallel07Coredtc {
}
