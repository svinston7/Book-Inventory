package com.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.Controller.PublisherController;
import com.Service.PublisherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Publisher;

class PublisherControllerTest {

    @Mock
    private PublisherService publisherService;

    @InjectMocks
    private PublisherController publisherController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(publisherController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testPostBook() throws Exception {
        Publisher mockPublisher = new Publisher();
        mockPublisher.setName("Test Publisher");
        mockPublisher.setCity("Test City");
        mockPublisher.setStateCode("TS");

        mockMvc.perform(post("/api/publisher/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockPublisher)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("POSTSUCCESS"))
                .andExpect(jsonPath("$.message").value("Publisher added successfully"));

        verify(publisherService, times(1)).addPublisher(any(Publisher.class));
    }

    @Test
    void testGetAllPublishers() throws Exception {
        List<Publisher> mockPublishers = Arrays.asList(
                new Publisher(1, "Publisher1", "City1", "State1"),
                new Publisher(2, "Publisher2", "City2", "State2")
        );

        when(publisherService.getAll()).thenReturn(mockPublishers);

        mockMvc.perform(get("/api/publisher"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Publisher1"))
                .andExpect(jsonPath("$[1].name").value("Publisher2"));

        verify(publisherService, times(1)).getAll();
    }

    @Test
    void testGetPublisherById() throws Exception {
        Publisher mockPublisher = new Publisher(1, "Publisher1", "City1", "State1");

        when(publisherService.findById(1)).thenReturn(mockPublisher);

        mockMvc.perform(get("/api/publisher/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Publisher1"))
                .andExpect(jsonPath("$.city").value("City1"));

        verify(publisherService, times(1)).findById(1);
    }

    @Test
    void testUpdatePublisherCity() throws Exception {
        Publisher mockPublisher = new Publisher(1, "Publisher1", "City1", "State1");

        when(publisherService.findById(1)).thenReturn(mockPublisher);

        mockMvc.perform(put("/api/publisher/update/city/1")
                .param("city", "New City"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("New City"));

        verify(publisherService, times(1)).findById(1);
        verify(publisherService, times(1)).addPublisher(mockPublisher);
    }

    @Test
    void testGetPublisherByName() throws Exception {
        Publisher mockPublisher = new Publisher(1, "Publisher1", "City1", "State1");

        when(publisherService.findByName("Publisher1")).thenReturn(mockPublisher);

        mockMvc.perform(get("/api/publisher/name/Publisher1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Publisher1"))
                .andExpect(jsonPath("$.city").value("City1"));

        verify(publisherService, times(1)).findByName("Publisher1");
    }

    @Test
    void testGetPublisherByCity() throws Exception {
        List<Publisher> mockPublishers = Arrays.asList(
                new Publisher(1, "Publisher1", "City1", "State1"),
                new Publisher(2, "Publisher2", "City1", "State2")
        );

        when(publisherService.findByCity("City1")).thenReturn(mockPublishers);

        mockMvc.perform(get("/api/publisher/city/City1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Publisher1"))
                .andExpect(jsonPath("$[1].name").value("Publisher2"));

        verify(publisherService, times(1)).findByCity("City1");
    }
}
