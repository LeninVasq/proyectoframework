package com.proyecto.proyectoso.controller;

import com.proyecto.proyectoso.entity.Alumnos;
import com.proyecto.proyectoso.entity.Carreras;
import com.proyecto.proyectoso.entity.Materias;
import com.proyecto.proyectoso.service.ALumnosService;
import com.proyecto.proyectoso.service.CarrerasService;
import com.proyecto.proyectoso.service.MateriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class Alumnoscontroller {



    @Autowired
    private ALumnosService alumnosService;

    @RequestMapping("/alumnos")
    public String vercarreras(Model modelo) {
        List<Alumnos> listarcarreras = alumnosService.getAlumnos();
        modelo.addAttribute("list", listarcarreras);



        return "Admin/alumnos";
    }


    @PostMapping("/addalumnos")
    public String saveSS(@ModelAttribute Alumnos alumnos) {
        System.out.println("Carrera ID: " + alumnos.getAlumnosId());
        System.out.println("Carrera Nombre: " + alumnos.getNombre());
        System.out.println("Carrera Apellido: " + alumnos.getApellido());
        String nombre = alumnos.getNombre();
        String apellido = alumnos.getApellido();

        try {
            Optional<Alumnos> existingAlumno = alumnosService.getAlumnosnombrepaellido(nombre,apellido);

            if (existingAlumno.isPresent()) {
                return "index";
            } else {
                alumnosService.saves(alumnos);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return "redirect:/alumnos";
    }


    @PostMapping("/updatealumno")
    public String updateAlumno(@ModelAttribute Alumnos alumnos) {
        System.out.println("Carrera ID: " + alumnos.getAlumnosId());
        System.out.println("Carrera Nombre: " + alumnos.getNombre());
        System.out.println("Carrera Apellido: " + alumnos.getApellido());

        try {
            Optional<Alumnos> existingAlumno = alumnosService.getAlumnosoid(alumnos.getAlumnosId());

            if (existingAlumno.isPresent()) {
                Alumnos alumnoToUpdate = existingAlumno.get();
                alumnoToUpdate.setNombre(alumnos.getNombre());
                alumnoToUpdate.setApellido(alumnos.getApellido());
                alumnosService.saves(alumnoToUpdate);
            } else {
                // Si el alumno no existe, redirige o maneja el caso
                System.out.println("Alumno no encontrado.");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return "redirect:/alumnos";
    }









}
