package com.paygoal.prueba.repositories;

import com.paygoal.prueba.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
    List<Producto> findByNombre(String nombre);

    List<Producto> findAllByOrderByPrecioAsc();


    List<Producto> findByNombreIgnoreCase(String nombre);

    List<Producto> findByNombreContainingIgnoreCase(String nombreBusqueda);
}
