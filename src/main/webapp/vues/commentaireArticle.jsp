<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NewsLetter.com : Commentaires</title>
</head>
<body><c:set var="currentpage" value="commentaireArticle"></c:set>
	<%@ include file="accueil.jsp" %>
	
	<fmt:formatDate value="${art.date}" type="date" var="fmtDate" pattern="dd-MM-YYYY HH-mm"/>
	
	<div class="article">
		<div class="titre"><c:out value="${art.titre}"></c:out>. Catégorie : <c:out value="${art.categorie.nom}"></c:out></div>
		<div class="sousTitre">&Eacute;crit le: ${fmtDate}, par: <c:out value="${art.redacteur.pseudo}"></c:out></div>
		<div class="contenu"><c:out value="${art.contenu}"></c:out></div>
	</div>
	
	<div class="commentaires">
	<form action="editCommentaire" method="post">
		<c:forEach items="${listCommentaires}" var="com" varStatus="loop">
		<fmt:formatDate value="${com.date}" type="date" var="fmtDate" pattern="dd-MM-YYYY  HH-mm"/>
			<div class="comm">
				<div class="infos"><c:out value="${fmtDate}"></c:out>, <c:out value="${com.utilisateur.pseudo}"/>:</div>
				<div class="message"><c:out value="${com.message}"></c:out></div>
			</div>
			<div class="liens">
				<c:if test="${user.id == com.utilisateur.id || user.id == art.redacteur.id}">
					<button name="buttonModifier" value="${com.id}">Modifier</button>
					<button name="buttonSupprimer" value="${com.id}">Supprimer</button>
				</c:if>
			</div>
		</c:forEach>
	</form>	
	</div>
	<form:form modelAttribute="commentaire" action="ajoutCommentaire" method="post">
		<fieldset style="border: solid 1px black solid">
			<legend><b>Ecrire un commentaire</b></legend>
			<form:input path="id" type="hidden"/>			
			<form:input path="date" type="hidden"/>
			<form:input path="utilisateur" type="hidden"/>
			<form:input path="article" type="hidden"/>
			<form:textarea path="message" rows="10" cols="50" autocomplete="off"/>
			<input type="submit" id="greenbutton" name="button" value="Valider">
		</fieldset>
	</form:form>