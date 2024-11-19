package com.proyecto.proyectoso.repository;

import com.proyecto.proyectoso.entity.inscripciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface InscripcionesRepository extends JpaRepository<inscripciones, Long> {

    List<inscripciones> findInscripcionesByAlumnosId_AlumnosId(Long alumnosId);


    @Query(value = "SELECT " +
            "i.incripcion_id, " +  // Corrige el nombre aquí si era un error tipográfico
            "m.nombre_materia, " +
            "o.nombre_grupo, " +
            "p.nombre, " +
            "p.apellido, " +
            "i.feha_incripcion, " + // También verifica 'feha_incripcion', probablemente sea 'fecha_inscripcion'
            "c.nombre_carrera, " +
            "a.alumnos_id " +
            "FROM inscripciones i " +
            "INNER JOIN grupos o ON o.grupo_id = i.grupo_id " +
            "INNER JOIN alumnos a ON i.alumnos_id = a.alumnos_id " +
            "INNER JOIN materias m ON i.materia_id = m.materia_id " +
            "INNER JOIN profesores p ON p.profesor_id = o.profesor_id " +
            "INNER JOIN carreras c ON c.carrera_id = m.carrera_id " +
            "WHERE i.alumnos_id = :alumnosId", nativeQuery = true)
    List<Object[]> obtenerDatosPorAlumno(@Param("alumnosId") Long alumnosId);



    @Query(value = "SELECT " +
            "i.incripcion_id, " +  // Corregido el nombre del campo
            "a.apellido, " +
            "a.nombre, " +
            "i.feha_incripcion " +  // Corregido el nombre del campo
            "FROM inscripciones i " +
            "INNER JOIN grupos o ON o.grupo_id = i.grupo_id " +
            "INNER JOIN alumnos a ON i.alumnos_id = a.alumnos_id " +
            "WHERE o.grupo_id = :grupoId", nativeQuery = true)
    List<Object[]> obtenerDatosPorGrupo(@Param("grupoId") Long grupoId);



}
