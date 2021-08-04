package br.com.planner.projectRegister.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.planner.projectRegister.FuncionarioDAO.CargoDao;
import br.com.planner.projectRegister.model.Cargo;

@FacesConverter ("cargoConverter")
public class CargoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String id) {
		CargoDao dao = new CargoDao();
		
		return dao.pesquisaIdCargo(Integer.parseInt(id));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object cargo) {

		return ((Cargo) cargo).getCodigo().toString();
	}
	
}
