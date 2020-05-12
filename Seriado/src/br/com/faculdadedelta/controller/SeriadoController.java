package br.com.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.faculdadedelta.dao.SeriadoDAO;
import br.com.faculdadedelta.modelo.Status;
import br.com.faculdadedelta.modelo.Seriado;
import br.com.faculdadedelta.modelo.Genero;
import br.com.faculdadedelta.util.FacesUtil;

@ManagedBean
@SessionScoped
public class SeriadoController {

	Seriado seriado = new Seriado();
	SeriadoDAO dao = new SeriadoDAO();
	Status statusSelecionado = new Status();
	Genero generoSelecionado = new Genero();
	
	private static final String PAGINA_CADASTRO_SERIADO = "cadastroSeriado.xhtml";
	private static final String PAGINA_LISTA_SERIADO = "listaSeriado.xhtml";
	
	public Seriado getSeriado() {
		return seriado;
	}

	public void setSeriado(Seriado seriado) {
		this.seriado = seriado;
	}

	public Status getStatusSelecionado() {
		return statusSelecionado;
	}

	public void setStatusSelecionado(Status statusSelecionado) {
		this.statusSelecionado = statusSelecionado;
	}

	public Genero getGeneroSelecionado() {
		return generoSelecionado;
	}

	public void setGeneroSelecionado(Genero generoSelecionado) {
		this.generoSelecionado = generoSelecionado;
	}

	public void limparCampos() {
		seriado = new Seriado();
		statusSelecionado = new Status();
		generoSelecionado = new Genero();
	}

	public String salvar() {
		try {
			if (seriado.getId() == 0) {
				seriado.setStatus(statusSelecionado);
				seriado.setGenero(generoSelecionado);
				dao.incluir(seriado);
				FacesUtil.exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				seriado.setStatus(statusSelecionado);
				seriado.setGenero(generoSelecionado);
				dao.alterar(seriado);
				FacesUtil.exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a operação. " + e.getMessage());
		}

		return PAGINA_CADASTRO_SERIADO;
	}

	public String excluir() {
		try {
			dao.excluir(seriado);
			FacesUtil.exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a operação. " + e.getMessage());
		}
		return PAGINA_LISTA_SERIADO;
	}

	public String editar() {
		statusSelecionado = seriado.getStatus();
		generoSelecionado = seriado.getGenero();
		
		return PAGINA_CADASTRO_SERIADO;
	}

	public List<Seriado> getLista() {
		List<Seriado> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a operação. " + e.getMessage());
		}
		return listaRetorno;
	}
}