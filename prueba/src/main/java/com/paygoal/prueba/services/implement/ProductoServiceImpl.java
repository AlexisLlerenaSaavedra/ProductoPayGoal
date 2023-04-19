package com.paygoal.prueba.services.implement;

import com.paygoal.prueba.models.Producto;
import com.paygoal.prueba.repositories.ProductoRepository;
import com.paygoal.prueba.services.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;
    @Override
    public List<Producto> findAllProducto() throws Exception {
        try {
            return productoRepository.findAll();
        }catch (Exception e){
            throw new Exception("Error en la solicitud");
        }
    }

    @Override
    public Producto saveProducto(Producto producto) throws Exception {
        try {
            return productoRepository.save(producto);
        }catch (Exception e){
            throw new Exception("Error en la solicitud");
        }
    }

    @Override
    public Producto updateProducto(long id, Producto producto) throws Exception {
        try {
            Optional<Producto> entityOptional=productoRepository.findById(id);
            Producto productoActual= entityOptional.get();
            productoActual=productoRepository.save(producto);
            return  productoActual;

        }catch (Exception e){
            throw new Exception("Error de solicitud");
        }
    }

    @Override
    public boolean deleteProducto(long id) throws Exception {

            Optional<Producto> entityOptional=productoRepository.findById(id);
            if (entityOptional.isPresent()){
                productoRepository.delete(entityOptional.get());
                return true;
            }else{
                throw new Exception("El id " + id +" no ha sido encontrado");
            }
    }

    @Override
    public Producto findByIdProducto(Long id) throws Exception {
        if (id<=0){
            throw new Exception("El id del producto no puede ser negativo o 0");
        }else {
            Optional<Producto> optional=productoRepository.findById(id);
            if (optional.isPresent()){
                return optional.get();

            }else{
                throw new Exception("El id " + id +" no ha sido encontrado");
            }
        }



    }

    @Override
    public List<Producto> findByNameProducto(String nombre) throws Exception {
            if ((nombre == null || nombre.trim().isEmpty()) ){
                throw new Exception("El nombre del producto no puede estar vacio");
            }else {
                String nombreBusqueda = nombre.trim().toLowerCase(); // Eliminar espacios en blanco y convertir a minúsculas
                List<Producto> productosEncontrados = productoRepository.findByNombreContainingIgnoreCase(nombreBusqueda);
                if (productosEncontrados.isEmpty()){
                    throw new Exception("No se encontro productos con ese nombre");

                }else {
                    return productosEncontrados;
                }
            }
    }

    @Override
    public List<Producto> findAllByOrderByPriceAsc() throws Exception {
        try {
            List<Producto> productosEncontrados = new ArrayList<>();
            productosEncontrados = productoRepository.findAllByOrderByPrecioAsc();
            return productosEncontrados;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
