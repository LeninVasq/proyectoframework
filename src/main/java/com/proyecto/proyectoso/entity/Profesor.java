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

    @Column(name = "estado")
    private String estado = "activo";

    @PrePersist
    public void asignarRolPorDefecto() {
        // Si no se ha asignado un rol y es una inserción, asignar el rol por defecto
        if (this.rol_id == null) {
            this.rol_id = new Roles();
            this.rol_id.setRol_id(2L); // Cambia 1 por el ID del rol que desees como por defecto
        }
    }

    @PreUpdate
    public void noActualizarRol() {
        // Si ya existe un rol asignado, no hacer nada
        if (this.rol_id != null) {
            return;
        }
    }


}
