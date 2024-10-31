package com.simbir.health.hospital_service.Class;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "name can't be null")
    @Size(min = 1, max = 50, message = "name must be between 1 and 50 characters")
    private String name;

    @NotNull(message = "address can't be null")
    @Size(min = 1, max = 50, message = "address must be between 1 and 50 characters")
    private String address;

    @NotNull(message = "contactPhone can't be null")
    @Size(min = 1, max = 20, message = "contactPhone must be between 1 and 20 characters")
    private String contactPhone;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms;
}
