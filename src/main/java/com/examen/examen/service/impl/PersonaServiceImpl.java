package com.examen.examen.service.impl;

import com.examen.examen.entity.PersonaEntity;
import com.examen.examen.entity.PedidoEntity;
import com.examen.examen.repository.PersonaRepository;
import com.examen.examen.repository.PedidoRepository;
import com.examen.examen.service.PersonaService;
import com.examen.examen.service.PedidoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    private final PedidoRepository pedidoRepository;
    private final PedidoService pedidoService;

    public PersonaServiceImpl(PersonaRepository personaRepository, PedidoRepository pedidoRepository, PedidoService pedidoService) {
        this.personaRepository = personaRepository;
        this.pedidoRepository = pedidoRepository;
        this.pedidoService = pedidoService;
    }

    @Override
    public PersonaEntity crearPersona(PersonaEntity persona) {
        return personaRepository.save(persona);
    }

    @Override
    public PersonaEntity buscarPersonaPorId(Long id) {
        return personaRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Persona no encontrada"));
    }

    @Override
    public List<PersonaEntity> buscarTodos() {
        return personaRepository.findAll();
    }

    @Override
    public PersonaEntity actualizarPersona(Long id, PersonaEntity personaActual) {
        PersonaEntity personaExistente = buscarPersonaPorId(id);
        personaExistente.setNombre(personaActual.getNombre());
        personaExistente.setDireccionEntity(personaActual.getDireccionEntity());

        personaActual.setId(personaExistente.getId());
        gestionarPedidos2(personaActual.getPedidos());
        return personaRepository.save(personaExistente);
    }

    private void gestionarPedidos(PersonaEntity personaExistente, List<PedidoEntity> pedidosActualizados ){
        List<PedidoEntity> pedidosExistentes = personaExistente.getPedidos();
       pedidosExistentes.clear();

       for(PedidoEntity pedido : pedidosActualizados){
            if(pedido.getId() != null){
                PedidoEntity pedidoEncontrado = pedidoRepository.findById(pedido.getId())
                        .orElseThrow(() -> new NoSuchElementException("Error pedido no ubicado"));
                pedidoEncontrado.setDescripcion(pedido.getDescripcion());
                pedidosExistentes.add(pedidoEncontrado);
            }else {
                pedido.setPersona(personaExistente);
                pedidosExistentes.add(pedido);
            }
        }
    }

   private void gestionarPedidos2(List<PedidoEntity> pedidosActualizados){
        for(PedidoEntity pedido : pedidosActualizados){
            if(pedido.getId() != null){
                    pedidoService.actualizarPedido(pedido.getId(),pedido.getPersona().getId(),pedido);
            }else {
               pedidoService.guardarPedido(pedido.getPersona().getId(),pedido);
            }
        }

    }

    @Override
    public void eliminarPersona(Long id) {
        PersonaEntity personaExistente = buscarPersonaPorId(id);
        personaRepository.delete(personaExistente);
    }
}
