package com.simbir.health.timetable_service.Class;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "time can't be null")
    @Future(message = "time must be in the future")
    private LocalDateTime time;

    @NotNull(message = "userId can't be null")
    private Long userId;

    private Boolean isBooked = false;
    @ManyToOne
    @JoinColumn(name = "timetable_id")
    private Timetable timetable;

}
