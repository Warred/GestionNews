<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="/style/style.css">
<title>NewsLetter.com : Connexion</title>
<style>
</style>
</head>
<body>
	<form:form modelAttribute="utilisateur" cssClass="box" action="authentification" method="post">
		<h1>Connexion</h1>
		<form:input path="pseudo" type="text" placeholder="Pseudonyme" autocomplete="off"/>
		<form:input path="password" type="password" placeholder="Mot de passe" />
		<p>
			<form:errors path="pseudo"  cssClass="error"/>
			<form:errors path="password" cssClass="error"/>
		</p>
		<input type="submit" id="greenbutton" name="buttonConnexion" value="Se connecter">
		<input type="submit" id="redbutton" name="buttonInscription" value="Inscription">
	</form:form>
</body>
</html>