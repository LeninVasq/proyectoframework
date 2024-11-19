    package com.proyecto.proyectoso.entity;

    import jakarta.persistence.*;
    import lombok.Data;

    import java.util.Date;


    @Data
    @Entity
    @Table(name ="inscripciones")
    public class inscripciones {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long incripcion_id;



        @ManyToOne
        @JoinColumn(name = "materia_id", referencedColumnName = "materia_id")
        private Materias materia_id;

        @ManyToOne
        @JoinColumn(name = "alumnosId", referencedColumnName = "alumnosId")
        private Alumnos alumnosId;

        @ManyToOne
        @JoinColumn(name = "grupo_id", referencedColumnName = "grupo_id")
        private Grupos grupo_id;

        @Column(name = "estado")
        private String estado = "inscripto";

        private Date feha_incripcion;

        @PrePersist
        public void setFechaIncripcion() {
            if (this.feha_incripcion == null) {
                this.feha_incripcion =  new java.util.Date(); // Asigna la fecha actual si no se ha establecido una fecha
            }
        }


    }
