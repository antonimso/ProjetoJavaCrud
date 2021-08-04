package br.com.planner.projectRegister.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.planner.projectRegister.FuncionarioDAO.PromocaoDAO;
import br.com.planner.projectRegister.model.Cargo;
import br.com.planner.projectRegister.model.Funcionario;

@ManagedBean(name = "promoBean")
@Named
@ViewScoped

public class PromocaoMB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Funcionario funcionario;
	private BigDecimal percentualAumento;
	private PromocaoDAO dao;
	private ArrayList<Funcionario> funcionarios;
	private Cargo cargo;

	
	@PostConstruct
	public void inicializarTela() {
		dao = new PromocaoDAO();
		funcionarios = dao.listarTodosFuncionarios();
		
		limpar();
	}
	
	public void limpar() {
		funcionario = new Funcionario();
	}
	
	public void aumentoPorCargo() {
		dao = new PromocaoDAO();
		
		funcionarios = dao.aumentoFuncionarios(funcionarios, percentualAumento);
		
	}
	
	public void filtrar() {
		//TODO: fazer validação referente as regras do negocio
		dao = new PromocaoDAO();
		funcionarios = dao.filtrarFuncionarios(cargo);
		
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public ArrayList<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public BigDecimal getPercentualAumento() {
		return percentualAumento;
	}

	public void setPercentualAumento(BigDecimal percentualAumento) {
		this.percentualAumento = percentualAumento;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	
	
	
}
