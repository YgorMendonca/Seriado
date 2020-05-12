package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.Status;
import br.com.faculdadedelta.util.ConexaoYgorMendonca;

public class StatusDAO {

	public void incluir(Status status) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "INSERT INTO status (descricao) VALUES (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, status.getDescStatus().trim());
		ps.executeUpdate();
		
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
	}
	
	public void alterar(Status status) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "UPDATE status SET descricao = ?  WHERE id_status = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, status.getDescStatus().trim());
		ps.setLong(2, status.getId());
		
		ps.executeUpdate();
		
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
	}
	
	public void excluir(Status status) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "DELETE FROM status WHERE id_status = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setLong(1, status.getId());
		
		ps.executeUpdate();
		
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
		
	}
	
	public Status pesquisarPorId(Long id) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "SELECT id_status, descricao FROM status WHERE id_status = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		
		ResultSet rs = ps.executeQuery();
		Status retorno = new Status();
		if (rs.next()) {
			retorno = popularStatus(rs);
		}
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
		
		return retorno;
	}
	
	
	public List<Status> listar() 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "SELECT id_status, descricao FROM status";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		List<Status> listaRetorno = new ArrayList<>();
		
		while(rs.next()) {
			listaRetorno.add(popularStatus(rs));
		}
		
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
		
		return listaRetorno;
	}
	
	private Status popularStatus(ResultSet rs) throws SQLException {
		
		Status status = new Status();
		
		status.setId(rs.getLong("id_status"));
		status.setDescStatus(rs.getString("descricao").trim());
				
		return status;
	}
}