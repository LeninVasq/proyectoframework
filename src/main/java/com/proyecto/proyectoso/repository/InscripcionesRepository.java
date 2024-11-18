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






}
