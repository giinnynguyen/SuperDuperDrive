package com.example.demo.test;

import com.example.demo.controllers.ItemController;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ItemControllerTest {

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemRepository itemRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllItems() {
        List<Item> itemList = new ArrayList<>();
        Item item1 = new Item();
        item1.setId(1L);
        item1.setName("Book1");
        Item item2 = new Item();
        item2.setId(2L);
        item2.setName("Book2");
        itemList.add(item1);
        itemList.add(item2);

        when(itemRepository.findAll()).thenReturn(itemList);

        ResponseEntity<List<Item>> response = itemController.getItems();

        assertEquals(200, response.getStatusCodeValue());
        List<Item> resultItems = response.getBody();
        assertEquals(2, resultItems.size());
    }

    @Test
    public void testGetItemById() {
        Item item = new Item();
        item.setId(1L);
        item.setName("Book");

        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        ResponseEntity<Item> response = itemController.getItemById(1L);

        assertEquals(200, response.getStatusCodeValue());
        Item resultItem = response.getBody();
        assertEquals("Book", resultItem.getName());
    }

    @Test
    public void testGetItemByIdNotFound() {
        when(itemRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Item> response = itemController.getItemById(1L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testGetItemsByName() {
        List<Item> itemList = new ArrayList<>();
        Item item1 = new Item();
        item1.setId(1L);
        item1.setName("Book");
        Item item2 = new Item();
        item2.setId(2L);
        item2.setName("Book");

        itemList.add(item1);
        itemList.add(item2);

        when(itemRepository.findByName("Book")).thenReturn(itemList);

        ResponseEntity<List<Item>> response = itemController.getItemsByName("Book");

        assertEquals(200, response.getStatusCodeValue());
        List<Item> resultItems = response.getBody();
        assertEquals(2, resultItems.size());
    }

    @Test
    public void testGetItemsByNameNotFound() {
        when(itemRepository.findByName("Book2")).thenReturn(new ArrayList<>());

        ResponseEntity<List<Item>> response = itemController.getItemsByName("Book2");

        assertEquals(404, response.getStatusCodeValue());
    }
}