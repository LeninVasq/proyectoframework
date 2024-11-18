    package com.proyecto.proyectoso.entity;

    import jakarta.persistence.*;
    import lombok.Data;

    import java.sql.Date;

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
        private String estado = "activo";

        private Date feha_incripcion;



    }
