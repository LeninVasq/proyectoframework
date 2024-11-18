package com.proyecto.proyectoso.controller;

import com.proyecto.proyectoso.entity.Carreras;
import com.proyecto.proyectoso.entity.Materias;
import com.proyecto.proyectoso.service.CarrerasService;
import com.proyecto.proyectoso.service.MateriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class Materiascontroller {

    @Autowired
    private MateriasService materiasService;

    @Autowired
    private CarrerasService carrerasService;

    @RequestMapping("/materias/{id}")
    public String verMateriasPorCarrera(
            @PathVariable("id") Long carreraId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model modelo) {

        // Obtener todas las materias
        List<Materias> listarmaterias = materiasService.getMaterias();
        List<Materias> materiasFiltradas = new ArrayList<>();

        // Filtrar materias por carreraId
        Optional<Carreras> carreras = carrerasService.getcarreraid(carreraId);
        for (Materias materia : listarmaterias) {
            if (materia.getCarrera() != null && materia.getCarrera().getCarrera_id().equals(carreraId)) {
                materiasFiltradas.add(materia);
            }
        }

        // Crear una página a partir de la lista filtrada
        int start = (int) Math.min(page * size, materiasFiltradas.size());
        int end = (int) Math.min((page + 1) * size, materiasFiltradas.size());

        // Sublist para obtener solo los elementos de la página actual
        List<Materias> materiasEnPagina = materiasFiltradas.subList(start, end);

        // Crear objeto Page
        Page<Materias> materiasPaginadas = new PageImpl<>(
                materiasEnPagina,
                PageRequest.of(page, size),
                materiasFiltradas.size()
        );

        // Agregar atributos al modelo
        modelo.addAttribute("materias", materiasPaginadas);
        modelo.addAttribute("carreraId", carreraId);
        modelo.addAttribute("carrera", carreras);
        modelo.addAttribute("currentPage", page);
        modelo.addAttribute("pageSize", size);
        modelo.addAttribute("totalPages", materiasPaginadas.getTotalPages());

        return "Admin/consultarmateria";
    }



    @PostMapping("/guardarmaterias")
    public String saveSS(@ModelAttribute Materias materias, RedirectAttributes redirectAttributes) {
        try {
            materiasService.save(materias);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Materia guardada exitosamente. ID: " + materias.getMateria_id() +
                            ", Nombre: " + materias.getNombreMateria());
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/materias/" + materias.getCarrera().getCarrera_id();
    }

    // Para manejar solicitudes AJAX de paginación
    @GetMapping("/materias/{id}/page")
    public String cargarPaginaMaterias(
            @PathVariable("id") Long carreraId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model modelo) {
        return verMateriasPorCarrera(carreraId, page, size, modelo);
    }





    @PostMapping("/actumaterias")
    public String save(@RequestParam("idCarrera") Long idCarrera,
                       @RequestParam("materiaId") Long materiaId,
                       @RequestParam("nombreCarrera") String nombreCarrera,
                       @RequestParam("descripcionCarrera") String descripcionCarrera,
                       RedirectAttributes redirectAttributes) {
        System.out.println("Carrera ID recibido: " + idCarrera);
        System.out.println("Carrera Nombre recibido: " + nombreCarrera); // Verifica el Nombre recibido
        Carreras carrera = carrerasService.getcarreraid(idCarrera)
                .orElseThrow(() -> new IllegalArgumentException("Carrera no encontrada con ID: " + idCarrera));

        Materias materia = new Materias();
        materia.setCarrera(carrera);  // Asignamos la carrera a la materia
        materia.setNombreMateria(nombreCarrera);
        materia.setDescripcion(descripcionCarrera);
        materia.setMateria_id(materiaId);

        try {
            materiasService.save(materia);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Carrera actualizo" +
                            "" +
                            " exitosamente. ID: " + carrera.getCarrera_id() + ", Nombre: " + carrera.getNombreCarrera());
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/materias/" + idCarrera;
    }

}