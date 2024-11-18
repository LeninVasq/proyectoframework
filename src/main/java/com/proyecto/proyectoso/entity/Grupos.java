package com.proyecto.proyectoso.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name ="grupos")
public class Grupos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long grupo_id;
    @Column(name= "nombre_grupo", unique = true,nullable = false)
    private String nombreGrupo;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fecha_creacion;

    @PrePersist
    public void setFechaCreacion() {
        if (this.fecha_creacion == null) {
            this.fecha_creacion = new Date();
        }
    }
}
