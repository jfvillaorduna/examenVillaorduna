package com.examen.examen.service;

import com.examen.examen.entity.PersonaEntity;

import java.util.List;

public interface PersonaService {
    PersonaEntity crearPersona(PersonaEntity persona);
    PersonaEntity buscarPersonaPorId(Long id);
    List<PersonaEntity> buscarTodos();
    PersonaEntity actualizarPersona(Long id, PersonaEntity persona);
    void eliminarPersona(Long id);
}
