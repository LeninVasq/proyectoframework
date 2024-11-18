package com.proyecto.proyectoso.service;


import com.proyecto.proyectoso.entity.inscripciones;
import com.proyecto.proyectoso.repository.InscripcionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionesService {


    @Autowired
    private InscripcionesRepository inscripcionRepository;

    public List<inscripciones> obtenerInscripcionesPorAlumno(Long alumnoId) {
        return inscripcionRepository.findInscripcionesByAlumnosId_AlumnosId(alumnoId);
    }
}
