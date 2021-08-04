 package br.com.planner.projectRegister.FuncionarioDAO;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.planner.projectRegister.jdbc.ConexaoJDBC;
import br.com.planner.projectRegister.model.Cargo;
import br.com.planner.projectRegister.model.Funcionario;

public class FuncionarioDAO {
	private final String SQL_INSERE_FUNCIONARIO = "INSERT INTO \"PUBLIC\".\"FUNCIONARIO\""
			+ "(\"NOME\", \"MATRICULA\", \"CARGO\", \"SALARIO\" ) VALUES(?, ?, ?, ?);";
	private final String SQL_ALTERA_FUNCIONARIO = "UPDATE FUNCIONARIO SET NOME=?, MATRICULA=?, CARGO=?, SALARIO=? WHERE ID=?;";
	private final String SQL_EXCLUI_FUNCIONARIO = "DELETE FROM FUNCIONARIO WHERE ID=?;";
	private final String SQL_SELECIONA_FUNCIONARIO = "SELECT * FROM FUNCIONARIO;";
	private final String SQL_PROCURA_CARGO_FUNCIONARIO = "SELECT FROM FUNCIONARIO WHERE CARGO=?;";	
	
	private PreparedStatement pst = null;

	public boolean inserirFuncionario(Funcionario umFunc) {
		boolean ret = false;
		Connection conn = ConexaoJDBC.conectar();
		try {
			pst = conn.prepareStatement(SQL_INSERE_FUNCIONARIO);
			pst.setString(1, umFunc.getNome());
			pst.setString(2, umFunc.getMatricula());
			pst.setLong(3, umFunc.getCargo().getCodigo());
			pst.setBigDecimal(4, umFunc.getSalario());
			ret = pst.execute();
			pst.close();
			ConexaoJDBC.fecharCNX();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		return ret;
	}

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

	public boolean alterarFuncionario(Funcionario umFunc) {
		boolean ret = false;
		Connection conn = ConexaoJDBC.conectar();
		try {
			pst = conn.prepareStatement(SQL_ALTERA_FUNCIONARIO);
			
			pst.setString(1, umFunc.getNome());
			pst.setString(2, umFunc.getMatricula());
			pst.setLong(3, umFunc.getCargo().getCodigo());
			pst.setBigDecimal(4, umFunc.getSalario());
			pst.setInt(5, umFunc.getId());
			pst.executeUpdate();
			pst.close();
			ConexaoJDBC.fecharCNX();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		return ret;
	}

	public boolean excluiFuncionario(Funcionario umFunc) {
		boolean ret = false;
		Connection conn = ConexaoJDBC.conectar();
		try {
			pst = conn.prepareStatement(SQL_EXCLUI_FUNCIONARIO);
			pst.setInt(1, umFunc.getId());
			ret = pst.execute();
			pst.close();
			ConexaoJDBC.fecharCNX();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		return ret;

	}

	public boolean pesquisaFuncionario(Funcionario umFunc, String nome) {
		boolean ret = false;
		Connection conn = ConexaoJDBC.conectar();
		try {
			pst = conn.prepareStatement(SQL_PROCURA_CARGO_FUNCIONARIO);
			pst.setString(1, umFunc.getNome());
			ret = pst.execute();
			pst.close();
			ConexaoJDBC.fecharCNX();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		return ret;

	}

}
