package com.example.demo.test;

import com.example.demo.controllers.CartController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CarControllerTest {

    @InjectMocks
    private CartController cartController;

    @Mock
    private UserRepository userRepository;


    @Mock
    private ItemRepository itemRepository;

    @Mock
    private CartRepository cartRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddToCart() {
        User user = new User();
        user.setUsername("ginny");
        user.setCart(new Cart());
        Item item = new Item();
        item.setId(1L);
        item.setName("Book");
        item.setPrice(BigDecimal.valueOf(33));

        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("ginny");
        request.setItemId(1L);
        request.setQuantity(5);

        when(userRepository.findByUsername("ginny")).thenReturn(user);
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        ResponseEntity<Cart> response = cartController.addTocart(request);

        assertEquals(200, response.getStatusCodeValue());
        Cart cart = response.getBody();
        assertEquals(5, cart.getItems().size());
    }

    @Test
    public void testRemoveFromCart() {
        User user = new User();
        user.setUsername("ginny");
        user.setCart(new Cart());
        Item item = new Item();
        item.setId(1L);
        item.setName("Book");
        item.setPrice(BigDecimal.valueOf(33));

        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("ginny");
        request.setItemId(1L);
        request.setQuantity(2);

        when(userRepository.findByUsername("ginny")).thenReturn(user);
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        ResponseEntity<Cart> response = cartController.removeFromcart(request);

        assertEquals(200, response.getStatusCodeValue());
        Cart cart = response.getBody();
        assertEquals(0, cart.getItems().size());
    }
}