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

/**
 * Implementación del servicio de Empresas.
 * Contiene toda la lógica de negocio del CRUD de empresas.
 */
@Service
public class EmpresaServiceImpl implements EmpresaService {

    /**
     * Base de datos en memoria simulada.
     * La clave es el ID de la empresa y el valor es el objeto Empresa.
     */
    private final Map<Long, Empresa> data = new HashMap<>();

    /**
     * Generador automático de IDs para las empresas.
     */
    private final AtomicLong sequence = new AtomicLong(1);

    /**
     * Devuelve todas las empresas del sistema.
     *
     * @return lista de empresas
     */
    @Override
    public List<Empresa> findAll() {
        return new ArrayList<>(data.values());
    }

    /**
     * Busca una empresa por su identificador.
     *
     * @param id identificador de la empresa
     * @return empresa encontrada
     * @throws NoSuchElementException si no existe
     */
    @Override
    public Empresa findById(Long id) {
        Empresa empresa = data.get(id);

        if (empresa == null) {
            throw new NoSuchElementException("Empresa con id " + id + " no encontrado");
        }

        return empresa;
    }

    /**
     * Crea una nueva empresa en el sistema.
     *
     * @param empresa empresa a crear
     * @return empresa creada con ID asignado
     */
    @Override
    public Empresa create(Empresa empresa) {
        Long id = sequence.getAndIncrement();
        empresa.setId(id);
        data.put(id, empresa);
        return empresa;
    }

    /**
     * Actualiza una empresa existente.
     *
     * @param id identificador de la empresa
     * @param empresa nuevos datos de la empresa
     * @return empresa actualizada
     * @throws NoSuchElementException si no existe
     */
    @Override
    public Empresa update(Long id, Empresa empresa) {
        Empresa existente = data.get(id);

        if (existente == null) {
            throw new NoSuchElementException("Empresa con id " + id + " no encontrado");
        }

        existente.setNombre(empresa.getNombre());
        existente.setEmail(empresa.getEmail());
        existente.setDireccion(empresa.getDireccion());
        existente.setTelefono(empresa.getTelefono());
        existente.setCif(empresa.getCif());

        return existente;
    }

    /**
     * Elimina una empresa por su identificador.
     *
     * @param id identificador de la empresa
     * @throws NoSuchElementException si no existe
     */
    @Override
    public void delete(Long id) {
        Empresa existente = data.get(id);

        if (existente == null) {
            throw new NoSuchElementException("Empresa con id " + id + " no encontrado");
        }

        data.remove(id);
    }
}
