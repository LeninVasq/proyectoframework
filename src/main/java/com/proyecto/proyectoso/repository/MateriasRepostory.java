package com.proyecto.proyectoso.repository;

import com.proyecto.proyectoso.entity.Carreras;
import com.proyecto.proyectoso.entity.Materias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriasRepostory extends JpaRepository<Materias, Long>{

    List<Materias> findByNombreMateria(String nombreMateria);
//    List<Materias> findByCarrera(Long materia);


    @Query(value = "SELECT m.materia_id, m.nombre_materia FROM materias m WHERE m.carrera_id = :carreraId", nativeQuery = true)
    List<Object[]> obtenerMateriasPorCarrera(@Param("carreraId") Long carreraId);


}
