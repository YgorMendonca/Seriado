package br.com.faculdadedelta.converter;

import java.sql.SQLException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.dao.GeneroDAO;
import br.com.faculdadedelta.modelo.Genero;

@FacesConverter(value = "generoConverter")
public class GeneroConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		
		if (valor != null) {
			GeneroDAO dao = new GeneroDAO();
			try {
				return dao.pesquisarPorId(Long.valueOf(valor));
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		
		if (valor != null) {
			return String.valueOf(((Genero)valor).getId());
		}
		
		return null;
	}

}