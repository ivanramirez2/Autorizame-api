package com.example.autorizame_api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.autorizame_api.model.Pedido;
import com.example.autorizame_api.repository.PedidoRepository;
import com.example.autorizame_api.service.PedidoService;
import com.example.autorizame_api.exception.RecursoNoEncontradoException;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Pedido con id " + id + " no encontrado"));
    }

    @Override
    public Pedido create(Pedido pedido) {
        // En una implementación real, aquí deberíamos validar que los IDs de cliente,
        // empresa, etc. existen.
        // Por simplicidad en la migración, confiamos en las Foreign Keys de la base de
        // datos que lanzarán error si no existen.
        if (pedido.getEstado() == null) {
            pedido.setEstado("CREADO");
        }
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido update(Long id, Pedido pedido) {
        Pedido existente = findById(id);

        existente.setDescripcion(pedido.getDescripcion());
        existente.setFecha(pedido.getFecha());
        existente.setIdCliente(pedido.getIdCliente());
        existente.setIdAutorizado(pedido.getIdAutorizado());
        existente.setIdRepartidor(pedido.getIdRepartidor());
        existente.setIdEmpresa(pedido.getIdEmpresa());
        existente.setEstado(pedido.getEstado());

        return pedidoRepository.save(existente);
    }

    @Override
    public void delete(Long id) {
        Pedido existente = findById(id);
        pedidoRepository.delete(existente);
    }

    @Override
    public Long countByCliente(Long idCliente) {
        return pedidoRepository.countByIdCliente(idCliente);
    }

    @Override
    public List<Long> findAutorizadosIdsByCliente(Long idCliente) {
        return pedidoRepository.findAutorizadosIdsByCliente(idCliente);
    }
}
