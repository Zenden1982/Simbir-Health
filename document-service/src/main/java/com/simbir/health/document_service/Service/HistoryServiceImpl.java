package com.simbir.health.document_service.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.simbir.health.document_service.Class.History;
import com.simbir.health.document_service.Client.AccountServiceClient;
import com.simbir.health.document_service.Repository.HistoryRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    private final AccountServiceClient accountServiceClient;

    @Override
    @Transactional
    public List<History> findByPacientId(Long userId, String token) {

        try {
            if (accountServiceClient.getUserInfo(token).getRoles()
                    .contains("ROLE_DOCTOR") || accountServiceClient.getUserInfo(token).getId().equals(userId)) {
                return historyRepository.findByPacientId(userId);
            }

            throw new RuntimeException("Forbidden");
        } catch (Exception e) {
            throw new RuntimeException("Error getting history", e);
        }
    }

    @Override
    @Transactional
    public History getHistory(Long id, String token) {
        if (accountServiceClient.getUserInfo(token).getRoles()
                .contains("ROLE_DOCTOR") || accountServiceClient.getUserInfo(token).getId().equals(id)) {
            return historyRepository.findById(id).orElseThrow(() -> new RuntimeException("History not found"));
        }

        throw new RuntimeException("Forbidden");
    }

    @Override
    @Transactional
    public History createHistory(History history, String token) {
        if (accountServiceClient.getUserInfo(token).getRoles()
                .contains("ROLE_DOCTOR")
                || accountServiceClient.getUserInfo(token).getRoles()
                        .contains("ROLE_ADMIN")
                || accountServiceClient.getUserInfo(token).getRoles()
                        .contains("ROLE_MANAGER")) {
            return historyRepository.save(history);

        }

        throw new RuntimeException("Forbidden");
    }

    @Override
    @Transactional
    public History updateHistory(Long id, History history, String token) {
        return historyRepository.findById(id).map(historyFinded -> {
            historyFinded.setDate(history.getDate());
            historyFinded.setPacientId(history.getPacientId());
            historyFinded.setHospitalId(history.getHospitalId());
            historyFinded.setDoctorId(history.getDoctorId());
            historyFinded.setRoom(history.getRoom());
            historyFinded.setData(history.getData());
            return historyRepository.save(historyFinded);
        }).orElseThrow(() -> {
            throw new RuntimeException("History not found");
        });
    }

}
