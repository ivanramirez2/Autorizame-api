package com.example.autorizame_api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.autorizame_api.model.Repartidor;
import com.example.autorizame_api.service.RepartidorService;

import com.example.autorizame_api.exception.RecursoNoEncontradoException;

/**
 * Implementación del servicio de Repartidores.
 * Contiene toda la lógica de negocio del CRUD de repartidores.
 */
@Service
public class RepartidorServiceImpl implements RepartidorService {

    /**
     * Base de datos en memoria simulada.
     * La clave es el ID del repartidor y el valor es el objeto Repartidor.
     */
    private final Map<Long, Repartidor> data = new HashMap<>();

    /**
     * Generador automático de IDs para los repartidores.
     */
    private final AtomicLong sequence = new AtomicLong(1);

    /**
     * Devuelve todos los repartidores del sistema.
     *
     * @return lista de repartidores
     */
    @Override
    public List<Repartidor> findAll() {
        return new ArrayList<>(data.values());
    }

    /**
     * Busca un repartidor por su identificador.
     *
     * @param id identificador del repartidor
     * @return repartidor encontrado
     * @throws RecursoNoEncontradoException si no existe
     */
    @Override
    public Repartidor findById(Long id) {
        Repartidor repartidor = data.get(id);

        if (repartidor == null) {
            throw new RecursoNoEncontradoException("Repartidor con id " + id + " no encontrado");
        }

        return repartidor;
    }

    /**
     * Crea un nuevo repartidor en el sistema.
     *
     * @param repartidor repartidor a crear
     * @return repartidor creado con ID asignado
     */
    @Override
    public Repartidor create(Repartidor repartidor) {
        Long id = sequence.getAndIncrement();
        repartidor.setId(id);
        data.put(id, repartidor);
        return repartidor;
    }

    /**
     * Actualiza un repartidor existente.
     *
     * @param id identificador del repartidor
     * @param repartidor nuevos datos del repartidor
     * @return repartidor actualizado
     * @throws RecursoNoEncontradoException si no existe
     */
    @Override
    public Repartidor update(Long id, Repartidor repartidor) {
        Repartidor existente = data.get(id);

        if (existente == null) {
            throw new RecursoNoEncontradoException("Repartidor con id " + id + " no encontrado");
        }

        existente.setNombre(repartidor.getNombre());
        existente.setEmail(repartidor.getEmail());
        existente.setDireccion(repartidor.getDireccion());

        return existente;
    }

    /**
     * Elimina un repartidor por su identificador.
     *
     * @param id identificador del repartidor
     * @throws RecursoNoEncontradoException si no existe
     */
    @Override
    public void delete(Long id) {
        Repartidor existente = data.get(id);

        if (existente == null) {
            throw new RecursoNoEncontradoException("Repartidor con id " + id + " no encontrado");
        }

        data.remove(id);
    }
}
