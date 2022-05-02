package org._2binstitute.discos.online.repository;
import org._2binstitute.discos.online.domain.TipoDomicilio;

public interface TipoDomicilioRepository {
	 	
	TipoDomicilio buscarPorDescripcion(String descripcion);
	
}
