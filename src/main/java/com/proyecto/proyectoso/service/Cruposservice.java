package com.proyecto.proyectoso.service;


import com.proyecto.proyectoso.entity.Grupos;
import com.proyecto.proyectoso.repository.GruposReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Cruposservice {

    @Autowired
    GruposReporitory gruposRepository;

    public List<Grupos> getGrupo(){
        return gruposRepository.findAll();
    }



    public List<Object[]> getGrupocorro(String correo){
        return gruposRepository.obtenerGruposPorCorreo(correo);
    }
    public Optional<Grupos> getGrupoid(Long id){
        return gruposRepository.findById(id);
    }


    public void saves(Grupos grupos){
        gruposRepository.save(grupos);
    }


    public Optional<Grupos> grupoExiste(String nombreGrupo) {
        return gruposRepository.findByNombreGrupo(nombreGrupo);
    }

    public Optional<Grupos> grupoExisteid(Long nombreGrupo) {
        return gruposRepository.findById(nombreGrupo);
    }
}
