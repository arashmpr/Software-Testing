
package domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void testGetterAndSetter() {
        Order order = new Order();
        order.setId(1);
        order.setCustomer(101);
        order.setPrice(50);
        order.setQuantity(2);

        assertEquals(1, order.getId());
        assertEquals(101, order.getCustomer());
        assertEquals(50, order.getPrice());
        assertEquals(2, order.getQuantity());
    }

    @Test
    public void testEqualsSameId() {
        Order order1 = new Order();
        order1.setId(1);

        Order order2 = new Order();
        order2.setId(1);

        assertEquals(order1, order2);
    }

    @Test
    public void testEqualsDifferentId() {
        Order order1 = new Order();
        order1.setId(1);

        Order order2 = new Order();
        order2.setId(2);

        assertNotEquals(order1, order2);
    }

    @Test
    public void testEqualsWithNonOrderObject() {
        Order order = new Order();
        order.setId(1);

        Object nonOrderObject = new Object();

        assertNotEquals(order, nonOrderObject);
    }

    @Test
    public void testEqualsWithNull() {
        Order order = new Order();
        order.setId(1);

        assertNotNull(order);
    }

    @Test
    public void testConstructor() {
        Order order = new Order();
        order.setId(1);
        order.setCustomer(101);
        order.setPrice(50);
        order.setQuantity(2);



        assertEquals(1, order.getId());
        assertEquals(101, order.getCustomer());
        assertEquals(50, order.getPrice());
        assertEquals(2, order.getQuantity());
    }

    @Test
    public void testLombokAnnotations() {

        Order order = new Order();
        order.setId(1);
        order.setCustomer(101);
        order.setPrice(50);
        order.setQuantity(2);

        assertEquals(1, order.getId());
        assertEquals(101, order.getCustomer());
        assertEquals(50, order.getPrice());
        assertEquals(2, order.getQuantity());
    }

    @Test
    public void testBoundaryValues() {
        Order order = new Order();
        order.setId(Integer.MAX_VALUE);
        order.setCustomer(Integer.MAX_VALUE);
        order.setPrice(Integer.MAX_VALUE);
        order.setQuantity(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, order.getId());
        assertEquals(Integer.MAX_VALUE, order.getCustomer());
        assertEquals(Integer.MAX_VALUE, order.getPrice());
        assertEquals(Integer.MAX_VALUE, order.getQuantity());
    }

    @Test
    public void testNegativeCases() {
        Order order = new Order();
        order.setId(-1);
        order.setCustomer(-101);
        order.setPrice(-50);
        order.setQuantity(-2);

        assertEquals(-1, order.getId());
        assertEquals(-101, order.getCustomer());
        assertEquals(-50, order.getPrice());
        assertEquals(-2, order.getQuantity());
    }
}
