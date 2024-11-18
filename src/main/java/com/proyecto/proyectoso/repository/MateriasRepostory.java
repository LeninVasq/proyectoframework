package com.proyecto.proyectoso.repository;

import com.proyecto.proyectoso.entity.Carreras;
import com.proyecto.proyectoso.entity.Materias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriasRepostory extends JpaRepository<Materias, Long>{

    List<Materias> findByNombreMateria(String nombreMateria);
//    List<Materias> findByCarrera(Long materia);



}
