package com.proyecto.proyectoso.controller;

import com.proyecto.proyectoso.entity.Carreras;
import com.proyecto.proyectoso.entity.Grupos;
import com.proyecto.proyectoso.entity.Materias;
import com.proyecto.proyectoso.entity.inscripciones;
import com.proyecto.proyectoso.service.CarrerasService;
import com.proyecto.proyectoso.service.Cruposservice;
import com.proyecto.proyectoso.service.InscripcionesService;
import com.proyecto.proyectoso.service.MateriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class Incripcionescontoller {

    @Autowired
    private InscripcionesService inscripcionService;
    @Autowired
    private Cruposservice gruposService;

    @Autowired
    private CarrerasService carrerasService;


    @Autowired
    private MateriasService materiasService;


    @GetMapping("/inscripciones/{id}")
    public String obtenerInscripciones(@PathVariable("id") Long alumnoId, Model model) {
        // Obtener las inscripciones para el alumno con el ID proporcionado
        List<inscripciones> inscripciones = inscripcionService.obtenerInscripcionesPorAlumno(alumnoId);
        List<Grupos> listarcarreras = gruposService.getGrupo();
        model.addAttribute("grupos", listarcarreras);

        List<Carreras> listarc = carrerasService.getcarreras();
        model.addAttribute("listarc", listarc);
        // Agregar las inscripciones al modelo





        // Agregar las inscripciones al modelo
        model.addAttribute("inscripciones", inscripciones);

        // Retornar el nombre de la vista HTML (por ejemplo, 'inscripcion.html' si usas Thymeleaf)
        return "Admin/incripcion";
    }

}
