
package domain;
import org.junit.Test;
import static org.junit.Assert.*;

public class EngineTest {

    @Test
    public void testGetAverageOrderQuantityByCustomer() {
        Engine engine = new Engine();

        int customerID = 1;
        int firstOrderQuantity = 45;
        int secondOrderQuantity = 75;

        Order firstOrder = new Order();
        Order secondOrder = new Order();

        firstOrder.setCustomer(customerID);
        secondOrder.setCustomer(customerID);

        firstOrder.setQuantity(firstOrderQuantity);
        secondOrder.setQuantity(secondOrderQuantity);

        engine.orderHistory.add(firstOrder);
        engine.orderHistory.add(secondOrder);

        assertEquals(60, engine.getAverageOrderQuantityByCustomer(customerID));
    }

    @Test
    public void testGetCustomerFraudulentQuantityWhenQuantityIsGreaterThanAverage() {
        Engine engine = new Engine();

        int customerID = 1;
        int firstOrderQuantity = 45;
        int secondOrderQuantity = 75;
        int thirdOrderQuantity = 70;

        Order firstOrder = new Order();
        Order secondOrder = new Order();
        Order thirdOrder = new Order();

        firstOrder.setCustomer(customerID);
        secondOrder.setCustomer(customerID);
        thirdOrder.setCustomer(customerID);

        firstOrder.setQuantity(firstOrderQuantity);
        secondOrder.setQuantity(secondOrderQuantity);
        thirdOrder.setQuantity(thirdOrderQuantity);

        engine.orderHistory.add(firstOrder);
        engine.orderHistory.add(secondOrder);

        assertEquals(10, engine.getCustomerFraudulentQuantity(thirdOrder));
    }

    @Test
    public void testGetCustomerFraudulentQuantityWhenQuantityIsEqualToAverage() {
        Engine engine = new Engine();

        int customerID = 1;
        int firstOrderQuantity = 45;
        int secondOrderQuantity = 75;
        int thirdOrderQuantity = 60;

        Order firstOrder = new Order();
        Order secondOrder = new Order();
        Order thirdOrder = new Order();

        firstOrder.setCustomer(customerID);
        secondOrder.setCustomer(customerID);
        thirdOrder.setCustomer(customerID);

        firstOrder.setQuantity(firstOrderQuantity);
        secondOrder.setQuantity(secondOrderQuantity);
        thirdOrder.setQuantity(thirdOrderQuantity);

        engine.orderHistory.add(firstOrder);
        engine.orderHistory.add(secondOrder);

        assertEquals(0, engine.getCustomerFraudulentQuantity(thirdOrder));
    }

    @Test
    public void testGetCustomerFraudulentQuantityWhenQuantityIsLessThanAverage() {
        Engine engine = new Engine();

        int customerID = 1;
        int firstOrderQuantity = 45;
        int secondOrderQuantity = 75;
        int thirdOrderQuantity = 50;

        Order firstOrder = new Order();
        Order secondOrder = new Order();
        Order thirdOrder = new Order();

        firstOrder.setCustomer(customerID);
        secondOrder.setCustomer(customerID);
        thirdOrder.setCustomer(customerID);

        firstOrder.setQuantity(firstOrderQuantity);
        secondOrder.setQuantity(secondOrderQuantity);
        thirdOrder.setQuantity(thirdOrderQuantity);

        engine.orderHistory.add(firstOrder);
        engine.orderHistory.add(secondOrder);

        assertEquals(0, engine.getCustomerFraudulentQuantity(thirdOrder));
    }

    @Test
    public void testGetQuantityPatternByPriceNoOrder() {
        Engine engine = new Engine();

        int price = 100;

        assertEquals(0, engine.getQuantityPatternByPrice(price));
    }

    @Test
    public void testGetQuantityPatternByPriceSameIds() {
        Engine engine = new Engine();

        int price = 100;
        int firstOrderID = 1;
        int secondOrderID = 1;

        Order firstOrder = new Order();
        Order secondOrder = new Order();

        firstOrder.setId(firstOrderID);
        secondOrder.setId(secondOrderID);

        engine.orderHistory.add(firstOrder);
        engine.orderHistory.add(secondOrder);

        assertEquals(0, engine.getQuantityPatternByPrice(price));
    }

    @Test
    public void testGetQuantityPatternByPriceDifferentPrices() {
        Engine engine = new Engine();

        int price = 300;
        int firstOrderID = 1;
        int secondOrderID = 2;
        int firstPrice = 100;
        int secondPrice = 200;

        Order firstOrder = new Order();
        Order secondOrder = new Order();

        firstOrder.setId(firstOrderID);
        secondOrder.setId(secondOrderID);

        firstOrder.setPrice(firstPrice);
        secondOrder.setPrice(secondPrice);

        engine.orderHistory.add(firstOrder);
        engine.orderHistory.add(secondOrder);

        assertEquals(0, engine.getQuantityPatternByPrice(price));
    }

    @Test
    public void testGetQuantityPatternByPriceDifferentOrdersSamePrices() {
        Engine engine = new Engine();

        int price = 150;
        int firstOrderID = 1;
        int secondOrderID = 2;
        int firstPrice = 150;
        int secondPrice = 150;
        int firstOrderQuantity = 30;
        int secondOrderQuantity = 60;

        Order firstOrder = new Order();
        Order secondOrder = new Order();

        firstOrder.setId(firstOrderID);
        secondOrder.setId(secondOrderID);

        firstOrder.setPrice(firstPrice);
        secondOrder.setPrice(secondPrice);

        firstOrder.setQuantity(firstOrderQuantity);
        secondOrder.setQuantity(secondOrderQuantity);

        engine.orderHistory.add(firstOrder);
        engine.orderHistory.add(secondOrder);

        assertEquals(30, engine.getQuantityPatternByPrice(price));
    }

    @Test
    public void testGetQuantityPatternByPriceDifferentOrdersSamePricesTwiceThatDiffersInDiff() {
        Engine engine = new Engine();

        int price = 150;
        int firstOrderID = 1;
        int secondOrderID = 2;
        int thirdOrderID = 3;
        int firstPrice = 150;
        int secondPrice = 150;
        int thirdPrice = 150;
        int firstOrderQuantity = 30;
        int secondOrderQuantity = 60;
        int thirdOrderQuantity = 100;

        Order firstOrder = new Order();
        Order secondOrder = new Order();
        Order thirdOrder = new Order();

        firstOrder.setId(firstOrderID);
        secondOrder.setId(secondOrderID);
        thirdOrder.setId(thirdOrderID);

        firstOrder.setPrice(firstPrice);
        secondOrder.setPrice(secondPrice);
        thirdOrder.setPrice(thirdPrice);

        firstOrder.setQuantity(firstOrderQuantity);
        secondOrder.setQuantity(secondOrderQuantity);
        thirdOrder.setQuantity(thirdOrderQuantity);

        engine.orderHistory.add(firstOrder);
        engine.orderHistory.add(secondOrder);
        engine.orderHistory.add(thirdOrder);

        assertEquals(0, engine.getQuantityPatternByPrice(price));
    }

    @Test
    public void testGetQuantityPatternByPriceDifferentOrdersSamePricesTwiceThatDiffIsSame() {
        Engine engine = new Engine();

        int price = 150;
        int firstOrderID = 1;
        int secondOrderID = 2;
        int thirdOrderID = 3;
        int firstPrice = 150;
        int secondPrice = 150;
        int thirdPrice = 150;
        int firstOrderQuantity = 30;
        int secondOrderQuantity = 60;
        int thirdOrderQuantity = 90;

        Order firstOrder = new Order();
        Order secondOrder = new Order();
        Order thirdOrder = new Order();

        firstOrder.setId(firstOrderID);
        secondOrder.setId(secondOrderID);
        thirdOrder.setId(thirdOrderID);

        firstOrder.setPrice(firstPrice);
        secondOrder.setPrice(secondPrice);
        thirdOrder.setPrice(thirdPrice);

        firstOrder.setQuantity(firstOrderQuantity);
        secondOrder.setQuantity(secondOrderQuantity);
        thirdOrder.setQuantity(thirdOrderQuantity);

        engine.orderHistory.add(firstOrder);
        engine.orderHistory.add(secondOrder);
        engine.orderHistory.add(thirdOrder);

        assertEquals(30, engine.getQuantityPatternByPrice(price));
    }

    @Test
    public void testAddOrderAndGetFraudulentQuantityContainsOrder(){
        Engine engine = new Engine();

        Order order = new Order();

        engine.orderHistory.add(order);

        assertEquals(0, engine.addOrderAndGetFraudulentQuantity(order));
    }

    @Test
    public void testAddOrderAndGetFraudulentQuantityQuantityNonZero(){
        Engine engine = new Engine();

        int customerID = 1;
        int quantity = 30;

        Order order = new Order();

        order.setCustomer(customerID);
        order.setQuantity(quantity);

        assertEquals(30, engine.addOrderAndGetFraudulentQuantity(order));
    }

    @Test
    public void testAddOrderAndGetFraudulentQuantityQuantityZero(){
        Engine engine = new Engine();

        int firstOrderID = 1;
        int secondOrderID = 2;
        int firstPrice = 150;
        int secondPrice = 150;
        int firstOrderQuantity = 30;
        int secondOrderQuantity = 60;

        Order firstOrder = new Order();
        Order secondOrder = new Order();

        firstOrder.setId(firstOrderID);
        secondOrder.setId(secondOrderID);

        firstOrder.setPrice(firstPrice);
        secondOrder.setPrice(secondPrice);

        firstOrder.setQuantity(firstOrderQuantity);
        secondOrder.setQuantity(secondOrderQuantity);

        engine.orderHistory.add(firstOrder);

        assertEquals(30, engine.addOrderAndGetFraudulentQuantity(secondOrder));
    }
}