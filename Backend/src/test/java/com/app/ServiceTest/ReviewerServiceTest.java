package com.app.ServiceTest;

import com.Service.ReviewerService;
import com.dao.ReviewerDAO;
import com.model.Reviewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReviewerServiceTest {

    @Mock
    private ReviewerDAO reviewerDao;

    @InjectMocks
    private ReviewerService reviewerService;

    private Reviewer reviewer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        reviewer = new Reviewer(1, "John Doe", "New York Times");
    }

    @Test
    public void testGetAllReviewers() {
        // Arrange
        when(reviewerDao.findAll()).thenReturn(List.of(reviewer));

        // Act
        List<Reviewer> result = reviewerService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    public void testFindById_Valid() {
        // Arrange
        when(reviewerDao.findById(1)).thenReturn(Optional.of(reviewer));

        // Act
        Reviewer result = reviewerService.findById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getReviewerId());
    }

    @Test
    public void testFindById_Invalid() {
        // Arrange
        when(reviewerDao.findById(1)).thenReturn(Optional.empty());

        // Act and Assert
        Exception exception = assertThrows(RuntimeException.class, () -> reviewerService.findById(1));
        assertEquals("Reviewer not found with id: 1", exception.getMessage());
    }

    @Test
    public void testAddReviewer() {
        // Arrange
        when(reviewerDao.save(reviewer)).thenReturn(reviewer);

        // Act
        reviewerService.addReviewer(reviewer);

        // Assert
        verify(reviewerDao, times(1)).save(reviewer);
    }

    @Test
    public void testRemoveReviewer() {
        // Arrange
        doNothing().when(reviewerDao).deleteById(1);

        // Act
        reviewerService.removeReviewer(1);

        // Assert
        verify(reviewerDao, times(1)).deleteById(1);
    }

    @Test
    public void testUpdateReviewerName() {
        // Arrange
        when(reviewerDao.findById(1)).thenReturn(Optional.of(reviewer));
        when(reviewerDao.save(any(Reviewer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        String result = reviewerService.updatereviewerFirstName(1, "Jane Doe");

        // Assert
        assertEquals("updated sucessfully", result);
        assertEquals("Jane Doe", reviewer.getName());
    }

    @Test
    public void testUpdateReviewerEmployedBy() {
        // Arrange
        when(reviewerDao.findById(1)).thenReturn(Optional.of(reviewer));
        when(reviewerDao.save(any(Reviewer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        String result = reviewerService.updateReviewerEmployedId(1, "Washington Post");

        // Assert
        assertEquals("updated sucessfully", result);
        assertEquals("Washington Post", reviewer.getEmployedBy());
    }

    @Test
    public void testUpdateReviewerName_ReviewerNotFound() {
        // Arrange
        when(reviewerDao.findById(1)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NullPointerException.class, () -> reviewerService.updatereviewerFirstName(1, "Jane Doe"));
    }
}
