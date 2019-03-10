package com.tienda.online.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.tienda.online.models.IngresoProducto;
import com.tienda.online.models.Producto;
import com.tienda.online.repositories.IngresoProductoRepository;
import com.tienda.online.repositories.UsuarioRepository;

@Service
public class IngresoProductoService {

	private IngresoProductoRepository ingresoProductoRepository;
	
	private ProductoService productoService;
	private UsuarioRepository usuarioRepository;

	public IngresoProductoService(IngresoProductoRepository ingresoProductoRepository, 
			@Lazy ProductoService productoService, UsuarioRepository usuarioRepository) {
		super();
		this.ingresoProductoRepository = ingresoProductoRepository;
		this.productoService = productoService;
		this.usuarioRepository = usuarioRepository;
	}
	
	public IngresoProducto guardar(IngresoProducto ingresoProducto) {
		ingresoProducto.setFechaIngreso(new Date());
		Producto producto = productoService.obtenerById(ingresoProducto.getProducto().getId());
		if(producto != null) {
			producto.setCantidad(producto.getCantidad() + ingresoProducto.getCantidad());
			productoService.guardar(producto);
			
			return guardarIngreso(producto, ingresoProducto);
		}
		return null;
	}
	
	public List<IngresoProducto> obtenerIngresoProductos(){
		return (List<IngresoProducto>) ingresoProductoRepository.findAll();
	}
	
	public IngresoProducto guardarIngreso(Producto producto, IngresoProducto ingresoProducto) {
		ingresoProducto.setTotal(producto.getPrecio().multiply(
				new BigDecimal(ingresoProducto.getCantidad())));
		IngresoProducto ingresoProductoSaved = ingresoProductoRepository.save(ingresoProducto);
		ingresoProductoSaved.setUsuario(usuarioRepository.
					findById(ingresoProducto.getUsuario().getId()).get());
		return ingresoProductoSaved;
	}
}
