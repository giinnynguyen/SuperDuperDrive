package com.example.demo.test;
import com.example.demo.controllers.OrderController;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSubmitOrder() {
        User user = new User();
        user.setId(1L);
        user.setUsername("ginny");
        user.setPassword("abcdefg");
        Cart userCart = new Cart();
        Item i = new Item();
        i.setPrice(new BigDecimal(1212));
        userCart.addItem(i);
        user.setCart(userCart);

        ModifyCartRequest cartRequest = new ModifyCartRequest();
        cartRequest.setUsername("ginny");
        cartRequest.setItemId(1L);
        cartRequest.setQuantity(2);

        when(userRepository.findByUsername("ginny")).thenReturn(user);

        ResponseEntity<UserOrder> response = orderController.submit("ginny");

        assertEquals(200, response.getStatusCodeValue());
        UserOrder resultOrder = response.getBody();
        assertEquals(user.getCart(), resultOrder.getCart());
    }

    @Test
    public void testSubmitOrderUserNotFound() {
        when(userRepository.findByUsername("hhhhadsasas")).thenReturn(null);

        ResponseEntity<UserOrder> response = orderController.submit("hhhhadsasas");

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testGetOrderHistoryForUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("ginny");
        user.setPassword("abcdefg");
        user.setCart(new Cart());

        List<UserOrder> orderList = new ArrayList<>();
        UserOrder order1 = new UserOrder();
        order1.setId(1L);
        order1.setUser(user);
        UserOrder order2 = new UserOrder();
        order2.setId(2L);
        order2.setUser(user);
        orderList.add(order1);
        orderList.add(order2);

        when(userRepository.findByUsername("ginny")).thenReturn(user);
        when(orderRepository.findByUser(user)).thenReturn(orderList);

        ResponseEntity<List<UserOrder>> response = orderController.getOrdersForUser("ginny");

        assertEquals(200, response.getStatusCodeValue());
        List<UserOrder> resultOrders = response.getBody();
        assertEquals(2, resultOrders.size());
    }

    @Test
    public void testGetOrderHistoryForUserUserNotFound() {
        when(userRepository.findByUsername("sdasdasd")).thenReturn(null);

        ResponseEntity<List<UserOrder>> response = orderController.getOrdersForUser("sdasdasd");

        assertEquals(404, response.getStatusCodeValue());
    }
}