package com.preferencecenter.infrastructure.persistance.repository;

import com.preferencecenter.domain.model.ConsentChangeEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentChangeEventRepository extends JpaRepository<ConsentChangeEvent, Long> {
}