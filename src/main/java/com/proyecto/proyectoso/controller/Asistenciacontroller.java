package com.proyecto.proyectoso.controller;

import com.proyecto.proyectoso.service.InscripcionesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class Asistenciacontroller {

    @Autowired
    private InscripcionesService inscripcionService;

    @GetMapping("/asistencia/{id}")
    public String asistencia(Model model, @PathVariable("id") Long alumnoId) {
        List<Object[]> inscripciones = inscripcionService.obtenerDatosPorGrupo(alumnoId);
        // Agregar las inscripciones al modelo
        model.addAttribute("inscripciones", inscripciones);
        return "Profe/asistencia";
    }
}
