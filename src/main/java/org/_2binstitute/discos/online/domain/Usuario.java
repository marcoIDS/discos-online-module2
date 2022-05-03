package org._2binstitute.discos.online.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Usuario {
	private Integer id;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private String password;
	private String email;
	private String rfc;
		
}
