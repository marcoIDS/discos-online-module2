package org._2binstitute.discos.online.repository;
import org._2binstitute.discos.online.domain.TipoDomicilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTipoDomicilioRepository implements TipoDomicilioRepository {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcTipoDomicilioRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public TipoDomicilio buscarPorDescripcion(String descripcion) {
		try {			
			String sql = "SELECT id, descripcion FROM tipo_domicilio WHERE descripcion  = ?";
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<TipoDomicilio>(TipoDomicilio.class), descripcion);		
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
}
