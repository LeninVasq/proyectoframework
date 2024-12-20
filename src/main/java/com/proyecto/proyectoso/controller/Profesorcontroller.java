package com.proyecto.proyectoso.controller;

import com.proyecto.proyectoso.entity.Materias;
import com.proyecto.proyectoso.entity.Profesor;
import com.proyecto.proyectoso.service.ALumnosService;
import com.proyecto.proyectoso.service.ProfesorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller // Cambiado a @Controller porque se están manejando vistas
public class Profesorcontroller {

    @Autowired
    private ProfesorService profesorService;
    @Autowired
    private ALumnosService aLumnosService;


    @RequestMapping("/profesores")
    public String add(Model modelo) {
        List<Profesor> listarcarreras = profesorService.getProfesores();
        modelo.addAttribute("list", listarcarreras);



        return "Admin/profesores";
    }


    @PostMapping("/guardarprofe")
    public String saveSS(@ModelAttribute Profesor profesor, RedirectAttributes redirectAttributes) {
        try {
            profesorService.saves(profesor);

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/profesores";
    }

    @RequestMapping("/")
    public String verPaginaDeInicio(Model model,HttpSession session) {
        // Verificar si el atributo 'rol' existe en la sesión
        String rol = (String) session.getAttribute("rol");

        if (rol == null) {
            // Redirige a una página de inicio de sesión o error si no hay rol
            return "index"; // Cambia según tu lógica
        }

        // Validar el rol y retornar la vista correspondiente
        switch (rol) {
            case "admin":

                return "Admin/Index";
            case "profe":
                return "Profe/Index";
            default:
                return "index"; // Vista para usuarios no autorizados o rol desconocido
        }
    }

    @RequestMapping("/home")
    public String verPaginaDe(HttpSession session) {
        // Verificar si el atributo 'rol' existe en la sesión
        String rol = (String) session.getAttribute("rol");

        if (rol == null) {
            return "redirect:/login";
        }

        switch (rol) {
            case "admin":
                return "Admin/Index";
            case "profe":
                return "Profe/Index";
            default:
                return "index"; // Vista para usuarios no autorizados o rol desconocido
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }


    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        boolean autenticado = profesorService.login(email, password);

        if (autenticado) {
            String rol = profesorService.emaillogin(email);
            session.setAttribute("rol", rol);
            session.setAttribute("correo", email);
            return "redirect:/usuario";
        } else {
            return "index";
        }
    }




    @GetMapping("/usuario")
    public String obtenerUsuario(HttpSession session) {
        String estado = (String) session.getAttribute("estado");
        String rol = (String) session.getAttribute("rol");


        if (rol.equals("admin")) {

            return "Admin/Index";
        } else if (rol.equals("profe")) {
            return "Profe/Index";

        } else {
            return "index";
        }

    }



}
