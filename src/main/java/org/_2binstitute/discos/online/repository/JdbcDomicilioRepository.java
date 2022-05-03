package org._2binstitute.discos.online.repository;
import java.util.HashMap;
import java.util.Map;

import org._2binstitute.discos.online.domain.Domicilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcDomicilioRepository implements DomicilioRepository{
	private SimpleJdbcInsert jdbcInsert;
	
	@Autowired
	public JdbcDomicilioRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("domicilio").usingGeneratedKeyColumns("id"); 
	}
	
	@Override
	public Domicilio insertar(Domicilio domicilio) {
		Map<String, Object> values = new HashMap<>();
		values.put("calle", domicilio.getCalle());
		values.put("num_exterior", domicilio.getNumExterior());
		values.put("num_interior", domicilio.getNumInterior());
		values.put("id_usuario", domicilio.getUsuario().getId());
		values.put("id_colonia", domicilio.getColonia().getId());
		values.put("id_tipo_domicilio", domicilio.getTipoDomicilio().getId());
		
		Integer idDomicilio = jdbcInsert.executeAndReturnKey(values).intValue();		
		domicilio.setId(idDomicilio);
		
		return domicilio;
	}

}
