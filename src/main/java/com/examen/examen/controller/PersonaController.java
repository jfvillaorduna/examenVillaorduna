package com.examen.examen.controller;

import com.examen.examen.entity.PersonaEntity;
import com.examen.examen.service.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {
    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public ResponseEntity<PersonaEntity> crearPersona(@RequestBody PersonaEntity persona) {
        PersonaEntity nuevoPersona = personaService.crearPersona(persona);
        return new ResponseEntity<>(nuevoPersona, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaEntity> obtenerPersona(@PathVariable Long id) {
        PersonaEntity persona = personaService.buscarPersonaPorId(id);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @GetMapping
    public List<PersonaEntity> buscarTodos() {
        return personaService.buscarTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaEntity> actualizarPersona(@PathVariable Long id, @RequestBody PersonaEntity persona) {
        PersonaEntity personaActualizado = personaService.actualizarPersona(id, persona);
        return new ResponseEntity<>(personaActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        personaService.eliminarPersona(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
