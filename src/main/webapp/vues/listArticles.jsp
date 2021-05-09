<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NewsLetter.com : Mes Articles</title>
</head>
<body>
	<c:set var="currentpage" value="listArticles"></c:set>
	<%@ include file="accueil.jsp" %>
	<form action="listArticles" method="post">
	
	<c:forEach items="${listArticle}" var="art">
		<fmt:formatDate value="${art.date}" type="date" var="fmtDate" pattern="dd-MM-YYYY"/>
		<div class="article">
			<div class="titre"><c:out value="${art.titre}"></c:out></div>
			<div class="sousTitre">&Eacute;crit le : ${fmtDate}, Catégorie : <c:out value="${art.categorie.nom}"></c:out></div>
			<div class="contenu"><c:out value="${art.contenu}"></c:out></div>
			<div class="footer">Auteur: <c:out value="${art.redacteur.pseudo}"></c:out></div>
			<div class="liens">
				<button name="buttonCommentaires" value="${art.id}">Commentaires</button>
				<c:if test="${user.id == art.redacteur.id}">
					<button name="buttonModifier" value="${art.id}">Modifier</button>
					<button name="buttonSupprimer" value="${art.id}">Supprimer</button>
				</c:if>
			</div>
		</div>
	</c:forEach>
	
	</form>
</body>
</html>