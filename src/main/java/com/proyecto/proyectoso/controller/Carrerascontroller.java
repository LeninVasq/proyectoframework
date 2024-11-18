package com.proyecto.proyectoso.controller;

import com.proyecto.proyectoso.entity.Carreras;
import com.proyecto.proyectoso.service.CarrerasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Carrerascontroller {

    @Autowired
    private CarrerasService carrerasService;

    @RequestMapping("/carreras")
    public String vercarreras(
            Model modelo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @ModelAttribute("successMessage") String successMessage,
            @ModelAttribute("errorMessage") String errorMessage) {

        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Carreras> pageCarreras = carrerasService.getCarrerasPaginadas(pageable);

            modelo.addAttribute("carreras", pageCarreras);
            modelo.addAttribute("currentPage", page);
            modelo.addAttribute("totalPages", pageCarreras.getTotalPages());
            modelo.addAttribute("totalItems", pageCarreras.getTotalElements());

            if (successMessage != null && !successMessage.isEmpty()) {
                modelo.addAttribute("successMessage", successMessage);
            }
            if (errorMessage != null && !errorMessage.isEmpty()) {
                modelo.addAttribute("errorMessage", errorMessage);
            }

            return "Admin/consultacarrears";
        } catch (Exception e) {
            modelo.addAttribute("errorMessage", "Error al cargar las carreras: " + e.getMessage());
            return "Admin/consultacarrears";
        }
    }

    @PostMapping("/actu")
    public String save(@RequestParam("idCarrera") Long idCarrera,
                       @RequestParam("nombreCarrera") String nombreCarrera,
                       RedirectAttributes redirectAttributes) {
        System.out.println("Carrera ID recibido: " + idCarrera);
        System.out.println("Carrera Nombre recibido: " + nombreCarrera);

        Carreras carrera = new Carreras();
        carrera.setCarrera_id(idCarrera);
        carrera.setNombreCarrera(nombreCarrera);

        try {
            carrerasService.saveOrUpdate(carrera);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Carrera actualizada exitosamente. ID: " + carrera.getCarrera_id() +
                            ", Nombre: " + carrera.getNombreCarrera());
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/carreras";
    }

    @PostMapping("/save")
    public String saveSS(@ModelAttribute Carreras carreras, RedirectAttributes redirectAttributes) {
        System.out.println("Carrera ID: " + carreras.getCarrera_id());
        System.out.println("Carrera Nombre: " + carreras.getNombreCarrera());

        try {
            carrerasService.saveOrUpdate(carreras);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Carrera guardada exitosamente. ID: " + carreras.getCarrera_id() +
                            ", Nombre: " + carreras.getNombreCarrera());
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/carreras";
    }
}