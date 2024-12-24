package com.app.ServiceTest;

import com.Service.PublisherService;
import com.dao.PublisherDAO;
import com.model.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PublisherServiceTest {

    @Mock
    private PublisherDAO publisherDao;

    @InjectMocks
    private PublisherService publisherService;

    private Publisher publisher;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        publisher = new Publisher(1, "Penguin Books", "New York", "NY");
    }

    @Test
    public void testGetAllPublishers() {
        // Arrange
        when(publisherDao.findAll()).thenReturn(List.of(publisher));

        // Act
        List<Publisher> result = publisherService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Penguin Books", result.get(0).getName());
    }

    @Test
    public void testFindById_Valid() {
        // Arrange
        when(publisherDao.findById(1)).thenReturn(Optional.of(publisher));

        // Act
        Publisher result = publisherService.findById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getPublisherId());
    }

    @Test
    public void testFindById_Invalid() {
        // Arrange
        when(publisherDao.findById(1)).thenReturn(Optional.empty());

        // Act and Assert
        Exception exception = assertThrows(RuntimeException.class, () -> publisherService.findById(1));
        assertEquals("Publisher not found with id: 1", exception.getMessage());
    }

    @Test
    public void testAddPublisher() {
        // Arrange
        when(publisherDao.save(publisher)).thenReturn(publisher);

        // Act
        publisherService.addPublisher(publisher);

        // Assert
        verify(publisherDao, times(1)).save(publisher);
    }

    @Test
    public void testRemovePublisher() {
        // Arrange
        doNothing().when(publisherDao).deleteById(1);

        // Act
        publisherService.removePublisher(1);

        // Assert
        verify(publisherDao, times(1)).deleteById(1);
    }

    @Test
    public void testFindByName() {
        // Arrange
        when(publisherDao.findByName("Penguin Books")).thenReturn(publisher);

        // Act
        Publisher result = publisherService.findByName("Penguin Books");

        // Assert
        assertNotNull(result);
        assertEquals("Penguin Books", result.getName());
    }

    @Test
    public void testFindByState() {
        // Arrange
        when(publisherDao.findByStateCode("NY")).thenReturn(List.of(publisher));

        // Act
        List<Publisher> result = publisherService.findByState("NY");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("NY", result.get(0).getStateCode());
    }

    @Test
    public void testFindByCity() {
        // Arrange
        when(publisherDao.findByCity("New York")).thenReturn(List.of(publisher));

        // Act
        List<Publisher> result = publisherService.findByCity("New York");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("New York", result.get(0).getCity());
    }
}
