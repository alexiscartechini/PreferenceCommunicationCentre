package com.volkswagen.preferencecenter.infrastructure.repository;

import com.volkswagen.preferencecenter.domain.model.Consent;
import com.volkswagen.preferencecenter.domain.model.ConsentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConsentRepository extends JpaRepository<Consent, ConsentType> {
    List<Consent> findByUserId(UUID id);
}