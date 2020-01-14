package com.facturacion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.facturacion.model.Factura;
import com.facturacion.repository.IFacturaRepository;
import com.facturacion.service.IFacturaService;
import com.facturacion.utils.Response;

@Service
public class FacturaServiceImpl implements IFacturaService{

	@Autowired
	IFacturaRepository repository;
	
	@Override
	public ResponseEntity<?> add(Factura factura) {
		
		String message = "OK";
		HttpStatus status = HttpStatus.OK;
		
		factura.getDetalle().forEach(detalle -> detalle.setFactura(factura));
		
		repository.save(factura);		

		return new ResponseEntity<>(new Response(message), status);
		
	}

	
}
