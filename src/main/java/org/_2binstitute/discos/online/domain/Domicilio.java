package org._2binstitute.discos.online.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Domicilio {

	private Integer id;
	private String calle;
	private String numExterior;
	private String numInterior;
	private Usuario usuario;
	private Colonia colonia;
	private TipoDomicilio tipoDomicilio;
	
}
