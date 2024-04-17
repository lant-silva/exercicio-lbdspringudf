package br.edu.fateczl.SpringWebUDF.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.fateczl.SpringWebUDF.model.Funcionario;

@Repository
public class FuncionarioDao implements IConsulta<Funcionario>{
	private GenericDao gDao;
	
	public FuncionarioDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public Funcionario consultar(Funcionario f) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM dbo.fn_funcionario() WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, f.getCodigo());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			f.setCodigo(rs.getInt("codigo"));
			f.setNome(rs.getString("nome"));
			f.setSalario(rs.getFloat("salario"));
		}
		rs.close();
		ps.close();
		c.close();
		return f;
	}

	@Override
	public List<Funcionario> listar() throws SQLException, ClassNotFoundException {
		List<Funcionario> funcionarios = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM dbo.fn_funcionario()";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Funcionario f = new Funcionario();
			f.setCodigo(rs.getInt("codigo"));
			f.setNome(rs.getString("nome"));
			f.setSalario(rs.getFloat("salario"));
			funcionarios.add(f);
		}
		rs.close();
		ps.close();
		c.close();
		return funcionarios;
	}
}
