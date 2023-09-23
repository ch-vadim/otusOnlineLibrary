package com.chvadim.otusOnlineLibrary.repository;

import com.chvadim.otusOnlineLibrary.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CommentRepositoryJpa extends JpaRepository<Comment, Long> {
}
