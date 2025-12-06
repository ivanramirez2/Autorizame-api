package com.example.autorizame_api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import com.example.autorizame_api.model.Empresa;
import com.example.autorizame_api.service.EmpresaService;

// IoC + separación por capas.
@Service
public class EmpresaServiceImpl implements EmpresaService {
    // Base de datos en memoria
    private final Map<Long, Empresa> data = new HashMap<>();

    // Generador de IDs 
    private final AtomicLong sequence = new AtomicLong(1);


    @Override
    public List<Empresa> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Empresa findById(Long id) {
        Empresa empresa = data.get(id);
        if (empresa == null) {
            throw new NoSuchElementException("Empresa con id " + id + " no encontrado");
        }
    return empresa;
    }

    @Override
    public Empresa create(Empresa empresa) {
        Long id = sequence.getAndIncrement();  
        empresa.setId(id);                 
        data.put(id, empresa);           
        return empresa;                    
    }


    @Override
    public Empresa update(Long id, Empresa empresa) {
        Empresa existente = data.get(id);

        if (existente == null) {
            throw new NoSuchElementException("Empresa con id " + id + " no encontrado");
        }

        existente.setNombre(empresa.getNombre());
        existente.setEmail(empresa.getEmail());
        existente.setDireccion(empresa.getDireccion());

        return existente;
    }

    @Override
    public void delete(Long id) {
        Empresa existente = data.get(id);

        if (existente == null) {
            throw new NoSuchElementException("Empresa con id " + id + " no encontrado");
        }

        data.remove(id);
    }






    
}
