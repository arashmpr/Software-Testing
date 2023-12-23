package model;

import exceptions.NotInStock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

class CommodityTest {

    private Commodity commodity;

    @BeforeEach
    void setUp() {
        commodity = new Commodity();
        commodity.setInStock(10);
        commodity.setInitRate(4.5f);
    }

    @Test
    void updateInStock_WithPositiveAmount_ShouldIncreaseInStock() throws NotInStock {
        int amount = 5;
        commodity.updateInStock(amount);
        int expectedInStock = 10 + amount;
        Assertions.assertEquals(expectedInStock, commodity.getInStock());
    }

    @Test
    void updateInStock_WithNegativeAmount_ShouldDecreaseInStock() throws NotInStock {
        int amount = -5;
        commodity.updateInStock(amount);
        int expectedInStock = 10 + amount;
        Assertions.assertEquals(expectedInStock, commodity.getInStock());
    }

    @Test
    void updateInStock_WithNegativeAmountGreaterThanInStock_ShouldThrowNotInStockException() {
        int amount = -15;
        Assertions.assertThrows(NotInStock.class, () -> commodity.updateInStock(amount));
    }
}