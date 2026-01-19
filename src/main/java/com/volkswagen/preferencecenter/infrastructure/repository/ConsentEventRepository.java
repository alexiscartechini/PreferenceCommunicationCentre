package com.volkswagen.preferencecenter.infrastructure.repository;

import com.volkswagen.preferencecenter.domain.model.ConsentEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentEventRepository extends JpaRepository<ConsentEvent, Long> {
}