package com.proyecto.proyectoso.service;


import com.proyecto.proyectoso.entity.Alumnos;
import com.proyecto.proyectoso.repository.AlumnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ALumnosService {



    @Autowired
    AlumnosRepository alumnosRepository;

    public List<Alumnos> getAlumnos (){
        return alumnosRepository.findAll();
    }


    public Long getcountalumnos(){
        return alumnosRepository.count();
    }

    public Optional<Alumnos> getAlumnosoid (Long id){
        return alumnosRepository.findById(id);
    }


    public Optional<Alumnos> getAlumnosnombrepaellido( String nombre, String apellido) {
        // Intentamos buscar por ID primero
        Optional<Alumnos> alumno ;


        alumno = alumnosRepository.findByNombreAndApellido(nombre, apellido);

        return alumno;
    }



    public void saves(Alumnos alumnos) {
        alumnosRepository.save(alumnos);
    }


}
