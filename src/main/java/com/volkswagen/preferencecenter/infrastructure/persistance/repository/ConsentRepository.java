package com.volkswagen.preferencecenter.infrastructure.persistance.repository;

import com.volkswagen.preferencecenter.domain.model.Consent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConsentRepository extends JpaRepository<Consent, Long> {
    List<Consent> findByUserId(UUID id);
}