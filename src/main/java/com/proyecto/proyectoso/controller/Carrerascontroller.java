package com.proyecto.proyectoso.controller;

import com.proyecto.proyectoso.entity.Carreras;
import com.proyecto.proyectoso.service.CarrerasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class Carrerascontroller {

    @Autowired
    private CarrerasService carrerasService;

    @RequestMapping("/carreras")
    public String vercarreras(Model modelo, @ModelAttribute("successMessage") String successMessage, 
                              @ModelAttribute("errorMessage") String errorMessage) {
        List<Carreras> listarcarreras = carrerasService.getcarreras();
        modelo.addAttribute("list", listarcarreras);
    
        if (successMessage != null && !successMessage.isEmpty()) {
            modelo.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null && !errorMessage.isEmpty()) {
            modelo.addAttribute("errorMessage", errorMessage);
        }
    
        return "Admin/consultacarrears";
    }





    @PostMapping("/actu")
    public String save(@RequestParam("idCarrera") Long idCarrera,
                       @RequestParam("nombreCarrera") String nombreCarrera,
                       RedirectAttributes redirectAttributes) {
        System.out.println("Carrera ID recibido: " + idCarrera); // Verifica el ID recibido
        System.out.println("Carrera Nombre recibido: " + nombreCarrera); // Verifica el Nombre recibido

        // Aquí puedes crear la carrera directamente con los valores recibidos
        Carreras carrera = new Carreras();
        carrera.setCarrera_id(idCarrera);
        carrera.setNombreCarrera(nombreCarrera);

        try {
            carrerasService.saveOrUpdate(carrera);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Carrera actualizo" +
                            "" +
                            " exitosamente. ID: " + carrera.getCarrera_id() + ", Nombre: " + carrera.getNombreCarrera());
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/carreras";
    }



    @PostMapping("/save")
    public String saveSS(@ModelAttribute Carreras carreras, RedirectAttributes redirectAttributes) {
        System.out.println("Carrera ID: " + carreras.getCarrera_id()); // Verifica el ID
        System.out.println("Carrera Nombre: " + carreras.getNombreCarrera()); // Verifica el Nombre

        try {
            carrerasService.saveOrUpdate(carreras);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Carrera guardada exitosamente. ID: " + carreras.getCarrera_id() + ", Nombre: " + carreras.getNombreCarrera());
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/carreras";
    }







}


