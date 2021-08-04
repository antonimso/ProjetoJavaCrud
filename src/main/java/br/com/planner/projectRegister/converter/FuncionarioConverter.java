package br.com.planner.projectRegister.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.planner.projectRegister.FuncionarioDAO.FuncionarioDAO;
import br.com.planner.projectRegister.model.Funcionario;

@FacesConverter ("funcionarioConverter")
public class FuncionarioConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String nome) {
		Funcionario funcionario = new Funcionario();
		FuncionarioDAO dao = new FuncionarioDAO();
		return dao.pesquisaFuncionario(funcionario, nome);
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object funcionario) {
		// TODO Auto-generated method stub
		return ((Funcionario) funcionario).getNome();
	}
	
}
