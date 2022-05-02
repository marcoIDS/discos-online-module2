package org._2binstitute.discos.online.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
import org._2binstitute.discos.online.domain.Usuario;

public class JdbcUsuarioRepository implements UsuarioRepository {	
	private DataSource dataSource;
	
	public JdbcUsuarioRepository(DataSource dataSource) {
		this.dataSource = dataSource; 
	}
		
	@Override
	public Usuario insertar(Usuario usuario) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO usuario(nombre, primer_apellido, segundo_apellido, "
					+ "password, email, rfc) VALUES (?, ?, ?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, usuario.getNombre());
			pstmt.setString(2, usuario.getPrimerApellido());
			pstmt.setString(3, usuario.getSegundoApellido());
			pstmt.setString(4, usuario.getPassword());
			pstmt.setString(5, usuario.getEmail());
			pstmt.setString(6, usuario.getRfc());
			
			pstmt.executeUpdate();	
			rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				usuario.setId(rs.getInt(1));
			}
			
			return usuario;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null) {rs.close();}} catch (Exception e) {}
			try {if (pstmt != null) {pstmt.close();}} catch (Exception e) {}
			try {if (conn != null) {conn.close();}} catch (Exception e) {}
		}		
		return null;
	}

	@Override
	public Usuario buscarPorEmail(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Usuario usuario = null;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT id, email FROM usuario WHERE email  = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setEmail(rs.getString("email"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null) {rs.close();}} catch (Exception e) {}
			try {if (pstmt != null) {pstmt.close();}} catch (Exception e) {}
			try {if (conn != null) {conn.close();}} catch (Exception e) {}
		}
		
		return usuario;
	}

}
