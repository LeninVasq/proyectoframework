package com.proyecto.proyectoso.controller;

import com.proyecto.proyectoso.entity.*;
import com.proyecto.proyectoso.service.CarrerasService;
import com.proyecto.proyectoso.service.Cruposservice;
import com.proyecto.proyectoso.service.InscripcionesService;
import com.proyecto.proyectoso.service.MateriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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




    @PostMapping("/eliminarRegistro")
    public String saveSS(@RequestParam("idInscripcion") Long idInscripcion,
                         @RequestParam("alumnoid") Long alumnoid) {



        inscripcionService.eliminar(idInscripcion);
        return "redirect:/inscripciones/" +alumnoid;
    }

    @PostMapping("/addincrip")
    public String saveSS(@RequestParam("grupo_id") Long grupo_id,
                         @RequestParam("alumnoid") Long alumnoid,
                         @RequestParam("materia_id") Long materia_id) {

        Grupos grupos = new Grupos();
        grupos.setGrupo_id(grupo_id);

        Materias materias = new Materias();
        materias.setMateria_id(materia_id);

        Alumnos alumnos = new Alumnos();
        alumnos.setAlumnosId(alumnoid);



        inscripciones materia = new inscripciones();
        materia.setGrupo_id(grupos);
        materia.setMateria_id(materias);
        materia.setAlumnosId(alumnos);

        inscripcionService.save(materia);
        return "redirect:/inscripciones/" +alumnoid;
    }
    @GetMapping("/inscripciones/{id}")
    public String obtenerInscripciones(@PathVariable("id") Long alumnoId, Model model) {
        // Obtener las inscripciones para el alumno con el ID proporcionado
        List<Object[]> inscripciones = inscripcionService.obtenerDatosDeAlumno(alumnoId);
        // Agregar las inscripciones al modelo
        model.addAttribute("inscripciones", inscripciones);
        List<Grupos> listarcarreras = gruposService.getGrupo();
        model.addAttribute("grupos", listarcarreras);

        List<Carreras> listarc = carrerasService.getcarreras();
        model.addAttribute("listarc", listarc);
        // Agregar las inscripciones al modelo



        model.addAttribute("alumno", alumnoId);







        // Retornar el nombre de la vista HTML (por ejemplo, 'inscripcion.html' si usas Thymeleaf)
        return "Admin/incripcion";
    }


    @GetMapping("/obtenerMaterias")
    @ResponseBody
    public List<Object[]> obtenerMateriasPorCarrera(@RequestParam Long carreraId) {
        return materiasService.obtenerDatosDeAlumno(carreraId);
    }

}
