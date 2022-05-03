package org._2binstitute.discos.online.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org._2binstitute.discos.online.AppConfig;
import org._2binstitute.discos.online.domain.Rol;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfig.class })
public class JdbcRolRepositoryIntegrationTest {
	
	@Autowired
	private RolRepository jdbcRolRepository;
	
	@Test
	public void buscarPorDescripcion_ObtieneRol() {
		String descripcion = "CLIENTE";
		Rol rol = jdbcRolRepository.buscarPorDescripcion(descripcion);
		assertNotNull(rol);
	}
	
	@Test
	private void  buscarPorDescripcion_NoObtieneRol() {
		String descripcion = "ROL_INEXISTENTE";
		Rol rol = jdbcRolRepository.buscarPorDescripcion(descripcion);
		assertNull(rol);
	}
}
