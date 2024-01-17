package model;

import utils.CucumberOptions;
import io.cucumber.junit.platform.engine.Cucumber;

@Cucumber
@CucumberOptions(
        features = {"classpath:model/features/BDD_User_AddCredit.feature",
                "classpath:model/features/BDD_User_removeItemFromBuyList.feature",
                "classpath:model/features/BDD_User_withdrawCredit.feature"},
        glue = {"model/steps"},
        plugin = {"pretty"}
)
public class RunCucumberTest {
}




