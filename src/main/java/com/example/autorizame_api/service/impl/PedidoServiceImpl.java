package com.example.autorizame_api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import com.example.autorizame_api.model.Pedido;
import com.example.autorizame_api.service.PedidoService;

// IoC + separación por capas.
@Service
public class PedidoServiceImpl implements PedidoService {
    // Base de datos en memoria
    private final Map<Long, Pedido> data = new HashMap<>();

    // Generador de IDs 
    private final AtomicLong sequence = new AtomicLong(1);


    @Override
    public List<Pedido> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Pedido findById(Long id) {
        Pedido pedido = data.get(id);
        if (pedido == null) {
            throw new NoSuchElementException("Pedido con id " + id + " no encontrado");
        }
    return pedido;
    }

    @Override
    public Pedido create(Pedido Pedido) {
        Long id = sequence.getAndIncrement();  
        Pedido.setId(id);                 
        data.put(id, Pedido);           
        return Pedido;                    
    }


    @Override
    public Pedido update(Long id, Pedido pedido) {
        Pedido existente = data.get(id);

        if (existente == null) {
            throw new NoSuchElementException("Pedido con id " + id + " no encontrado");
        }

        existente.setDescripcion(pedido.getDescripcion());
        existente.setEstado(pedido.getEstado());
        existente.setFecha(pedido.getFecha());
        existente.setIdCliente(pedido.getIdCliente());
        existente.setIdAutorizado(pedido.getIdAutorizado());
        existente.setIdRepartidor(pedido.getIdRepartidor());
        existente.setIdEmpresa(pedido.getIdEmpresa());

        return existente;
    }


    @Override
    public void delete(Long id) {
        Pedido existente = data.get(id);

        if (existente == null) {
            throw new NoSuchElementException("Pedido con id " + id + " no encontrado");
        }

        data.remove(id);
    }






    
}
