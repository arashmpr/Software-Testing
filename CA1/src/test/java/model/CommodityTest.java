package model;

import exceptions.NotInStock;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class CommodityTest {
    @Test
    void testUpdateInStockHasStock() throws NotInStock {
        Commodity commodity = new Commodity();
        commodity.setInStock(10);

        int amount = 5;
        int expectedInStock = 10 + amount;

        commodity.updateInStock(amount);

        assertEquals(expectedInStock, commodity.getInStock());
    }
    @Test
    void testUpdateInStockInsufficient() {
        Commodity commodity = new Commodity();
        commodity.setInStock(10);

        int amount = -15;

        assertThrows(NotInStock.class, () -> commodity.updateInStock(amount));
    }

    @Test
    void testAddRate() {
        Commodity commodity = new Commodity();
        commodity.setInitRate(8);

        commodity.addRate("user1", 5);
        commodity.addRate("user2", 8);

        Map<String, Integer> expectedUserRatings = new HashMap<>();
        expectedUserRatings.put("user1", 5);
        expectedUserRatings.put("user2", 8);
        float expectedRating = 7;

        assertEquals(expectedUserRatings, commodity.getUserRate());
        assertEquals(expectedRating, commodity.getRating());
    }
}