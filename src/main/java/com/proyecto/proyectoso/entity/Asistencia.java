package com.proyecto.proyectoso.entity;


import jakarta.persistence.*;

@Entity
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_asistencia;
    @Column(name= "estado", unique = true,nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "incripcion_id", referencedColumnName = "incripcion_id")
    private inscripciones incripcion_id;

}
