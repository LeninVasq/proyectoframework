package com.proyecto.proyectoso.service;


import com.proyecto.proyectoso.entity.Materias;
import com.proyecto.proyectoso.entity.inscripciones;
import com.proyecto.proyectoso.repository.InscripcionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionesService {


    @Autowired
    private InscripcionesRepository inscripcionRepository;


    public List<Object[]> obtenerDatosDeAlumno(Long alumnosId) {
        return inscripcionRepository.obtenerDatosPorAlumno(alumnosId);
    }

    public List<Object[]> obtenerDatosPorGrupo(Long alumnosId) {
        return inscripcionRepository.obtenerDatosPorGrupo(alumnosId);
    }


    public void save(inscripciones inscripciones){
        inscripcionRepository.save(inscripciones);
    }

    public void eliminar(Long id){
        inscripcionRepository.deleteById(id);
    }

    public List<inscripciones> obtenerInscripcionesPorAlumno(Long alumnoId) {
        return inscripcionRepository.findInscripcionesByAlumnosId_AlumnosId(alumnoId);
    }
}
