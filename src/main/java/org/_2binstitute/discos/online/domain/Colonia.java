package org._2binstitute.discos.online.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Colonia {
	private Integer Id;
	private String name;
	private String cp;
	private Municipio municipio;
	
}
