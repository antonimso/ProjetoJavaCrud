package br.com.planner.projectRegister.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.planner.projectRegister.FuncionarioDAO.CargoDao;
import br.com.planner.projectRegister.model.Cargo;

@FacesConverter("promoConverter")
public class PromoverConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String descricao) {
		CargoDao dao = new CargoDao();

		return dao.pesquisaDescricaoCargo(descricao);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object cargo) {
		
		return ((Cargo) cargo).getDescricao().toString();
	}

}
