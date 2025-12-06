package com.example.autorizame_api.service;

import java.util.List;

import com.example.autorizame_api.model.Pedido;



public interface PedidoService {

    List<Pedido> findAll();

    Pedido findById(Long id);

    Pedido create(Pedido pedido);

    Pedido update(Long id, Pedido pedido);

    void delete(Long id);

    Long countByCliente(Long idCliente);

}

    

