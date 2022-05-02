package org._2binstitute.discos.online.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;
import org._2binstitute.discos.online.domain.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRolRepository implements RolRepository{
	private DataSource dataSource;
	
	@Autowired
	public JdbcRolRepository(DataSource dataSource) {
		this.dataSource = dataSource; 
	}
	
	@Override
	public Rol buscarPorDescripcion(String descripcion) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Rol rol = null;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT id, descripcion FROM rol WHERE descripcion  = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, descripcion);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				rol = new Rol();
				rol.setId(rs.getInt("id"));
				rol.setDescripcion(rs.getString("descripcion"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null) {rs.close();}} catch (Exception e) {}
			try {if (pstmt != null) {pstmt.close();}} catch (Exception e) {}
			try {if (conn != null) {conn.close();}} catch (Exception e) {}
		}
		
		return rol;
	}

}
