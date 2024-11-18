package com.proyecto.proyectoso.repository;


import com.proyecto.proyectoso.entity.Grupos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface GruposReporitory extends JpaRepository<Grupos, Long> {

    Optional<Grupos> findByNombreGrupo(String nombreGrupo);

}

