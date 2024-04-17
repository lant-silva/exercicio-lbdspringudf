package br.edu.fateczl.SpringWebUDF.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.fateczl.SpringWebUDF.model.Dependente;

@Repository
public class DependenteDao implements IConsulta<Dependente>{
	private GenericDao gDao;
	
	@Override
	public Dependente consultar(Dependente d) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM dbo.fn_dependente(?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, d.getCodigo());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			d.setCodigo(rs.getInt("codigo"));
			d.setCodigoFuncionario(rs.getInt("codigo_funcionario"));
			d.setNome(rs.getString("nome"));
			d.setSalario(rs.getFloat("salario"));
		}
		rs.close();
		ps.close();
		c.close();
		return d;
	}

	@Override
	public List<Dependente> listar() throws SQLException, ClassNotFoundException {
		List<Dependente> dependentes = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM dbo.fn_dependente()";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Dependente d = new Dependente();
			d.setCodigo(rs.getInt("codigo"));
			d.setCodigoFuncionario(rs.getInt("codigo_funcionario"));
			d.setNome(rs.getString("nome"));
			d.setSalario(rs.getFloat("salario"));
			dependentes.add(d);
		}
		rs.close();
		ps.close();
		c.close();
		return dependentes;
	}
}
