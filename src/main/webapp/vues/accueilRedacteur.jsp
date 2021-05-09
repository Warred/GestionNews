<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<title>NewsLetter.com : Accueil (redacteur)</title>
	<link rel="stylesheet" href="/style/style.css">
</head>
<body>
	<div class="menu" id="myMenu">
		<a href="javascript:void(0);" class="icon" onclick="myFunction()">
    		Menu (Afficher/Maquer) <i class="fa fa-bars"></i>
    	</a>
		<a <c:if test="${empty currentpage}">class="active"</c:if> href="redirect?page=accueilRedacteur">Accueil</a>
		<a <c:if test="${currentpage == 'listArticles' }">class="active"</c:if> href="redirect?page=listArticles">Lire les articles</a>
		<a <c:if test="${currentpage == 'ajouterArticle' }">class="active"</c:if> href="redirect?page=ajouterArticle">Rediger un article</a>
		<a <c:if test="${currentpage == 'mesArticles' }">class="active"</c:if> href="redirect?page=mesArticles">Mes articles</a>
		<a <c:if test="${currentpage == 'mesCommentaires' }">class="active"</c:if> href="redirect?page=mesCommentaires">Mes commentaires</a>
		<a href="redirect?page=deco">Déconnexion</a>
	</div>
	<c:if test="${empty currentpage}"><div>
		<h2>Bonjour, <c:out value="${user.pseudo}"></c:out>.</h2>
		<h3>Connection à l'application "NewsLetter v1" réussie.</h3>
	</div></c:if>
	<br>
	<script>
		function myFunction() {
			var x = document.getElementById("myMenu");
			if (x.className === "menu") {
				x.className += " responsive";
			} else {
				x.className = "menu";
			}
		}
	</script>
</body>
</html>