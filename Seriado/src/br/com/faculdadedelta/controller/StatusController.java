package br.com.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.faculdadedelta.dao.StatusDAO;
import br.com.faculdadedelta.modelo.Status;
import br.com.faculdadedelta.util.FacesUtil;

@ManagedBean
@SessionScoped
public class StatusController {

	Status status = new Status();
	StatusDAO dao = new StatusDAO();
	
	private static final String PAGINA_CADASTRO_STATUS = "cadastroStatus.xhtml";
	private static final String PAGINA_LISTA_STATUS= "listaStatus.xhtml";
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void limparCampos() {
		status = new Status();
	}
	
	public String salvar() {
		try {
			if (status.getId() == 0) {
				dao.incluir(status);
				FacesUtil.exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				dao.alterar(status);
				FacesUtil.exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return PAGINA_CADASTRO_STATUS;
	}
	
	public String excluir() {
		try {
			dao.excluir(status);
			FacesUtil.exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a operação: " + e.getMessage());			
		}
		return PAGINA_LISTA_STATUS;
	}
	
	public String editar() {
		return PAGINA_CADASTRO_STATUS;
	}
	
	public List<Status> getLista() {
		List<Status> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.exibirMensagem("Erro ao realizar a operação: " + e.getMessage());			
		}
		return listaRetorno;
	}
}