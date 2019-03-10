package com.tienda.online.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.online.models.Compra;
import com.tienda.online.services.CompraService;

@RestController
@RequestMapping("/api/compra")
public class CompraController extends BaseController{

	private CompraService compraService;
	
	public CompraController(CompraService compraService) {
		super();
		this.compraService = compraService;
	}

	@PostMapping
	public Compra guardarCompra(@RequestBody Compra compra) {
		return compraService.guardar(compra);
	}
	
	@GetMapping
	public List<Compra> obtenerCompras(){
		return compraService.obtenerCompras();
	}
}
