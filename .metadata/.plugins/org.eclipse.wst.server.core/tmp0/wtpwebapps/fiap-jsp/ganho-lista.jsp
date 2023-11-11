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
            <th>C�digo do Ganho</th>
            <th>Tipo de Receita</th>
            <th>C�digo da Movimenta��o</th>
            <th>C�digo do Extrato</th>
            <th>Nome da Movimenta��o</th>
            <th>Descri��o da Movimenta��o</th>
            <th>Valor da Movimenta��o (R$)</th>
            <th>Data da Movimenta��o</th>
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