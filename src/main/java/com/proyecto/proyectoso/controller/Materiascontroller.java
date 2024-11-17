package com.proyecto.proyectoso.controller;

import com.proyecto.proyectoso.entity.Carreras;
import com.proyecto.proyectoso.entity.Materias;
import com.proyecto.proyectoso.service.MateriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Materiascontroller {

    @Autowired
    private MateriasService materiasService;
    @RequestMapping("/materias/{id}")
    public String verMateriasPorCarrera(@PathVariable("id") Long carreraId, Model modelo) {
        // Obtener todas las materias
        List<Materias> listarmaterias = materiasService.getMaterias();

        // Crear una lista para almacenar solo las materias que coinciden con el carreraId
        List<Materias> materiasFiltradas = new ArrayList<>();

        // Filtrar las materias por carreraId
        for (Materias materia : listarmaterias) {
            // Asegúrate de que la carrera de la materia no sea nula y que coincida con el carreraId
            if (materia.getCarrera() != null && materia.getCarrera().getCarrera_id().equals(carreraId)) {
                materiasFiltradas.add(materia);
            }
        }

        // Agregar la lista filtrada al modelo
        modelo.addAttribute("list", materiasFiltradas);

        // Retornar la vista
        return "Admin/consultarmateria";
    }



}
