<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NewsLetter.com : Mes Commentaires</title>
</head>
<body> 
	<c:set var="currentpage" value="mesCommentaires"></c:set>
	<%@ include file="accueil.jsp" %>
	<div class="commentaires">
	<form:form modelAttribute="commentaire" action="editMesCommentaires" method="post">
		<c:forEach items="${listCommentaireOwned}" var="com">
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
			<c:if test="${commentaire.id == com.id}">
					<fieldset style="border: solid 1px black solid">
						<legend><b>Modifier le commentaire</b></legend>
						<form:input path="id" type="hidden"/>			
						<form:input path="date" type="hidden"/>
						<form:input path="utilisateur" type="hidden"/>
						<form:input path="article" type="hidden"/>
						<form:textarea path="message" rows="10" cols="50" autocomplete="off"/>
						<input type="submit" id="greenbutton" name="buttonValider" value="Valider">
					</fieldset>
			</c:if>
		</c:forEach>
	</form:form>
	</div>
</body>
</html>