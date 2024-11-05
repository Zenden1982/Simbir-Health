package com.simbir.health.document_service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simbir.health.document_service.Class.History;

public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findByPacientId(Long userId);

}
