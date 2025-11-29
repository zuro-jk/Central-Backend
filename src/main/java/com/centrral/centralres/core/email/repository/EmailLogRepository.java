package com.centrral.centralres.core.email.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.core.email.model.EmailLog;

public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {

    Optional<EmailLog> findFirstByToAddressAndSubjectOrderBySentAtDesc(String toAddress, String subject);
}