package com.proyecto.proyectoso.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="carreras")
public class Carreras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carrera_id;
    @Column(name= "nombreCarrera", unique = true,nullable = false)
    private String nombreCarrera;
}
