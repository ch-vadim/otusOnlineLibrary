package com.chvadim.otusOnlineLibrary.repository;

import com.chvadim.otusOnlineLibrary.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.ParagraphView;

public interface BookRepositoryJpa extends JpaRepository<Book, Long> {

}
