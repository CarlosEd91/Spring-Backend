package com.tienda.online.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tienda.online.models.Compra;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Integer>{

}
