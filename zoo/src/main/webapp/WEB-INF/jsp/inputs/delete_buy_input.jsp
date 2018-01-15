<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/includes/header.jsp" />
<main class="container">

<div class="form-group col-md-3 col-xs-12">

	<div class="form-group col-md-12 col-xs-12">
		<label for="sel1">Selecione o insumo:</label> <select
			class="form-control" id="name" name="name">
			<option>Todos</option>

			<c:forEach items="${list}" var="input">
				<option>${input.nameInput}</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group col-md-12 col-xs-12">
		<label for="sel1">Digite um ano:</label> <input class="form-control" type="text" maxlength="4"
			id="dateBuy" name="dateBuy">
	</div>
	<input type="button" value="Pesquisar" id="salvar"
		class="btn btn-success salvar col-md-12 col-xs-12 col-md-offset-12">
</div>

<div id="snackbar">Cadastro realizado com sucesso!</div>

<div class="form-group col-md-9 col-xs-12" id="reportBuy">


</div>

</main>
<c:import url="/includes/footer.jsp" />
<script src="<c:url value="/js/delete_buy_input.js" />"></script>
