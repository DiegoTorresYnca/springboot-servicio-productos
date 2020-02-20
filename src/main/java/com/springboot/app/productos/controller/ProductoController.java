package com.springboot.app.productos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.productos.models.entity.Producto;
import com.springboot.app.productos.models.service.IProductoService;

@RestController /* Para indicar que es un controlador rest */
public class ProductoController {
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer port;

	@Autowired
	private IProductoService productoService;

	@GetMapping("/listar")
	public List<Producto> listar() {
		return productoService.findAll().stream().map(pro -> {
			//pro.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			pro.setPort(port);
			return pro;
		}).collect(Collectors.toList());
	}

	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) {
		Producto pro = productoService.findById(id);
		pro.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		return pro;
	}
}
