package com.example.autorizame_api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.autorizame_api.model.Autorizado;
import com.example.autorizame_api.service.AutorizadoService;

import com.example.autorizame_api.exception.RecursoNoEncontradoException;


/**
 * Implementación del servicio de Autorizados.
 * Contiene toda la lógica de negocio del CRUD de autorizados.
 */
@Service
public class AutorizadoServiceImpl implements AutorizadoService {

    /**
     * Base de datos en memoria simulada.
     * La clave es el ID del autorizado y el valor es el objeto Autorizado.
     */
    private final Map<Long, Autorizado> data = new HashMap<>();

    /**
     * Generador automático de IDs para los autorizados.
     */
    private final AtomicLong sequence = new AtomicLong(1);

    /**
     * Devuelve todos los autorizados del sistema.
     *
     * @return lista de autorizados
     */
    @Override
    public List<Autorizado> findAll() {
        return new ArrayList<>(data.values());
    }

    /**
     * Busca un autorizado por su identificador.
     *
     * @param id identificador del autorizado
     * @return autorizado encontrado
     * @throws RecursoNoEncontradoException si no existe
     */
    @Override
    public Autorizado findById(Long id) {
        Autorizado autorizado = data.get(id);

        if (autorizado == null) {
            throw new RecursoNoEncontradoException("Autorizado con id " + id + " no encontrado");
        }

        return autorizado;
    }

    /**
     * Crea un nuevo autorizado en el sistema.
     *
     * @param autorizado autorizado a crear
     * @return autorizado creado con ID asignado
     */
    @Override
    public Autorizado create(Autorizado autorizado) {
        Long id = sequence.getAndIncrement();
        autorizado.setId(id);
        data.put(id, autorizado);
        return autorizado;
    }

    /**
     * Actualiza un autorizado existente.
     *
     * @param id identificador del autorizado
     * @param autorizado nuevos datos del autorizado
     * @return autorizado actualizado
     * @throws RecursoNoEncontradoException si no existe
     */
    @Override
    public Autorizado update(Long id, Autorizado autorizado) {
        Autorizado existente = data.get(id);

        if (existente == null) {
            throw new RecursoNoEncontradoException("Autorizado con id " + id + " no encontrado");
        }

        existente.setNombre(autorizado.getNombre());
        existente.setEmail(autorizado.getEmail());
        existente.setDireccion(autorizado.getDireccion());

        return existente;
    }

    /**
     * Elimina un autorizado por su identificador.
     *
     * @param id identificador del autorizado
     * @throws RecursoNoEncontradoException si no existe
     */
    @Override
    public void delete(Long id) {
        Autorizado existente = data.get(id);

        if (existente == null) {
            throw new RecursoNoEncontradoException("Autorizado con id " + id + " no encontrado");
        }

        data.remove(id);
    }
}
