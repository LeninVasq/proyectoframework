package com.proyecto.proyectoso.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "profesores")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profesor_id;
    private String nombre;
    private String apellido;
    @Column(name= "correo", unique = true,nullable = false)
    private String correo;
    private String contra;
    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "rol_id")
    private Roles rol_id;
}
