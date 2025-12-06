package com.example.autorizame_api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import com.example.autorizame_api.model.Autorizado;
import com.example.autorizame_api.service.AutorizadoService;

// IoC + separación por capas.
@Service
public class AutorizadoServiceImpl implements AutorizadoService {
    // Base de datos en memoria
    private final Map<Long, Autorizado> data = new HashMap<>();

    // Generador de IDs 
    private final AtomicLong sequence = new AtomicLong(1);


    @Override
    public List<Autorizado> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Autorizado findById(Long id) {
        Autorizado cliente = data.get(id);
        if (cliente == null) {
            throw new NoSuchElementException("Autorizado con id " + id + " no encontrado");
        }
    return cliente;
    }

    @Override
    public Autorizado create(Autorizado autorizado) {
        Long id = sequence.getAndIncrement();  
        autorizado.setId(id);                 
        data.put(id, autorizado);           
        return autorizado;                    
    }


    @Override
    public Autorizado update(Long id, Autorizado autorizado) {
        Autorizado existente = data.get(id);

        if (existente == null) {
            throw new NoSuchElementException("Autorizado con id " + id + " no encontrado");
        }

        existente.setNombre(autorizado.getNombre());
        existente.setEmail(autorizado.getEmail());
        existente.setDireccion(autorizado.getDireccion());

        return existente;
    }

    @Override
    public void delete(Long id) {
        Autorizado existente = data.get(id);

        if (existente == null) {
            throw new NoSuchElementException("Autorizado con id " + id + " no encontrado");
        }

        data.remove(id);
    }






    
}
