package org._2binstitute.discos.online.repository;
import org._2binstitute.discos.online.domain.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcPerfilRepository implements PerfilRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcPerfilRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Perfil insertar(Perfil perfil) {		
		String sql = "INSERT INTO perfil(id_usuario, id_rol) VALUES (?, ?) ";
		jdbcTemplate.update(sql,perfil.getUsuario().getId(), perfil.getRol().getId());
		return perfil;
	}

}
