package com.proyecto.proyectoso.repository;

import com.proyecto.proyectoso.entity.Alumnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlumnosRepository extends JpaRepository<Alumnos, Long> {
    Optional<Alumnos> findByNombreAndApellido(String nombre, String apellido);

}

