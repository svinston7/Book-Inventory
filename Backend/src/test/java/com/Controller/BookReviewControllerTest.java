//package com.Controller;
//
//import com.Service.BookReviewService;
//import com.exception.CustomException;
//import com.model.BookReview;
//import com.model.Reviewer;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class BookReviewControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private BookReviewService bookReviewService;
//
//    @InjectMocks
//    private BookReviewController bookReviewController;
//
//    @BeforeEach
//    public void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(bookReviewController)
//                .setControllerAdvice(new ExceptionHandlerExceptionResolver())
//                .build();
//    }
//
//    @Test
//    public void testPostReview_Success() throws Exception {
//        BookReview review = new BookReview();
//        review.setIsbn("123456");
//        review.setReviewerId(1);  // Reviewer ID is part of BookReview
//        review.setRating(5);
//        review.setComments("Great book!");
//
//        when(bookReviewService.findByisbn("123456")).thenReturn(null);
//        when(bookReviewService.addBookReview(review)).thenReturn(null);
//
//        mockMvc.perform(post("/api/bookreview/post")
//                        .contentType("application/json")
//                        .content(new ObjectMapper().writeValueAsString(review)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value("POSTSUCCESS"))
//                .andExpect(jsonPath("$.message").value("Book Reviewer added successfully"));
//
//        verify(bookReviewService, times(1)).addBookReview(review);
//    }
//
//    @Test
//    public void testPostReview_Fail_Duplicate() throws Exception {
//        BookReview review = new BookReview();
//        review.setIsbn("123456");
//        review.setReviewerId(1);  // Reviewer ID is part of BookReview
//        review.setRating(5);
//        review.setComments("Great book!");
//
//        when(bookReviewService.findByisbn("123456")).thenReturn(review);
//
//        mockMvc.perform(post("/api/bookreview/post")
//                        .contentType("application/json")
//                        .content(new ObjectMapper().writeValueAsString(review)))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.code").value("ADDFAILS"))
//                .andExpect(jsonPath("$.message").value("Book Reviewer already exists"));
//
//        verify(bookReviewService, times(0)).addBookReview(review);
//    }
//
//    @Test
//    public void testGetAllReviewers() throws Exception {
//        Reviewer reviewer1 = new Reviewer();
//        reviewer1.setReviewerId(1);
//        reviewer1.setName("John");
//        reviewer1.setEmployedBy("Company A");
//
//        Reviewer reviewer2 = new Reviewer();
//        reviewer2.setReviewerId(2);
//        reviewer2.setName("Jane");
//        reviewer2.setEmployedBy("Company B");
//
//        List<Reviewer> reviewers = Arrays.asList(reviewer1, reviewer2);
//
//        when(bookReviewService.getAllReviewers("123456")).thenReturn(reviewers);
//
//        mockMvc.perform(get("/api/bookreview/123456"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$[0].name").value("John"))
//                .andExpect(jsonPath("$[1].name").value("Jane"))
//                .andExpect(jsonPath("$[0].employedBy").value("Company A"))
//                .andExpect(jsonPath("$[1].employedBy").value("Company B"));
//    }
//
//    @Test
//    public void testUpdateRating() throws Exception {
//        String isbn = "123456";
//        int rating = 4;
//        BookReview review = new BookReview();
//        review.setIsbn(isbn);
//        review.setRating(rating);
//
//        when(bookReviewService.findByisbn(isbn)).thenReturn(review);
//        doNothing().when(bookReviewService).addBookReview(review);
//
//        mockMvc.perform(put("/api/bookreview/update/rating/{isbn}", isbn)
//                        .param("rating", String.valueOf(rating)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Updated"));
//
//        verify(bookReviewService, times(1)).addBookReview(review);
//    }
//
//    @Test
//    public void testUpdateComment() throws Exception {
//        String isbn = "123456";
//        String comment = "Updated review comment";
//        BookReview review = new BookReview();
//        review.setIsbn(isbn);
//        review.setComments(comment);
//
//        when(bookReviewService.findByisbn(isbn)).thenReturn(review);
//        doNothing().when(bookReviewService).addBookReview(review);
//
//        mockMvc.perform(put("/api/bookreview/update/comment/{isbn}", isbn)
//                        .param("comment", comment))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Updated"));
//
//        verify(bookReviewService, times(1)).addBookReview(review);
//    }
//}
