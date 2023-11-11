<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insira o Novo Ganho</title>
</head>
<body>
<form action="#" method="post">
    Código do Ganho: <input type="text" id="cd_ganho" name="cd_ganho" required><br>

    Tipo de Receita: <input type="text" id="tp_receita" name="tp_receita" required><br>

    Código da Movimentação: <input type="text" id="cd_movimentacao" name="cd_movimentacao" required><br>

    Código do Extrato: <input type="text" id="cd_extrato" name="cd_extrato" required><br>

    Nome da Movimentação: <input type="text" id="nm_movimentacao" name="nm_movimentacao" required><br>

    Descrição da Movimentação: <textarea id="ds_movimentacao" name="ds_movimentacao" rows="4"></textarea><br>

    Valor da Movimentação (R$): <input type="number" id="vl_movimentacao" name="vl_movimentacao" step="0.01" required><br>

    Data da Movimentação: <input type="date" id="dt_movimentacao" name="dt_movimentacao" required><br>

    Quantidade de Parcelas: <input type="number" id="qtd_parcelas" name="qtd_parcelas" required><br>

    <button type="submit">Registrar</button>
</form>
</body>
</html>