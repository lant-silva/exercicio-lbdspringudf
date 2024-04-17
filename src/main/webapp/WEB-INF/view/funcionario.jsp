<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Funcionario</title>
<link rel="stylesheet" type="text/css" href='<c:url value = "./resources/css/styles.css"/>'>
<header>
	<h1 align="center">Exercicio Spring - Funcionarios</h1>
	<div>
		<jsp:include page="menu.jsp" />
	</div>
</header>
</head>
<body>
	<div>
		<div align="center" class="container">
			<form action="funcionario" method="post">
				<table>
					<tr>
						<td colspan="4"><input class="input_data" type="number"
							min="0" id="codigo" name="codigo" placeholder="Código Funcionario" 
							value='<c:out value="${funcionario.codigo}"></c:out>'>
						<td />
						<td><input type="submit" id="botao" name="botao"
							value="Buscar">
						<td />
					<tr />
					
					<tr>
						<td colspan="4"><input class="input_data" type="text"
							maxlength="100" id="nome" name="nome" placeholder="Nome"
							value='<c:out value="${funcionario.nome}"></c:out>'>
					</tr>
					
					<tr>
						<td colspan="4"><input class="input_data" type="number"
							min="0" id="salario" name="salario" placeholder="Salário" 
							value='<c:out value="${funcionario.salario}"></c:out>'>
						<td />
					</tr>
					<tr>
						<td><input type="submit" id="botao" name="botao"
							value="Listar Funcionarios"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<br />
	<div align="center">
		<c:if test="${not empty saida }">
			<H2>
				<b><c:out value="${saida }" /></b>
			</H2>
		</c:if>
	</div>
	<br />
	<div align="center">
		<c:if test="${not empty erro }">
			<H2>
				<b><c:out value="${erro }" /></b>
			</H2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty funcionarios }">
			<table class="table-round">
				<thead>
					<tr>
						<th>Codigo</th>
						<th>Nome</th>
						<th>Salario</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="f" items="${funcionarios }">
						<td><c:out value="${f.codigo}" /></td>
						<td><c:out value="${f.nome}" /></td>
						<td><c:out value="${f.salario}" /></td>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>