package com.tienda.online.controllers;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.online.models.IngresoProducto;
import com.tienda.online.services.IngresoProductoService;

@RestController
@RequestMapping("/api/ingresoProducto")
public class IngresoProductoController extends BaseController{

	private IngresoProductoService ingresoProductoService;
	
	public IngresoProductoController(IngresoProductoService ingresoProductoService) {
		super();
		this.ingresoProductoService = ingresoProductoService;
	}

	@PostMapping
	public IngresoProducto guardarIngresoProducto(@RequestBody IngresoProducto ingresoProducto) {
		IngresoProducto newIngresoProducto = ingresoProductoService.guardar(ingresoProducto);
		if(newIngresoProducto == null) {
			throw new DataIntegrityViolationException("No existe el producto " );
		}
		return newIngresoProducto;
	}
	
	@GetMapping
	public List<IngresoProducto> obtenerIngresoProductos(){
		return ingresoProductoService.obtenerIngresoProductos();
	}
}
