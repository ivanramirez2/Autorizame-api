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

/**
 * Implementación del servicio de Clientes.
 * Contiene toda la lógica de negocio del CRUD de clientes.
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    /**
     * Base de datos en memoria simulada.
     * La clave es el ID del cliente y el valor es el objeto Cliente.
     */
    private final Map<Long, Cliente> data = new HashMap<>();

    /**
     * Generador automático de IDs para los clientes.
     */
    private final AtomicLong sequence = new AtomicLong(1);

    /**
     * Devuelve todos los clientes del sistema.
     *
     * @return lista de clientes
     */
    @Override
    public List<Cliente> findAll() {
        return new ArrayList<>(data.values());
    }

    /**
     * Busca un cliente por su identificador.
     *
     * @param id identificador del cliente
     * @return cliente encontrado
     * @throws NoSuchElementException si no existe
     */
    @Override
    public Cliente findById(Long id) {
        Cliente cliente = data.get(id);

        if (cliente == null) {
            throw new NoSuchElementException("Cliente con id " + id + " no encontrado");
        }

        return cliente;
    }

    /**
     * Crea un nuevo cliente en el sistema.
     *
     * @param cliente cliente a crear
     * @return cliente creado con ID asignado
     */
    @Override
    public Cliente create(Cliente cliente) {
        Long id = sequence.getAndIncrement();
        cliente.setId(id);
        data.put(id, cliente);
        return cliente;
    }

    /**
     * Actualiza un cliente existente.
     *
     * @param id identificador del cliente
     * @param cliente nuevos datos del cliente
     * @return cliente actualizado
     * @throws NoSuchElementException si no existe
     */
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

    /**
     * Elimina un cliente por su identificador.
     *
     * @param id identificador del cliente
     * @throws NoSuchElementException si no existe
     */
    @Override
    public void delete(Long id) {
        Cliente existente = data.get(id);

        if (existente == null) {
            throw new NoSuchElementException("Cliente con id " + id + " no encontrado");
        }

        data.remove(id);
    }
}
