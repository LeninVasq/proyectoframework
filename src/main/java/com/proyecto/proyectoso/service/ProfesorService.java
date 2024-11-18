package com.proyecto.proyectoso.service;


import com.proyecto.proyectoso.entity.Profesor;
import com.proyecto.proyectoso.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProfesorService {
    @Autowired
    ProfesorRepository profesorRepository;

    public List<Profesor> getProfesores(){
        return profesorRepository.findAll();
    }



    public Optional<Profesor> getProfesoresId(Long id){
        return profesorRepository.findById(id);
    }



    public void saves(Profesor profesores) {
        profesorRepository.save(profesores);
    }

    public boolean login(String correo, String contra){
        Optional<Profesor> profesor = profesorRepository.findByCorreoAndContra(correo, contra);
        return profesor.isPresent();
    }

    public String emaillogin(String correo) {
        Optional<Profesor> profesor = profesorRepository.findByCorreo(correo);
        return profesor.map(p -> p.getRol_id().getRol()).orElse(null);
    }


    public String emailloginp(String correo) {
        Optional<Profesor> profesor = profesorRepository.findByCorreo(correo);
        return profesor.map(Profesor::getEstado).orElse(null);
    }
    public void deleteprofe(Long id) {
        profesorRepository.deleteById(id);
    }
}

