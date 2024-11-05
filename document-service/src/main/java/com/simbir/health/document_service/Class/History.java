package com.simbir.health.document_service.Class;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "date can't be null")
    private LocalDateTime date;

    @NotNull(message = "pacientId can't be null")
    private Integer pacientId;

    @NotNull(message = "hospitalId can't be null")
    private Integer hospitalId;

    @NotNull(message = "doctorId can't be null")
    private Integer doctorId;

    @NotNull(message = "room can't be null")
    private String room;

    @NotNull(message = "data can't be null")
    private String data;
}
