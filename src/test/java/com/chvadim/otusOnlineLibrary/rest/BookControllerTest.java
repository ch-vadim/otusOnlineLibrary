package com.chvadim.otusOnlineLibrary.rest;

import com.chvadim.otusOnlineLibrary.domain.Author;
import com.chvadim.otusOnlineLibrary.domain.Book;
import com.chvadim.otusOnlineLibrary.domain.Genre;
import com.chvadim.otusOnlineLibrary.service.Library;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private Library library;

    @Test
    void shouldReturnCorrectBooksList() throws Exception {
        List<Book> bookList = List.of(
                new Book(
                    1,
                    "book1",
                    new Author(1, "author1"),
                    new Genre(1, "genre1")),
                new Book(
                        2,
                        "book2",
                        new Author(2, "author2"),
                        new Genre(2, "genre2"))
                );

        given(library.getAllBooks()).willReturn(bookList);

        mvc.perform(get("/book/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(bookList)));
    }

    @Test
    void shouldReturnCorrectBookById() throws Exception {
        Book book =  new Book(
                1,
                "book1",
                new Author(1, "author1"),
                new Genre(1, "genre1"));

        given(library.getBookById(1)).willReturn(Optional.of(book));

        mvc.perform(get("/book").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(book)));
    }

    @Test
    void shouldCallRepositorySaveMethod() throws Exception {
        Book book =  new Book(
                1,
                "book1",
                new Author(1, "author1"),
                new Genre(1, "genre1"));

        mvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(book)));

        verify(library, times(1)).saveBook(book);
    }

    @Test
    void shouldUpdateBookName() throws Exception {
        Book book =  new Book(
                1,
                "book1",
                new Author(1, "author1"),
                new Genre(1, "genre1"));

        given(library.getBookById(1)).willReturn(Optional.of(book));
        given(library.saveBook(any())).willAnswer(invocation -> invocation.getArgument(0));

        Book expectedBook = new Book(
                1,
                "new",
                new Author(1, "author1"),
                new Genre(1, "genre1"));

        String expectedResult = mapper.writeValueAsString(expectedBook);

        mvc.perform(patch("/book/{id}", 1)
                        .param("name", "new")
                        .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }
}
