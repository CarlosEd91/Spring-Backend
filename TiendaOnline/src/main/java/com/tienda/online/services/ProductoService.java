package com.tienda.online.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tienda.online.models.IngresoProducto;
import com.tienda.online.models.Producto;
import com.tienda.online.models.Usuario;
import com.tienda.online.repositories.ProductoRepository;

@Service
public class ProductoService {

	private ProductoRepository productoRepository;
	private IngresoProductoService ingresoProductoService;

	public ProductoService(ProductoRepository productoRepository,
			IngresoProductoService ingresoProductoService) {
		super();
		this.productoRepository = productoRepository;
		this.ingresoProductoService = ingresoProductoService;
	}
	
	public Producto guardar(Producto producto) {
		producto.setFecha(new Date());
		if(productoRepository.findByNombre(producto.getNombre()) == null) {
			Producto newProducto = productoRepository.save(producto);
			
			if(producto.getCantidad() > 0) {
				IngresoProducto ingresoProducto = new IngresoProducto();
				ingresoProducto.setCantidad(producto.getCantidad());
				ingresoProducto.setFechaIngreso(new Date());
				ingresoProducto.setProducto(newProducto);
				ingresoProducto.setUsuario(new Usuario(6));
				ingresoProductoService.guardarIngreso(newProducto, ingresoProducto);
			}
			return newProducto;
		}
		return null;
	}
	
	public List<Producto> obtenerProductos(){
		return (List<Producto>) productoRepository.findAll();
	}
	
	public Producto obtenerById(Integer id) {
		if(productoRepository.findById(id).isPresent()) {
			return productoRepository.findById(id).get();
		}
		return null;
	}
	
	public void actualizarProducto(Producto producto) {
		productoRepository.save(producto);
	}
}
