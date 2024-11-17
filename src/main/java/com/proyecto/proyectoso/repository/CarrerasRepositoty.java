package com.proyecto.proyectoso.repository;

import com.proyecto.proyectoso.entity.Carreras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrerasRepositoty extends JpaRepository<Carreras, Long> {
    public abstract boolean existsByNombreCarrera(String nombreCarrera);
    boolean existsById(Long idCarrera);  // Verificar existencia por ID
    Optional<Carreras> findById(Long idCarrera);
}

