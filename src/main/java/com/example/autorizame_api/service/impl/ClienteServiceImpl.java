package com.example.autorizame_api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import com.example.autorizame_api.model.Cliente;
import com.example.autorizame_api.service.ClienteService;

// IoC + separación por capas.
@Service
public class ClienteServiceImpl implements ClienteService {
    // Base de datos en memoria
    private final Map<Long, Cliente> data = new HashMap<>();

    // Generador de IDs 
    private final AtomicLong sequence = new AtomicLong(1);


    @Override
    public List<Cliente> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Cliente findById(Long id) {
        Cliente cliente = data.get(id);
        if (cliente == null) {
            throw new NoSuchElementException("Cliente con id " + id + " no encontrado");
        }
    return cliente;
    }

    @Override
    public Cliente create(Cliente cliente) {
        Long id = sequence.getAndIncrement();  
        cliente.setId(id);                 
        data.put(id, cliente);           
        return cliente;                    
    }


    @Override
    public Cliente update(Long id, Cliente cliente) {
        Cliente existente = data.get(id);

        if (existente == null) {
            throw new NoSuchElementException("Cliente con id " + id + " no encontrado");
        }

        existente.setNombre(cliente.getNombre());
        existente.setEmail(cliente.getEmail());
        existente.setDireccion(cliente.getDireccion());

        return existente;
    }

    @Override
    public void delete(Long id) {
        Cliente existente = data.get(id);

        if (existente == null) {
            throw new NoSuchElementException("Cliente con id " + id + " no encontrado");
        }

        data.remove(id);
    }






    
}
