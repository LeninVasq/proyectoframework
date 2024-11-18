package com.proyecto.proyectoso.controller;



import com.proyecto.proyectoso.entity.Alumnos;
import com.proyecto.proyectoso.entity.Grupos;
import com.proyecto.proyectoso.service.Cruposservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller

public class Gruposcontroller {



    @Autowired
    private Cruposservice gruposService;

    @RequestMapping("/grupos")
    public String vercarreras(Model modelo) {
        List<Grupos> listarcarreras = gruposService.getGrupo();
        modelo.addAttribute("list", listarcarreras);



        return "Admin/grupos";
    }


    @PostMapping("/addgrupos")
    public String saveSS(@ModelAttribute Grupos grupos) {
        System.out.println("Carrera ID: " + grupos.getGrupo_id());
        System.out.println("Carrera Nombre: " + grupos.getNombreGrupo());


        try {
            Optional<Grupos> existingGrupo = gruposService.grupoExiste(grupos.getNombreGrupo());

            if (existingGrupo.isPresent()) {

            } else {
                gruposService.saves(grupos);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return "redirect:/grupos";
    }


    @PostMapping("/updateGRUPO")
    public String updateAlumno(@ModelAttribute Grupos grupos) {
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


        return "redirect:/grupos";
    }

}
