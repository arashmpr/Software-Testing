package model.steps;

import exceptions.CommodityIsNotInBuyList;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import model.Commodity;
import model.User;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RemoveItemFromBuyListStepDefinitions {
    private User user;

    @Given("^a user with the following buy list:$")
    public void setUserWithBuyList(List<Map<String, String>> buyListDetails) {
        user = new User();
        for (Map<String, String> item : buyListDetails) {
            String commodityId = item.get("Commodity ID");
            int quantity = Integer.parseInt(item.get("Quantity"));
            user.getBuyList().put(commodityId, quantity);
        }
    }

    @When("^the user removes one (.+) from the buy list$")
    public void removeItemFromBuyList(String commodityId) throws CommodityIsNotInBuyList {
        Commodity commodity = new Commodity();
        commodity.setId(commodityId);
        user.removeItemFromBuyList(commodity);
    }
    @When("the user attempts to remove a non-existent item {string} from the buy list")
    public void theUserAttemptsToRemoveANonExistentItemFromTheBuyList(String arg0) {

    }
    @When("the user attempts to remove one grape from the buy list")
    public void theUserAttemptsToRemoveOneGrapeFromTheBuyList() {
    }
    @When("the user removes the last banana from the buy list")
    public void theUserRemovesTheLastBananaFromTheBuyList() {
    }
    @Then("^the buy list should have:$")
    public void verifyBuyList(List<Map<String, String>> expectedBuyList) {
        for (Map<String, String> item : expectedBuyList) {
            String commodityId = item.get("Commodity ID");
            int expectedQuantity = Integer.parseInt(item.get("Quantity"));
            assertEquals(expectedQuantity, user.getBuyList().get(commodityId));
        }
    }
    @Then("^a CommodityIsNotInBuyList exception should be thrown$")
    public void verifyCommodityIsNotInBuyListException() {
        assertThrows(CommodityIsNotInBuyList.class, () -> user.removeItemFromBuyList(new Commodity()));
    }



}
