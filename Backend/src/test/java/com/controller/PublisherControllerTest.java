package com.Controller;

import com.Service.PublisherService;
import com.model.Publisher;
import com.exception.CustomException;
import com.exception.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PublisherControllerTest {

    @InjectMocks
    private PublisherController publisherController;

    @Mock
    private PublisherService publisherService;

    private Publisher samplePublisher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        samplePublisher = new Publisher(1, "Sample Publisher", "Sample City", "SC");
    }

    @Test
    void testPostPublisher() {
        when(publisherService.findByName(samplePublisher.getName())).thenReturn(null);

        ResponseEntity<?> response = publisherController.postPublisher(samplePublisher);

       
        assertEquals(201, response.getStatusCodeValue());

        Response responseBody = (Response) response.getBody();
        assertEquals("POSTSUCCESS", responseBody.getCode());
        assertEquals("Publisher added successfully", responseBody.getMessage());

        verify(publisherService, times(1)).addPublisher(samplePublisher);
    }

    @Test
    void testPostPublisherAlreadyExists() {
       
        when(publisherService.findByName(samplePublisher.getName())).thenReturn(samplePublisher);

        CustomException thrown = assertThrows(CustomException.class, () -> {
            publisherController.postPublisher(samplePublisher);
        });

        assertEquals("ADDFAILS", thrown.getCode());
        assertEquals("Publisher already exists", thrown.getMessage());

     
        verify(publisherService, never()).addPublisher(samplePublisher);
    }

    @Test
    void testGetAllPublishers() {
      
        List<Publisher> publisherList = Arrays.asList(samplePublisher);
        when(publisherService.getAll()).thenReturn(publisherList);

       
        ResponseEntity<?> response = publisherController.getAllPublishers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(publisherList, response.getBody());

        verify(publisherService, times(1)).getAll();
    }

    @Test
    void testGetPublisherById() {
     
        when(publisherService.findById(1)).thenReturn(samplePublisher);

     
        ResponseEntity<?> response = publisherController.getPublisher(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(samplePublisher, response.getBody());

       
        verify(publisherService, times(1)).findById(1);
    }

    @Test
    void testGetPublisherByName() {
        
        when(publisherService.findByName("Sample Publisher")).thenReturn(samplePublisher);

       
        ResponseEntity<?> response = publisherController.getPublisherByName("Sample Publisher");

      
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(samplePublisher, response.getBody());

       
        verify(publisherService, times(1)).findByName("Sample Publisher");
    }

    @Test
    void testGetPublisherByCity() {
       
        List<Publisher> publisherList = Arrays.asList(samplePublisher);
        when(publisherService.findByCity("Sample City")).thenReturn(publisherList);

     
        ResponseEntity<?> response = publisherController.getPublisherByCity("Sample City");

      
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(publisherList, response.getBody());

        verify(publisherService, times(1)).findByCity("Sample City");
    }

    @Test
    void testUpdatePublisherCity() {
       
        when(publisherService.findById(1)).thenReturn(samplePublisher);
        doNothing().when(publisherService).addPublisher(samplePublisher);

        
        ResponseEntity<?> response = publisherController.updatePublisherCity(1, "New City");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(samplePublisher, response.getBody());
        assertEquals("New City", samplePublisher.getCity());

       
        verify(publisherService, times(1)).addPublisher(samplePublisher);
    }

    @Test
    void testUpdatePublisherName() {
        
        when(publisherService.findById(1)).thenReturn(samplePublisher);
        doNothing().when(publisherService).addPublisher(samplePublisher);

      
        ResponseEntity<?> response = publisherController.updatePublisherName(1, "Updated Publisher");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(samplePublisher, response.getBody());
        assertEquals("Updated Publisher", samplePublisher.getName());

        
        verify(publisherService, times(1)).addPublisher(samplePublisher);
    }

    @Test
    void testUpdatePublisherState() {
        
        when(publisherService.findById(1)).thenReturn(samplePublisher);
        doNothing().when(publisherService).addPublisher(samplePublisher);

        ResponseEntity<?> response = publisherController.updatePublisherState(1, "Updated State");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(samplePublisher, response.getBody());
        assertEquals("Updated State", samplePublisher.getStateCode());

        verify(publisherService, times(1)).addPublisher(samplePublisher);
    }
}
