package com.chvadim.otusOnlineLibrary.service;

import com.chvadim.otusOnlineLibrary.domain.Book;
import com.chvadim.otusOnlineLibrary.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface Library {

    Book saveBook(Book book);

    Book updateBookNameById(long id, String name);

    List<Book> getAllBooks();

    Optional<Book> getBookById(long id);

    void deleteBookById(long id);

    long countBooks();

    Comment saveComment(Comment comment);

    Comment updateCommentTextById(long id, String text);

    List<Comment> getCommentsByBookId(long bookId);

    void deleteCommentById(long id);

    long countComments();

    long countAuthors();

    long countGenres();
}
