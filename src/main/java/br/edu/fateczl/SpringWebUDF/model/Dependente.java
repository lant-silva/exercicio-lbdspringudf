package br.edu.fateczl.SpringWebUDF.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dependente{
	int codigo;
	int codigoFuncionario;
	String nome;
	float salario;
}
