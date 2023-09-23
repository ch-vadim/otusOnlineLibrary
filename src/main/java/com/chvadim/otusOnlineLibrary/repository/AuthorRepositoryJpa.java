package com.chvadim.otusOnlineLibrary.repository;

import com.chvadim.otusOnlineLibrary.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepositoryJpa extends JpaRepository<Author, Long> {
}
