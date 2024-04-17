package br.edu.fateczl.SpringWebUDF.persistence;

import java.sql.SQLException;
import java.util.List;

public interface IConsulta<T> {
	public T consultar(T t) throws SQLException, ClassNotFoundException;
	public List<T> listar() throws SQLException, ClassNotFoundException;
}
