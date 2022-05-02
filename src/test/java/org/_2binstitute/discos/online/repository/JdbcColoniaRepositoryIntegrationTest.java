package org._2binstitute.discos.online.repository;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org._2binstitute.discos.online.AppConfig;
import org._2binstitute.discos.online.domain.Colonia;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
public class JdbcColoniaRepositoryIntegrationTest {
	@Autowired
	private ColoniaRepository jdbcColoniaRepository;
	
	@Test
	public void buscarPorCp_ObtieneColonias() {
		//jdbcColoniaRepository = new JdbcColoniaRepository();
		String cp = "10000";
		List<Colonia> colonias = jdbcColoniaRepository.buscarPorCp(cp);
		assertTrue(!colonias.isEmpty());
	}
}
 