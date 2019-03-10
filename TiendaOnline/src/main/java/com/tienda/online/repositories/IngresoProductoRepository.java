package com.tienda.online.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tienda.online.models.IngresoProducto;

@Repository
public interface IngresoProductoRepository extends CrudRepository<IngresoProducto, Integer>{

}
