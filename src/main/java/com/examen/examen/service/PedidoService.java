package com.examen.examen.service;

import com.examen.examen.entity.PedidoEntity;

import java.util.List;

public interface PedidoService {
    PedidoEntity guardarPedido(Long clienteId, PedidoEntity pedido);
    PedidoEntity obtenerPedidoPorId(Long id);
    List<PedidoEntity> obtenerTodosLosPedidos();
    PedidoEntity actualizarPedido(Long id, Long idCliente ,PedidoEntity pedido);
    void eliminarPedido(Long id);
}
