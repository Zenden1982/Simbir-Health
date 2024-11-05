package com.simbir.health.document_service.Service;

import java.util.List;

import com.simbir.health.document_service.Class.History;

public interface HistoryService {

    List<History> findByPacientId(Long userId, String token);

    History getHistory(Long id, String token);

    History createHistory(History history, String token);

    History updateHistory(Long id, History history, String token);
}
