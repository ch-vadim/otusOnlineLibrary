package com.chvadim.otusOnlineLibrary.repository;

import com.chvadim.otusOnlineLibrary.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepositoryJpa extends JpaRepository<Genre, Long> {
}
