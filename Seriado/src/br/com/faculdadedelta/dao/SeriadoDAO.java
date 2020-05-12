package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.Status;
import br.com.faculdadedelta.modelo.Seriado;
import br.com.faculdadedelta.modelo.Genero;
import br.com.faculdadedelta.util.ConexaoYgorMendonca;

public class SeriadoDAO {

	public void incluir(Seriado seriado) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "INSERT INTO series (id_status, id_genero, nome, comentario, nota_avaliacao) VALUES (?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, seriado.getStatus().getId());
		ps.setLong(2, seriado.getGenero().getId());
		ps.setString(3, seriado.getNome().trim());
		ps.setString(4, seriado.getComentario().trim());
		ps.setDouble(5, seriado.getNotaAvaliacao());
		
				
		ps.executeUpdate();
		
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
	}
	
	public void alterar(Seriado seriado) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "UPDATE series SET id_status = ?, id_genero = ?, nome = ?, comentario = ?, nota_avaliacao = ?  WHERE id_serie = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setLong(1, seriado.getStatus().getId());
		ps.setLong(2, seriado.getGenero().getId());
		ps.setString(3, seriado.getNome().trim());
		ps.setString(4, seriado.getComentario().trim());
		ps.setDouble(5, seriado.getNotaAvaliacao());
		ps.setLong(6, seriado.getId());
		
		ps.executeUpdate();
		
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
	}
	
	public void excluir(Seriado seriado) 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "DELETE FROM series WHERE id_serie = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, seriado.getId());
		
		ps.executeUpdate();
		
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
		
	}
		
	public List<Seriado> listar() 
			throws ClassNotFoundException, SQLException {
		Connection conn = ConexaoYgorMendonca.conectarNoBancoDeDados();
		String sql = "SELECT "
				+ " s.id_serie AS idSerie, "
				+ " s.nome AS nomeSerie, "
				+ " s.comentario AS comentarioSerie, "
				+ " s.nota_avaliacao AS notaAvaliacaoSerie, "
				+ " st.id_status AS idStatus, "
				+ " st.descricao AS descricaoStatus, "
				+ " g.id_genero AS idGenero, "
				+ " g.descricao AS descricaoGenero "
				+ " FROM series s INNER JOIN status st ON s.id_status = st.id_status "
				+ " INNER JOIN genero g ON s.id_genero = g.id_genero ";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Seriado> listaRetorno = new ArrayList<>();
		while (rs.next()) {
			
			Seriado seriado = new Seriado();
			seriado.setId(rs.getLong("idSerie"));
			seriado.setNome(rs.getString("nomeSerie"));
			seriado.setComentario(rs.getString("comentarioSerie"));
			seriado.setNotaAvaliacao(rs.getDouble("notaAvaliacaoSerie"));
						
			Status status = new Status();
			status.setId(rs.getLong("idStatus"));
			status.setDescStatus(rs.getString("descricaoStatus").trim());
			
			Genero genero = new Genero();
			genero.setId(rs.getLong("idGenero"));
			genero.setDescGenero(rs.getString("descricaoGenero").trim());
			
		    seriado.setStatus(status);
		    seriado.setGenero(genero);
		  		    
		    listaRetorno.add(seriado);
		}
		
		ConexaoYgorMendonca.fecharConexao(conn, ps, null);
		
		return listaRetorno;
	}
	
}