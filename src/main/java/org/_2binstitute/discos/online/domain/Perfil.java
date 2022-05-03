package org._2binstitute.discos.online.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Perfil {
	private final Usuario usuario;
	private final Rol rol;
}
