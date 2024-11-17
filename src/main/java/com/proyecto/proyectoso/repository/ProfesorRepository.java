package com.proyecto.proyectoso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.proyectoso.entity.Profesor;

import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    Optional<Profesor> findByCorreoAndContra(String correo, String contra);

    Optional<Profesor> findByCorreo(String correo);
}



