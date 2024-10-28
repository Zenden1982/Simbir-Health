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

    private Long hospitalId;
    private Long doctorId;

    @Column(name = "time_from")
    private LocalDateTime from;

    @Column(name = "time_to")
    private LocalDateTime to;
    private String room;

    @OneToMany(mappedBy = "timetable", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Appointment> appointments;

}