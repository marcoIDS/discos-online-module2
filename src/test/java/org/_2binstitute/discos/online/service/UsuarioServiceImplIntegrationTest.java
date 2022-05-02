package org._2binstitute.discos.online.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.UUID;

import org._2binstitute.discos.online.AppConfig;
import org._2binstitute.discos.online.domain.Colonia;
import org._2binstitute.discos.online.domain.Domicilio;
import org._2binstitute.discos.online.domain.TipoDomicilio;
import org._2binstitute.discos.online.domain.Usuario;
import org._2binstitute.discos.online.repository.ColoniaRepository;
import org._2binstitute.discos.online.repository.JdbcColoniaRepository;
import org._2binstitute.discos.online.repository.JdbcTipoDomicilioRepository;
import org._2binstitute.discos.online.repository.TipoDomicilioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
public class UsuarioServiceImplIntegrationTest {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ColoniaRepository coloniaRepository;
	@Autowired
	private TipoDomicilioRepository tipoDomicilioRepository;
	
	@Test
	public void registrarUsuario_CreaUsuario() {
		String email = "test" + UUID.randomUUID().getLeastSignificantBits() + "@gmail.com";
		Usuario usuario = new Usuario();
		usuario.setNombre("TestNombre");
		usuario.setPrimerApellido("TestPrimerApellido");
		usuario.setSegundoApellido("TestSegundoApellido");
		usuario.setPassword("TestPassword");
		usuario.setEmail(email);
		usuario.setRfc("TestRFC");
		
		Domicilio domicilio = new Domicilio();
		domicilio.setCalle("TestCalle");
		domicilio.setNumExterior("TestNumExterior");
		domicilio.setNumInterior("TestNumeroInterior");
		
		String cp = "11000";
		List<Colonia> colonias = coloniaRepository.buscarPorCp(cp);
		domicilio.setColonia(colonias.get(0));
		
		TipoDomicilio tipoDomicilio = tipoDomicilioRepository.buscarPorDescripcion("ENTREGA");
		domicilio.setTipoDomicilio(tipoDomicilio);
		
		usuarioService.registrarUsuario(usuario, domicilio);
		
		assertNotNull(usuario.getId());
		
	}
}
