package org.example.myblog.repository;

import org.example.myblog.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findByToken(String token);
    List<RefreshToken> findByUserId(Long userId);
}
