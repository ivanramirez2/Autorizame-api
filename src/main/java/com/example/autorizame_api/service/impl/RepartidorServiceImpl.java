package com.example.autorizame_api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import com.example.autorizame_api.model.Repartidor;
import com.example.autorizame_api.service.RepartidorService;

// IoC + separación por capas.
@Service
public class RepartidorServiceImpl implements RepartidorService {
    // Base de datos en memoria
    private final Map<Long, Repartidor> data = new HashMap<>();

    // Generador de IDs 
    private final AtomicLong sequence = new AtomicLong(1);


    @Override
    public List<Repartidor> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Repartidor findById(Long id) {
        Repartidor cliente = data.get(id);
        if (cliente == null) {
            throw new NoSuchElementException("Repartidor con id " + id + " no encontrado");
        }
    return cliente;
    }

    @Override
    public Repartidor create(Repartidor repartidor) {
        Long id = sequence.getAndIncrement();  
        repartidor.setId(id);                 
        data.put(id, repartidor);           
        return repartidor;                    
    }


    @Override
    public Repartidor update(Long id, Repartidor repartidor) {
        Repartidor existente = data.get(id);

        if (existente == null) {
            throw new NoSuchElementException("Repartidor con id " + id + " no encontrado");
        }

        existente.setNombre(repartidor.getNombre());
        existente.setEmail(repartidor.getEmail());
        existente.setDireccion(repartidor.getDireccion());

        return existente;
    }

    @Override
    public void delete(Long id) {
        Repartidor existente = data.get(id);

        if (existente == null) {
            throw new NoSuchElementException("Repartidor con id " + id + " no encontrado");
        }

        data.remove(id);
    }






    
}
