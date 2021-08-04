package br.com.planner.projectRegister.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.planner.projectRegister.FuncionarioDAO.FuncionarioDAO;
import br.com.planner.projectRegister.model.Funcionario;

@ManagedBean(name = "bean")
@Named
@ViewScoped
public class FuncionarioMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Funcionario funcionario;
	private FuncionarioDAO dao;
	private ArrayList<Funcionario> funcionarios;
	private boolean alterar;

	@PostConstruct
	public void inicializarTela() {
		dao = new FuncionarioDAO();
		funcionarios = dao.listarTodosFuncionarios();
		limpar();
	}

	public void limpar() {
		funcionario = new Funcionario();
	}

	public void salvar() {
		// TODO: fazer validação referente as regras do negocio

		dao = new FuncionarioDAO();
		if (alterar) {
			dao.alterarFuncionario(funcionario);
		} else {
			dao.inserirFuncionario(funcionario);
		}
		inicializarTela();
		alterar = false;

	}

	public Funcionario pesquisaPorCargo(String nome) {
		dao = new FuncionarioDAO();
		dao.pesquisaFuncionario(funcionario, nome);

		return funcionario;

	}

	public void deletar(Funcionario f) {
		dao.excluiFuncionario(f);
		inicializarTela();
	}

	public void carregarFuncionario(Funcionario f) {
		funcionario = f;
		alterar = true;

	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = (ArrayList<Funcionario>) funcionarios;
	}

}
