package br.com.planner.projectRegister.controller;

import java.sql.Connection;
import java.util.ArrayList;

import br.com.planner.projectRegister.FuncionarioDAO.CargoDao;
import br.com.planner.projectRegister.FuncionarioDAO.FuncionarioDAO;
import br.com.planner.projectRegister.jdbc.ConexaoJDBC;
import br.com.planner.projectRegister.model.Cargo;
import br.com.planner.projectRegister.model.Funcionario;

public class PrAplication {

	public static void main(String[] args) {
		
		Connection c = ConexaoJDBC.conectar();
		
		if(c == null) {
			System.out.println("Não conectado Sir!");
			
		}else {
			System.out.println("Conectado!");
			//Testes iniciais com o entity
//			EntityManagerFactory emf = Persistence.createEntityManagerFactory("minha-persistence-unit");
//			
//			EntityManager em = emf.createEntityManager();
//			
//			List<Funcionario> lista = em.createQuery("FROM Funcionario", Funcionario.class).getResultList();
//			
//			for(Funcionario objeto : lista) {
//				System.out.println("Funcionário(a) de matricula: " + objeto.getMatricula() + ", " + objeto.getNome());
//				System.out.println("Ganha: " + objeto.getSalario() + " e tem cargo de " + objeto.getCargo());
//			}
//			
//			Funcionario func = new Funcionario();
//			func.setCargo("Estagiária Junior");
//			func.setMatricula("20173526");
//			func.setNome("Tamires");
//			func.setSalario(1000.00);
			
			
//			em.getTransaction().begin();
//			em.persist(func);
//			em.getTransaction().commit();	
//			
//			em.close();
//			emf.close();
			
			//FuncionarioDAO funcDAO = new FuncionarioDAO();
			CargoDao carDao = new CargoDao();
			
//			Funcionario funci = new Funcionario();
//			funci.setNome("Kefnet");
//			funci.setMatricula("85554454");
//			funci.setCargo("One of the five Gods");
//			funci.setSalario(9000.00);
			Cargo carg = new Cargo();
			carg.setCodigo(1);
			//carg.setDescrição("Estágiario Sênior");
			
//			funcDAO.inserirFuncionario(funci);
			carDao.inserirCargo(carg);
			
//			ArrayList<Funcionario> listFuncionarios = (ArrayList<Funcionario>) funcDAO.listarTodosFuncionarios();
//			for(Funcionario umFuncionario : listFuncionarios) {
//				System.out.println(umFuncionario.toString());
//			}
			ArrayList<Cargo> listaCargos = (ArrayList<Cargo>) carDao.listarTodosCargos();
			for (Cargo umCargo : listaCargos) {
				System.out.println(umCargo.toString());
			}
		}
				
	}

}
