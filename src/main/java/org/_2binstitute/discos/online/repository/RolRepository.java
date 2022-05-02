package org._2binstitute.discos.online.repository;

import org._2binstitute.discos.online.domain.Rol;

public interface RolRepository {

	Rol buscarPorDescripcion(String descripcion);
}
