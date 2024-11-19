package com.proyecto.proyectoso.repository;


import com.proyecto.proyectoso.entity.Grupos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface GruposReporitory extends JpaRepository<Grupos, Long> {

    Optional<Grupos> findByNombreGrupo(String nombreGrupo);


    @Query(value = "SELECT g.nombre_grupo, g.grupo_id, p.profesor_id " +
            "FROM grupos g INNER JOIN profesores p ON p.profesor_id = g.profesor_id " +
            "WHERE p.correo = :correo", nativeQuery = true)
    List<Object[]> obtenerGruposPorCorreo(@Param("correo") String correo);

}

