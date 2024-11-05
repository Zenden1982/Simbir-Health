package com.simbir.health.document_service.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simbir.health.document_service.Class.History;
import com.simbir.health.document_service.Service.HistoryService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("History")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("/Account/{id}")
    public List<History> findByPacientId(@PathVariable Long userId, HttpServletRequest request) {
        return historyService.findByPacientId(userId, request.getHeader("Authorization"));
    }

    @GetMapping("/{id}")
    public History getHistory(@PathVariable Long id, HttpServletRequest request) {
        return historyService.getHistory(id, request.getHeader("Authorization"));
    }

    @PostMapping
    public History createHistory(@RequestBody History history, HttpServletRequest request) {
        return historyService.createHistory(history, request.getHeader("Authorization"));
    }

    @PutMapping("/{id}")
    public History updateHistory(@PathVariable Long id, @RequestBody History history, HttpServletRequest request) {
        return historyService.updateHistory(id, history, request.getHeader("Authorization"));
    }
}
