package com.example.exerciseDux.model.team;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Entity
@Table(name = "team")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @NotBlank(message = "La liga es obligatoria")
    private String league;
    @NotBlank(message = "El país es obligatorio")
    private String country;

}
