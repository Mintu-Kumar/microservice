package com.kafkaproject.repository;

import com.kafkaproject.entities.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaJpaRepository extends JpaRepository<WikimediaData,Long> {


}
