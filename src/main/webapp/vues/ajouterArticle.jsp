<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NewsLetter.com : Accueil</title>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">	
		$(document).ready(function(){
	        $('input[type="radio"]').click(function(){
				 var val = $(this).attr( "value" );
				 if(val == 'oui') {
				 	$('#ajoutCat').show();
				 	$('#choisirCat').hide();
				 } else {
				 	$('#choisirCat').show();
				   $('#ajoutCat').hide();
				 }
	        });
		});
	</script>
<style>
	textarea { resize: none; }
	.error { color: red; }]
</style>
</head>
<body> 
	<c:set var="currentpage" value="ajouterArticle"></c:set>
	<%@ include file="accueil.jsp" %>
	<form:form modelAttribute="article" cssClass="box" action="ajoutArticle" method="post">
		<fieldset style="border: solid 1px black solid">
			<legend><b>Ajout d'un nouvel article</b></legend>
			<p>Choisir une catégorie</p>
			<div>
				<label><input type="radio" id="ajouter" name="newCat" value="oui" 
				<c:if test="${ newCat == 'oui'}">checked</c:if>>Ajouter une catégorie</label>
				<label><input type="radio" id="choisir" name="newCat" value="non" 
				<c:if test="${ newCat == 'non'}">checked</c:if>>Choisir dans la liste</label>
			</div><br><form:errors path="categorie" cssClass="error"/>
			<div id="ajoutCat" <c:if test="${newCat != 'oui'}">style="display: none"</c:if>>
				<label>Nom de la catégorie : </label><input type="text" name="nomCat">
			</div>
			<div id="choisirCat" <c:if test="${newCat != 'non'}">style="display: none"</c:if>>
				<select name="selectCat">
					<option value="">--choisir une catégorie--</option>
					<c:forEach items="${listCategorie}" var="cat">
						<option value="${cat.id}" <c:if test="${thisCat == cat.id}">selected</c:if>>${cat.nom}</option>
					</c:forEach>
				</select>
			</div>
			<br>
			<form:input path="id" type="hidden"/>
			<form:input path="redacteur" type="hidden"/>
			<form:input path="date" type="hidden"/>
			<label>Titre de l'article :</label>
			<form:input path="titre" type="text" autocomplete="off"/><br>
			<label>Rédigez votre article :</label><br>
			<form:textarea path="contenu" autocomplete="off" rows="10" cols="50"/>
			<br><input type="submit" id="greenbutton" name="button" value="Valider">
		</fieldset>
	</form:form>
</body>
</html>