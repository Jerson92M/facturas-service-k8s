package com.facturacion.service;

import org.springframework.http.ResponseEntity;

import com.facturacion.model.Factura;

public interface IFacturaService {

	public ResponseEntity<?> add(Factura factura);

}
