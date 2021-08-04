package br.com.planner.projectRegister.FuncionarioDAO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.planner.projectRegister.jdbc.ConexaoJDBC;
import br.com.planner.projectRegister.model.Cargo;
import br.com.planner.projectRegister.model.Funcionario;

public class PromocaoDAO {
	private final String SQL_SELECIONA_FUNCIONARIO = "SELECT * FROM FUNCIONARIO;";
	private final String SQL_PROCURA_CARGO_FUNCIONARIO = "SELECT * FROM FUNCIONARIO WHERE CARGO=?;";
	private final String SQL_AUMENTO_FUNCIONARIO = "UPDATE FUNCIONARIO SET SALARIO=? WHERE ID=?;";

	private PreparedStatement pst = null;

	public ArrayList<Funcionario> listarTodosFuncionarios() {
		ArrayList<Funcionario> listaDeFuncionarios = new ArrayList<Funcionario>();
		Connection conn = ConexaoJDBC.conectar();
		Funcionario umFunci = new Funcionario();
		CargoDao dao = new CargoDao();
		try {
			pst = conn.prepareStatement(SQL_SELECIONA_FUNCIONARIO);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Cargo cargo = new Cargo();
				cargo = dao.pesquisaIdCargo(rs.getInt("CARGO"));
				umFunci = new Funcionario();
				umFunci.setId(rs.getInt("ID"));
				umFunci.setNome(rs.getString("NOME"));
				umFunci.setMatricula(rs.getString("MATRICULA"));
				umFunci.setCargo(cargo);
				umFunci.setSalario(rs.getBigDecimal("SALARIO").setScale(4, RoundingMode.DOWN));
				listaDeFuncionarios.add(umFunci);
			}
			pst.executeQuery();
			rs.close();
			pst.close();
			ConexaoJDBC.fecharCNX();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Erro ao executar o Statement" + e.toString());
		}
		return listaDeFuncionarios;
	}
	
	public ArrayList<Funcionario> aumentoFuncionarios(ArrayList<Funcionario> funcionarios, BigDecimal percentual) {
		Connection conn = ConexaoJDBC.conectar();
		try {
			pst = conn.prepareStatement(SQL_AUMENTO_FUNCIONARIO);
			for (Funcionario umFunci : funcionarios) {
				BigDecimal novoSalario = umFunci.getSalario().add(umFunci.getSalario().multiply(percentual.divide(new BigDecimal(100))));
				umFunci.setSalario(novoSalario);
				pst.setBigDecimal(1, novoSalario);
				pst.setInt(2, umFunci.getId());
				pst.executeUpdate();
				
			}
			pst.close();
			ConexaoJDBC.fecharCNX();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Erro ao executar o Statement" + e.toString());
		}
		return funcionarios;
	}
	
	public ArrayList<Funcionario> filtrarFuncionarios(Cargo cargo) {
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		Connection conn = ConexaoJDBC.conectar();
		Funcionario umFunci = new Funcionario();

		try {
			pst = conn.prepareStatement(SQL_PROCURA_CARGO_FUNCIONARIO);
			
			pst.setInt(1, cargo.getCodigo());
			pst.executeQuery();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				umFunci = new Funcionario();
				umFunci.setId(rs.getInt("ID"));
				umFunci.setNome(rs.getString("NOME"));
				umFunci.setMatricula(rs.getString("MATRICULA"));
				umFunci.setCargo(cargo);
				umFunci.setSalario(rs.getBigDecimal("SALARIO"));
				funcionarios.add(umFunci);
			}
			pst.close();
			ConexaoJDBC.fecharCNX();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Erro ao executar o Statement" + e.toString());
		}
		return funcionarios;
	}

}
