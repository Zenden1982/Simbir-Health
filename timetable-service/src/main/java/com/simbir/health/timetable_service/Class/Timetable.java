package com.simbir.health.timetable_service.Class;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "hospitalId can't be null")
    private Long hospitalId;

    @NotNull(message = "doctorId can't be null")
    private Long doctorId;

    @Column(name = "time_from")
    @NotNull(message = "from can't be null")
    @Future(message = "from must be in the future")
    private LocalDateTime from;

    @Column(name = "time_to")
    @NotNull(message = "to can't be null")
    @Future(message = "to must be in the future")
    private LocalDateTime to;

    @NotNull(message = "room can't be null")
    @Size(min = 1, max = 50, message = "room must be between 1 and 50 characters")
    private String room;

    @OneToMany(mappedBy = "timetable", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Appointment> appointments;

}