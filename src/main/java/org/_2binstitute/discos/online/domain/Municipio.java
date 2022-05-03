package org._2binstitute.discos.online.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Municipio {
	private Integer Id;
	private String nombre;
	private Estado estado;
}
