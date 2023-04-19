package com.paygoal.prueba.services;

import com.paygoal.prueba.models.Producto;

import java.util.List;

public interface ProductoService {
    public List<Producto> findAllProducto() throws Exception;

    public Producto saveProducto(Producto producto) throws Exception;

    public Producto updateProducto(long id, Producto producto) throws Exception;

    public boolean deleteProducto(long id) throws Exception;

    public Producto findByIdProducto(Long id) throws Exception;

    public List<Producto> findByNameProducto(String name) throws Exception;
    public List<Producto> findAllByOrderByPriceAsc() throws Exception;
}
