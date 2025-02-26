package com.demo.service;

import com.demo.domain.Order;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmailServiceTest {

    private EmailService emailService;
    private Order order;

    @Before
    public void setUp() {
        emailService = EmailService.getInstance();
        order = new Order();
    }

    @Test
    public void testGetInstance_ShouldReturnSameInstance() {
        EmailService instance1 = EmailService.getInstance();
        EmailService instance2 = EmailService.getInstance();
        assertSame(instance1, instance2);
    }

    @Test(expected = RuntimeException.class)
    public void testSendEmail_ShouldThrowException() {
        emailService.sendEmail(order);
    }

    @Test
    public void testSendEmail_WithCC_ShouldReturnTrue() {
        boolean result = emailService.sendEmail(order, "test@example.com");
        assertTrue(result);
        assertTrue(order.isCustomerNotified());
    }
}



//JUnit is a unit testing open-source framework for the
// Java programming language
//assertArrayEquals
//Test(expected=NullPointerException.class)
//Test(timeout=100) if program takes more time than this then test fail
// run-with recognize test Class
//run-with Parameterized class
//define actual parameters parameterize method constructor plays passing parameters to Test method
//suit Test Anotation @SuitClass({})