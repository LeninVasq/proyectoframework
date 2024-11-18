package com.proyecto.proyectoso.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "alumnos")
public class Alumnos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alumnosId;

    @Column(name = "nombre", unique = true, nullable = false)
    private String nombre;

    @Column(name = "apellido", unique = true, nullable = false)
    private String apellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fecha_nacimiento;

    @PrePersist
    public void setFechaNacimiento() {
        if (this.fecha_nacimiento == null) {
            this.fecha_nacimiento = new Date(); // Asigna la fecha actual si no se ha establecido una fecha
        }
    }
}
