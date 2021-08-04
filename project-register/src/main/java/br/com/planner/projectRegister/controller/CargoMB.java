package br.com.planner.projectRegister.controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.com.planner.projectRegister.FuncionarioDAO.CargoDao;
import br.com.planner.projectRegister.model.Cargo;

@ManagedBean(name = "beanc")
@ApplicationScoped
public class CargoMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Cargo cargo;
	private CargoDao dao;
	private ArrayList<Cargo> cargos;
	
	@PostConstruct
	public void inicializarTela() {
		dao = new CargoDao();
		cargos = dao.listarTodosCargos();
		limpar();
		
	}

	private void limpar() {
		cargo = new Cargo();
	}
	
	public void salvar() {
		//TODO: fazer validação referente as regras do negocio

		dao = new CargoDao();
		dao.inserirCargo(cargo);
		
		inicializarTela();
	}
	
	public void deletar(Cargo c) {
		dao.excluiCargo(c);
		inicializarTela();
	}
	
	
	
	public void carregarCargo(Cargo c) {
		cargo = c;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public ArrayList<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(ArrayList<Cargo> cargos) {
		this.cargos = cargos;
	}
	
	
	
}
