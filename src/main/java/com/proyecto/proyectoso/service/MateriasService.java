package com.proyecto.proyectoso.service;

import com.proyecto.proyectoso.entity.Materias;
import com.proyecto.proyectoso.repository.MateriasRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriasService {


    @Autowired
    MateriasRepostory materiaRepository;

    public List<Materias> getMaterias(){
        return materiaRepository.findAll();
    }


   // public List<Materias> getMateriasid(Long id){

     //   return materiaRepository.findByCarrera(id);
    //}


    public List<Object[]> obtenerDatosDeAlumno(Long carreraId) {
        return materiaRepository.obtenerMateriasPorCarrera(carreraId);
    }

    public List<Materias> getMateriasnombre(String materia){
        return materiaRepository.findByNombreMateria(materia);
    }





    public void save(Materias materias){
        materiaRepository.save(materias);
    }


    public void deletemate(Long id){
        materiaRepository.deleteById(id);
    }
}
