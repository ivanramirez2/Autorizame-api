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

/**
 * Implementación del servicio de Pedidos.
 * Contiene toda la lógica de negocio del CRUD y la agregación.
 */

@Service
public class PedidoServiceImpl implements PedidoService {

    /**
     * Base de datos en memoria simulada.
     * La clave es el ID del pedido y el valor es el objeto Pedido.
     */
    private final Map<Long, Pedido> data = new HashMap<>();

    /**
     * Generador automático de IDs para los pedidos.
     */
    private final AtomicLong sequence = new AtomicLong(1);

    /**
     * Devuelve todos los pedidos del sistema.
     *
     * @return lista de pedidos
     */
    @Override
    public List<Pedido> findAll() {
        return new ArrayList<>(data.values());
    }

    /**
     * Busca un pedido por su identificador.
     *
     * @param id identificador del pedido
     * @return pedido encontrado
     * @throws NoSuchElementException si no existe
     */
    @Override
    public Pedido findById(Long id) {
        Pedido pedido = data.get(id);

        if (pedido == null) {
            throw new NoSuchElementException("Pedido con id " + id + " no encontrado");
        }

        return pedido;
    }

    /**
     * Crea un nuevo pedido en el sistema.
     * Valida que todos los IDs relacionados existan.
     *
     * @param pedido pedido a crear
     * @return pedido creado con ID asignado
     * @throws IllegalArgumentException si faltan relaciones
     */
    @Override
    public Pedido create(Pedido pedido) {

        // Validación de relaciones obligatorias
        if (pedido.getIdCliente() == null ||
            pedido.getIdAutorizado() == null ||
            pedido.getIdRepartidor() == null ||
            pedido.getIdEmpresa() == null) {

            throw new IllegalArgumentException("Todos los IDs relacionados son obligatorios");
        }

        // Generación y asignación del ID
        Long id = sequence.getAndIncrement();
        pedido.setId(id);

        // Guardado en la base de datos en memoria
        data.put(id, pedido);

        return pedido;
    }

    /**
     * Actualiza un pedido existente.
     *
     * @param id identificador del pedido
     * @param pedido datos nuevos del pedido
     * @return pedido actualizado
     */
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

    /**
     * Elimina un pedido por su identificador.
     *
     * @param id identificador del pedido
     */
    @Override
    public void delete(Long id) {
        Pedido existente = data.get(id);

        if (existente == null) {
            throw new NoSuchElementException("Pedido con id " + id + " no encontrado");
        }

        data.remove(id);
    }

    /**
     * AGREGACIÓN:
     * Devuelve cuántos pedidos tiene un cliente.
     *
     * @param idCliente identificador del cliente
     * @return número total de pedidos del cliente
     */
    @Override
    public Long countByCliente(Long idCliente) {
        return data.values() // Todos los pedidos
                .stream()    // Recorre la colección
                .filter(p -> p.getIdCliente().equals(idCliente)) // Solo los del cliente
                .count();    // Cuenta cuántos hay
    }
}
