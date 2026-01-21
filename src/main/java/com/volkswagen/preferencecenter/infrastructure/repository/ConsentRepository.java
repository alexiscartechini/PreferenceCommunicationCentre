package com.volkswagen.preferencecenter.infrastructure.repository;

import com.volkswagen.preferencecenter.domain.model.Consent;
import com.volkswagen.preferencecenter.domain.model.ConsentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentRepository extends JpaRepository<Consent, ConsentType> {
}
