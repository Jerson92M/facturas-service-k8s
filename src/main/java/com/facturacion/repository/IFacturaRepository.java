package com.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facturacion.model.Factura;

@Repository
public interface IFacturaRepository extends JpaRepository<Factura, Integer>{

}
