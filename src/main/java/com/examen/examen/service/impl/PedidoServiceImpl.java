package com.examen.examen.service.impl;

import com.examen.examen.entity.PersonaEntity;
import com.examen.examen.entity.PedidoEntity;
import com.examen.examen.repository.PersonaRepository;
import com.examen.examen.repository.PedidoRepository;
import com.examen.examen.service.PedidoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PersonaRepository personaRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, PersonaRepository personaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    public PedidoEntity guardarPedido(Long personaId, PedidoEntity pedido) {
        PersonaEntity personaExistente = personaRepository.findById(personaId)
                .orElseThrow(() -> new NoSuchElementException("Error persona no existe"));
        pedido.setPersona(personaExistente);
        return pedidoRepository.save(pedido);
    }

    @Override
    public PedidoEntity obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Pedido no encontrado"));
    }

    @Override
    public List<PedidoEntity> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public PedidoEntity actualizarPedido(Long id, Long idPersona, PedidoEntity pedido) {
        PedidoEntity pedidoExistente = obtenerPedidoPorId(id);
        pedidoExistente.setDescripcion(pedido.getDescripcion());
        return pedidoRepository.save(pedidoExistente);
    }

    @Override
    public void eliminarPedido(Long id) {
        PedidoEntity pedidoExistente = obtenerPedidoPorId(id);
        pedidoRepository.delete(pedidoExistente);
    }
}
