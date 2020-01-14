package com.facturacion.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facturacion.model.Factura;
import com.facturacion.service.IFacturaService;

@RestController
@RequestMapping("/facturas")
public class FacturaResource {

	@Autowired
	IFacturaService service;
	
	@PostMapping
	public ResponseEntity<?> addClientes(@RequestBody Factura factura){
		
		return service.add(factura);
	}
	
	
}
