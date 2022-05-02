package org._2binstitute.discos.online.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;
import org._2binstitute.discos.online.domain.Perfil;

public class JdbcPerfilRepository implements PerfilRepository {
	private DataSource dataSource;
	
	public JdbcPerfilRepository(DataSource dataSource) {
		this.dataSource = dataSource; 
	}
	
	@Override
	public Perfil insertar(Perfil perfil) {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO perfil(id_usuario, id_rol) VALUES (?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, perfil.getUsuario().getId());
			pstmt.setInt(2, perfil.getRol().getId());
			
			pstmt.executeUpdate();	
			return perfil;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {if (pstmt != null) {pstmt.close();}} catch (Exception e) {}
			try {if (conn != null) {conn.close();}} catch (Exception e) {}
		}		
		return null;
	}

}
