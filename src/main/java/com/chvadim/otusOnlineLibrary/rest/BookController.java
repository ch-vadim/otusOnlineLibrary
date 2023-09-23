package com.chvadim.otusOnlineLibrary.rest;

import com.chvadim.otusOnlineLibrary.domain.Book;
import com.chvadim.otusOnlineLibrary.domain.Comment;
import com.chvadim.otusOnlineLibrary.service.Library;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookController {

    private final Library library;

    public BookController(Library library) {
        this.library = library;
    }

    @GetMapping("/book/all")
    public List<Book> getAllBooks() {
        return library.getAllBooks();
    }

    @GetMapping("/book")
    public Book getBookById(@RequestParam("id") long id) {
        return library.getBookById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/book")
    public Book saveBook(@RequestBody Book book) {
        return library.saveBook(book);
    }

    @PatchMapping("/book/{id}")
    public Book updateBookNameById(@PathVariable("id") long id,
            @RequestParam("name") String name) {
        return library.updateBookNameById(id, name);
    }

    @DeleteMapping("/book")
    public void deleteBookById(@RequestParam("id") long id) {
        library.deleteBookById(id);
    }

    @PostMapping("/comment")
    public Comment saveComment(@RequestBody Comment comment) {
        return library.saveComment(comment);
    }

    @PatchMapping("/comment/{id}")
    public Comment updateCommentTextById(@PathVariable("id") long id,
                                         @RequestParam("text") String text) {
        return library.updateCommentTextById(id, text);
    }

    @GetMapping("/comment")
    public List<Comment> getCommentsByBookId(@RequestParam("bookId") long bookId) {
        return library.getCommentsByBookId(bookId);
    }

    @DeleteMapping("/comment")
    public void deleteCommentById(@RequestParam("id") long id) {
        library.deleteCommentById(id);
    }

    @GetMapping("/book/count")
    public Long countBooks() {
        return library.countBooks();
    }

    @GetMapping("/author/count")
    public Long countAuthors() {
        return library.countAuthors();
    }

    @GetMapping("/genre/count")
    public Long countGenres() {
        return library.countGenres();
    }

    @GetMapping("/comment/count")
    public Long countComment() {
        return library.countComments();
    }

    @ExceptionHandler(NotFoundException.class)
    public String notFoundHandler(NotFoundException e) {
        return "Not found";
    }
}
