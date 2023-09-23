package com.chvadim.otusOnlineLibrary.service;

import com.chvadim.otusOnlineLibrary.domain.Book;
import com.chvadim.otusOnlineLibrary.domain.Comment;
import com.chvadim.otusOnlineLibrary.repository.AuthorRepositoryJpa;
import com.chvadim.otusOnlineLibrary.repository.BookRepositoryJpa;
import com.chvadim.otusOnlineLibrary.repository.CommentRepositoryJpa;
import com.chvadim.otusOnlineLibrary.repository.GenreRepositoryJpa;
import com.chvadim.otusOnlineLibrary.rest.NotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryImpl implements Library{

    private final AuthorRepositoryJpa authorRepositoryJpa;

    private final GenreRepositoryJpa genreRepositoryJpa;

    private final CommentRepositoryJpa commentRepositoryJpa;

    private final BookRepositoryJpa bookRepositoryJpa;

    public LibraryImpl(AuthorRepositoryJpa authorRepositoryJpa,
                       GenreRepositoryJpa genreRepositoryJpa,
                       CommentRepositoryJpa commentRepositoryJpa,
                       BookRepositoryJpa bookRepositoryJpa) {
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.genreRepositoryJpa = genreRepositoryJpa;
        this.commentRepositoryJpa = commentRepositoryJpa;
        this.bookRepositoryJpa = bookRepositoryJpa;
    }

    @Transactional
    @Override
    public Book saveBook(Book book) {
        return bookRepositoryJpa.save(book);
    }

    @Transactional
    @Override
    public Book updateBookNameById(long id, String name) {
        Book book = bookRepositoryJpa.findById(id).orElseThrow(NotFoundException::new);
        book.setName(name);
        return bookRepositoryJpa.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return bookRepositoryJpa.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> getBookById(long id) {
        return bookRepositoryJpa.findById(id);
    }

    @Transactional
    @Override
    public void deleteBookById(long id) {
        bookRepositoryJpa.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public long countBooks() {
        return bookRepositoryJpa.count();
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        return commentRepositoryJpa.save(comment);
    }

    @Transactional
    @Override
    public Comment updateCommentTextById(long id, String text) {
        Comment comment = commentRepositoryJpa.findById(id).orElseThrow(NotFoundException::new);
        comment.setText(text);
        return commentRepositoryJpa.save(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getCommentsByBookId(long bookId) {
        ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("book.id", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("id", "text", "book.name", "book.author", "book.genre");

        Example<Comment> example = Example.of(
                new Comment(
                    0,
                    null,
                    new Book(bookId, null, null, null)),
                ignoringExampleMatcher);


        return commentRepositoryJpa.findAll(example);
    }

    @Transactional
    @Override
    public void deleteCommentById(long id) {
        commentRepositoryJpa.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public long countComments() {
        return commentRepositoryJpa.count();
    }

    @Transactional(readOnly = true)
    @Override
    public long countAuthors() {
        return authorRepositoryJpa.count();
    }
    @Transactional(readOnly = true)
    @Override
    public long countGenres() {
        return genreRepositoryJpa.count();
    }
}
