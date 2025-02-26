package com.demo.service;

import com.demo.domain.Order;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderServiceTest {

    private OrderService orderService;
    private EmailService emailService;
    private Order order;

    @Before
    public void setUp() {
        emailService = EmailService.getInstance();
        orderService = OrderService.getInstance();
        order = new Order();
    }

    @Test(expected = RuntimeException.class)
    public void testPlaceOrder_ShouldSetPriceWithTaxAndNotifyCustomer() {
        order.setPrice(100.0);
        orderService.placeOrder(order);
        assertEquals(120.0, order.getPriceWithTax(), 0.01);
    }

    @Test
    public void testPlaceOrder_WithCC_ShouldReturnTrueWhenEmailSent() {
        order.setPrice(100.0);
        boolean result = orderService.placeOrder(order, "cc@example.com");
        assertTrue(result);
        assertEquals(120.0, order.getPriceWithTax(), 0.01);
        assertTrue(order.isCustomerNotified());
    }
}
