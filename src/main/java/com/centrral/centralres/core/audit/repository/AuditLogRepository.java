package com.centrral.centralres.core.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.core.audit.model.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
