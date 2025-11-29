package com.centrral.centralres.core.security.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.core.security.model.RefreshToken;
import com.centrral.centralres.core.security.model.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByExpiryDateBefore(Instant now);

    void deleteByToken(String token);

    void deleteAllByUser(User user);

    List<RefreshToken> findAllByUser(User user);
}
