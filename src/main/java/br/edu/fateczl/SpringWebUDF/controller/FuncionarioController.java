package br.edu.fateczl.SpringWebUDF.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.SpringWebUDF.model.Funcionario;
import br.edu.fateczl.SpringWebUDF.persistence.FuncionarioDao;
import br.edu.fateczl.SpringWebUDF.persistence.GenericDao;

@Controller
public class FuncionarioController {
	
	@Autowired
	GenericDao gDao;
	
	@Autowired
	FuncionarioDao fDao;
	
	@RequestMapping(name="funcionario", value="/funcionario", method = RequestMethod.GET)
	public ModelAndView funcionarioGet(ModelMap model) {
		return new ModelAndView("funcionario");
	}
	
	@RequestMapping(name="funcionario", value="/funcionario", method = RequestMethod.POST)
	public ModelAndView funcionarioPost(@RequestParam Map<String, String> allRequestParam, ModelMap model) {
		String cmd = allRequestParam.get("botao");
		String codigo = allRequestParam.get("codigo");
		String nome = allRequestParam.get("nome");
		String salario = allRequestParam.get("salario");
		
		String saida = "";
		String erro = "";
		Funcionario f =  new Funcionario();
		List<Funcionario> funcionarios = new ArrayList<>();
		
		if(!cmd.contains("Listar")) {
			f.setCodigo(Integer.parseInt(codigo));
		}
		try {
			if(cmd.contains("Buscar")) {
				f = buscarFuncionario(f);
			}
			if(cmd.contains("Listar")) {
				funcionarios = listarFuncionarios();
			}
		} catch(SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
			model.addAttribute("funcionario", f);
			model.addAttribute("funcionarios", funcionarios);
		}
		return new ModelAndView("funcionario");
	}

	private Funcionario buscarFuncionario(Funcionario f) throws ClassNotFoundException, SQLException {
		f = fDao.consultar(f);
		return f;
	}

	private List<Funcionario> listarFuncionarios() throws ClassNotFoundException, SQLException {
		List<Funcionario> funcionarios = fDao.listar();
		return funcionarios;
	}
	
}
