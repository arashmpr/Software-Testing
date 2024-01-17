package model.steps;
import exceptions.InsufficientCredit;
import exceptions.InvalidCreditRange;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import model.User;

import static org.junit.jupiter.api.Assertions.*;

public class WithdrawCreditStepDefinitions {
    private User user;

    @Given("^a user with a credit balance of (.+)$")
    public void setInitialCredit(float initialCredit) {
        user = new User();
        user.setCredit(initialCredit);
    }

    @When("^the user withdraws (.+) credit$")
    public void withdrawCredit(float amount) throws InsufficientCredit, InvalidCreditRange {
        user.withdrawCredit(amount);
    }

    @When("^the user attempts to withdraw (.+) credit$")
    public void attemptWithdrawal(float amount) {
        assertThrows(InsufficientCredit.class, () -> user.withdrawCredit(amount));
    }

    @When("^the user attempts to withdraw a negative (.+) credit$")
    public void attemptNegativeWithdrawal(float amount) {
        assertThrows(InvalidCreditRange.class, () -> user.withdrawCredit(amount));
    }

    @Then("^the new credit balance should be (.+)$")
    public void verifyNewCreditBalance(float expectedBalance) {
        assertEquals(expectedBalance, user.getCredit(), 0.001);
    }

    @Then("^an InsufficientCredit exception should be thrown$")
    public void verifyInsufficientCreditException() {
    }

    @Then("^an InvalidCreditRange exception should be thrown$")
    public void verifyInvalidCreditRangeException() {
    }
}
