package br.com.planner.projectRegister.FuncionarioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.planner.projectRegister.jdbc.ConexaoJDBC;
import br.com.planner.projectRegister.model.Cargo;

public class CargoDao {
	private final String SQL_INSERE_CARGO = "INSERT INTO \"PUBLIC\".\"CARGO\""
			+ "(\"CODIGO\", \"DESCRICAO\") VALUES(?, ?);";
	private final String SQL_ALTERA_CARGO = "UPDATE CARGO SET DESCRICAO=? WHERE ID=?;";
	private final String SQL_EXCLUI_CARGO = "DELETE FROM CARGO WHERE ID=?;";
	private final String SQL_SELECIONA_CARGO = "SELECT * FROM CARGO;";
	private final String SQL_PROCURA_CODIGO_CARGO = "SELECT * FROM CARGO WHERE CODIGO=?;";
	private final String SQL_PROCURA_DESCRICAO_CARGO = "SELECT * FROM CARGO WHERE DESCRICAO=?;";


	
	private PreparedStatement pst = null;

	public boolean inserirCargo(Cargo umCargo) {
		boolean ret = false;
		Connection conn = ConexaoJDBC.conectar();
		try {
			pst = conn.prepareStatement(SQL_INSERE_CARGO);
			pst.setInt(1, umCargo.getCodigo());
			pst.setString(2, umCargo.getDescricao());
			ret = pst.execute();
			pst.close();
			ConexaoJDBC.fecharCNX();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		return ret;
	}

	public ArrayList<Cargo> listarTodosCargos() {
		ArrayList<Cargo> listaDeCargos = new ArrayList<Cargo>();
		Connection conn = ConexaoJDBC.conectar();
		Cargo umCargo;
		try {
			pst = conn.prepareStatement(SQL_SELECIONA_CARGO);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				umCargo = new Cargo();
				umCargo.setCodigo(rs.getInt("CODIGO"));
				umCargo.setDescricao(rs.getString("DESCRICAO"));
				listaDeCargos.add(umCargo);
			}
			pst.executeQuery();
			rs.close();
			pst.close();
			ConexaoJDBC.fecharCNX();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Erro ao executar o Statement" + e.toString());
		}
		return listaDeCargos;
	}

	public boolean alterarCargo(Cargo umCargo) {
		boolean ret = false;
		Connection conn = ConexaoJDBC.conectar();
		try {
			pst = conn.prepareStatement(SQL_ALTERA_CARGO);
			pst.setInt(1, umCargo.getCodigo());
			pst.setString(2, umCargo.getDescricao());
			ret = pst.execute();
			pst.close();
			ConexaoJDBC.fecharCNX();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		return ret;
	}

	public boolean excluiCargo(Cargo umCargo) {
		boolean ret = false;
		Connection conn = ConexaoJDBC.conectar();
		try {
			pst = conn.prepareStatement(SQL_EXCLUI_CARGO);
			pst.setInt(1, umCargo.getCodigo());
			ret = pst.execute();
			pst.close();
			ConexaoJDBC.fecharCNX();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		return ret;

	}

	public Cargo pesquisaIdCargo(int id) {
		Connection conn = ConexaoJDBC.conectar();
		Cargo umCargo = new Cargo();
		try {
			pst = conn.prepareStatement(SQL_PROCURA_CODIGO_CARGO);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				umCargo.setCodigo(rs.getInt("CODIGO"));
				umCargo.setDescricao(rs.getString("DESCRICAO"));
			}
			pst.executeQuery();
			rs.close();
			pst.close();
			ConexaoJDBC.fecharCNX();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Erro ao executar o Statement" + e.toString());
		}
		return umCargo;

	}
	
	public Cargo pesquisaDescricaoCargo(String descricao) {
		Connection conn = ConexaoJDBC.conectar();
		Cargo umCargo = new Cargo();
		try {
			pst = conn.prepareStatement(SQL_PROCURA_DESCRICAO_CARGO);
			pst.setString(1, descricao);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				umCargo.setCodigo(rs.getInt("CODIGO"));
				umCargo.setDescricao(rs.getString("DESCRICAO"));
			}
			pst.executeQuery();
			rs.close();
			pst.close();
			ConexaoJDBC.fecharCNX();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Erro ao executar o Statement" + e.toString());
		}
		return umCargo;

	}
}
