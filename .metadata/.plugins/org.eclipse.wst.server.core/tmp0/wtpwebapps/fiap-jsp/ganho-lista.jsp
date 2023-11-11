<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ganho lista</title>
</head>
<body>
<br>
<a href="GanhoNovo">Criar um novo Ganho</a>

<table border="1">
 
        <tr>
            <th>Código do Ganho</th>
            <th>Tipo de Receita</th>
            <th>Código da Movimentação</th>
            <th>Código do Extrato</th>
            <th>Nome da Movimentação</th>
            <th>Descrição da Movimentação</th>
            <th>Valor da Movimentação (R$)</th>
            <th>Data da Movimentação</th>
            <th>Quantidade de Parcelas</th>
        </tr>
    
   
  <c:forEach items="${ganho}" var="ganho">
            <tr>
                <td><fmt:formatNumber value ="${ganho.get_cd_ganho}" type="currency"/></td>
                <td>${ganho.tp_receita}</td>
                <td>${ganho.cd_movimentacao}</td>
                <td>${ganho.cd_extrato}</td>
                <td>${ganho.nm_movimentacao}</td>
                <td>${ganho.ds_movimentacao}</td>
                <td>${ganho.vl_movimentacao}</td>
                <td><fmt:formatDate pattern = "dd/MM/yyyy" value="${ganho.dt_movimentacao}"/></td>
                <td><fmt:formatNumber value = "${ganho.qtd_parcelas}" type="currency"/></td>
                <td><a href="GanhoEditar?id=${ganho.cd_ganho}">Editar</a></td>

            </tr>
        </c:forEach>
 
</table>



</body>
</html>