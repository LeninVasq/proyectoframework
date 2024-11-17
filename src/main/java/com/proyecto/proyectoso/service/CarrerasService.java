package com.proyecto.proyectoso.service;

import com.proyecto.proyectoso.entity.Carreras;
import com.proyecto.proyectoso.repository.CarrerasRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrerasService {

    @Autowired
    CarrerasRepositoty carrerasRepositoty;


    public List<Carreras> getcarreras(){
        return carrerasRepositoty.findAll();
    }

    public void saves(Carreras carreras) {
        if (carrerasRepositoty.existsByNombreCarrera(carreras.getNombreCarrera())) {
            throw new IllegalArgumentException("El nombre de la carrera ya existe.");
        }
        carrerasRepositoty.save(carreras);
    }




    public void saveOrUpdate(Carreras carreras) {
        System.out.println("ID recibido en saveOrUpdate: " + carreras.getCarrera_id());
        System.out.println("Nombre recibido en saveOrUpdate: " + carreras.getNombreCarrera());

        if (carreras.getCarrera_id() != null && carrerasRepositoty.existsById(carreras.getCarrera_id())) {
            // Actualizar la carrera
            Carreras existingCarrera = carrerasRepositoty.findById(carreras.getCarrera_id())
                    .orElseThrow(() -> new IllegalArgumentException("La carrera con el id " + carreras.getCarrera_id() + " no existe."));

            existingCarrera.setNombreCarrera(carreras.getNombreCarrera());
            carrerasRepositoty.save(existingCarrera);
        } else {
            // Validar duplicados por nombre
            if (carrerasRepositoty.existsByNombreCarrera(carreras.getNombreCarrera())) {
                throw new IllegalArgumentException("El nombre de la carrera ya existe.");
            }
            carrerasRepositoty.save(carreras);
        }
    }







}
