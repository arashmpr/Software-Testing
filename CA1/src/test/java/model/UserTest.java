package model;

import exceptions.CommodityIsNotInBuyList;
import exceptions.InsufficientCredit;
import exceptions.InvalidCreditRange;

import static org.junit.jupiter.api.Assertions.*;

import exceptions.NotInStock;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class UserTest {

    @Test
    public void testAddCreditWithPositiveAmount() throws InvalidCreditRange {
        float initial_credit = 10;
        float amount = 5;

        float expectedCredit = 15;

        User user = new User();
        user.setCredit(initial_credit);

        user.addCredit(amount);

        assertEquals(expectedCredit, user.getCredit());
    }

    @Test
    public void testAddCreditWithNegativeAmount() {
        float initial_credit = 10;
        float amount = -5;

        User user = new User();
        user.setCredit(initial_credit);

        assertThrows(InvalidCreditRange.class, () -> user.addCredit(amount));
    }

    @Test
    public void testWithdrawCredit() throws InsufficientCredit {
        float initial_credit = 10;
        float amount = 3;

        float expectedCredit = 7;

        User user = new User();
        user.setCredit(initial_credit);

        user.withdrawCredit(amount);

        assertEquals(expectedCredit, user.getCredit());
    }

    @Test
    public void testWithdrawCreditWithAmountGreaterThanCredit() {
        float initial_credit = 10;
        float amount = 12;

        User user = new User();
        user.setCredit(initial_credit);

        assertThrows(InsufficientCredit.class, () -> user.withdrawCredit(amount));
    }

    @Test
    public void testAddBuyItemWithNoBuyHistory() {
        String commodity_id = "1";

        Commodity commodity = new Commodity();
        commodity.setId(commodity_id);

        User user = new User();
        user.addBuyItem(commodity);

        Map<String, Integer> expectedBuyList = new HashMap<>();
        expectedBuyList.put(commodity_id, 1);

        assertEquals(expectedBuyList, user.getBuyList());
    }

    @Test
    public void testAddBuyItemWithBuyHistory() {
        String commodity_id = "21";

        Commodity commodity = new Commodity();
        commodity.setId(commodity_id);

        User user = new User();
        user.addBuyItem(commodity);
        user.addBuyItem(commodity);
        user.addBuyItem(commodity);

        Map<String, Integer> expectedBuyList = new HashMap<>();
        expectedBuyList.put(commodity_id, 3);

        assertEquals(expectedBuyList, user.getBuyList());
    }

    @Test
    public void testAddPurchasedItemForTheFirstTime() {
        String itemId = "13";
        int itemQuantity = 9;

        User user = new User();
        user.addPurchasedItem(itemId, itemQuantity);

        Map<String, Integer> expectedPurchasedList = new HashMap<>();
        expectedPurchasedList.put(itemId, itemQuantity);

        assertEquals(expectedPurchasedList, user.getPurchasedList());
    }

    @Test
    public void testAddPurchasedItemRepeatedly() {
        String itemId = "15";
        int itemQuantity1 = 9;
        int itemQuantity2 = 12;

        User user = new User();
        user.addPurchasedItem(itemId, itemQuantity1);
        user.addPurchasedItem(itemId, itemQuantity2);

        Map<String, Integer> expectedPurchasedList = new HashMap<>();
        expectedPurchasedList.put(itemId, itemQuantity1 + itemQuantity2);

        assertEquals(expectedPurchasedList, user.getPurchasedList());
    }

    @Test
    public void testRemoveItemFromBuyListWithNoCommodityInBuyList() {
        String commodity_id = "1";

        Commodity commodity = new Commodity();
        commodity.setId(commodity_id);

        User user = new User();

        assertThrows(CommodityIsNotInBuyList.class, () -> user.removeItemFromBuyList(commodity));
    }
}