package com.tienda.online.controllers;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.online.models.Producto;
import com.tienda.online.services.ProductoService;

@RestController
@RequestMapping("/api/producto")
public class ProductoController extends BaseController{

	private ProductoService productoService;
	
	public ProductoController(ProductoService productoService) {
		super();
		this.productoService = productoService;
	}

	@PostMapping
	public Producto guardarProducto(@RequestBody Producto producto) {
		Producto newProducto = productoService.guardar(producto);
		if(newProducto == null) {
			throw new DataIntegrityViolationException("Ya existe un producto: " + producto.getNombre());
		}
		return newProducto;
	}
	
	@GetMapping
	public List<Producto> obtenerProductos(){
		return productoService.obtenerProductos();
	}
}
