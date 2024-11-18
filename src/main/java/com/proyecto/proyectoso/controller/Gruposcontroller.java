package com.proyecto.proyectoso.controller;



import com.proyecto.proyectoso.entity.Alumnos;
import com.proyecto.proyectoso.entity.Grupos;
import com.proyecto.proyectoso.entity.Materias;
import com.proyecto.proyectoso.service.Cruposservice;
import com.proyecto.proyectoso.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller

public class Gruposcontroller {



    @Autowired
    private Cruposservice gruposService;
    @Autowired
    private ProfesorService profesorService;

    @RequestMapping("/grupos/{id}")
    public String vercarreras(Model modelo, @PathVariable("id") Long profesorid) {
        List<Grupos> listarcarreras = gruposService.getGrupo();
        List<Grupos> gruposfiltrados = new ArrayList<>();
        Optional<Grupos> grupo = gruposService.getGrupoid(profesorid);
        for (Grupos materia : listarcarreras) {
            if (materia.getProfesor_id() != null && materia.getProfesor_id().getProfesor_id().equals(profesorid)) {
                gruposfiltrados.add(materia);

            }

        }

        modelo.addAttribute("list", gruposfiltrados);
        modelo.addAttribute("profesor", profesorid);


        return "Admin/grupos";
    }


    @PostMapping("/addgrupos")
    public String saveSS(@ModelAttribute Grupos grupos, @RequestParam("id") Long id) {
        System.out.println("Carrera ID: " + grupos.getGrupo_id());
        System.out.println("Carrera Nombre: " + grupos.getNombreGrupo());
        System.out.println("ID recibido: " + id); // Imprimir el valor recibido del RequestParam

        try {
            Optional<Grupos> existingGrupo = gruposService.grupoExiste(grupos.getNombreGrupo());

            if (existingGrupo.isPresent()) {
                // Lógica si el grupo ya existe
            } else {
                gruposService.saves(grupos);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return "redirect:/grupos/" + id;
    }



    @PostMapping("/updateGRUPO")
    public String updateAlumno(@ModelAttribute Grupos grupos,  @RequestParam("id") Long id) {
        System.out.println("Carrera ID: " + grupos.getGrupo_id());
        System.out.println("Carrera Nombre: " + grupos.getNombreGrupo());

        try {
            Optional<Grupos> existingGrupo = gruposService.grupoExisteid(grupos.getGrupo_id());

            if (existingGrupo.isPresent()) {
                Grupos alumnoToUpdate = existingGrupo.get();
                alumnoToUpdate.setNombreGrupo(grupos.getNombreGrupo());

                gruposService.saves(alumnoToUpdate);
            } else {
                System.out.println("Alumno no encontrado.");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }


        return "redirect:/grupos/" + id;
    }

}
