package com.proyecto.proyectoso.service;

import com.proyecto.proyectoso.entity.Carreras;
import com.proyecto.proyectoso.repository.CarrerasRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrerasService {

    @Autowired
    CarrerasRepositoty carrerasRepositoty;

    // Método original para obtener todas las carreras
    public List<Carreras> getcarreras(){
        return carrerasRepositoty.findAll();
    }

    // Nuevo método para obtener carreras paginadas
    public Page<Carreras> getCarrerasPaginadas(Pageable pageable) {
        return carrerasRepositoty.findAll(pageable);
    }

    public void saves(Carreras carreras) {
        if (carrerasRepositoty.existsByNombreCarrera(carreras.getNombreCarrera())) {
            throw new IllegalArgumentException("El nombre de la carrera ya existe.");
        }
        carrerasRepositoty.save(carreras);
    }

    public Optional<Carreras> getcarreraid(Long id){
        return carrerasRepositoty.findById(id);
    }

    public void saveOrUpdate(Carreras carreras) {
        System.out.println("ID recibido en saveOrUpdate: " + carreras.getCarrera_id());
        System.out.println("Nombre recibido en saveOrUpdate: " + carreras.getNombreCarrera());

        if (carreras.getCarrera_id() != null && carrerasRepositoty.existsById(carreras.getCarrera_id())) {
            // Actualizar la carrera
            Carreras existingCarrera = carrerasRepositoty.findById(carreras.getCarrera_id())
                    .orElseThrow(() -> new IllegalArgumentException("La carrera con el id " + carreras.getCarrera_id() + " no existe."));

            // Verificar si el nuevo nombre ya existe en otra carrera
            if (!existingCarrera.getNombreCarrera().equals(carreras.getNombreCarrera()) &&
                    carrerasRepositoty.existsByNombreCarrera(carreras.getNombreCarrera())) {
                throw new IllegalArgumentException("El nombre de la carrera ya existe.");
            }

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