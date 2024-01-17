package model.steps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;
import exceptions.InvalidCreditRange;
import model.User;

public class AddCreditStepDefinitions {
    private User user;

    @Given("^a user with (.+) initial credit$")
    public void setInitialCredit(float initialCredit) {
        user = new User();
        user.setCredit(initialCredit);
    }

    @When("^the user adds (.+) credit$")
    public void addCredit(float amount) throws InvalidCreditRange {
        user.addCredit(amount);
    }

    @When("^the user wants to add negative amount of (.+) credit$")
    public void addNegativeCredit(float amount) {
        assertThrows(InvalidCreditRange.class, () -> user.addCredit(amount));
    }

    @Then("^credit is added successfully and he/she has (.+) credit")
    public void verifyNewCreditBalance(float expectedBalance) {
        assertEquals(expectedBalance, user.getCredit());
    }

    @Then("^an InvalidCreditRange exception should be thrown at him/her$")
    public void verifyInvalidCreditRangeException() {
        assertThrows(InvalidCreditRange.class, () -> user.addCredit(-1));
    }
}