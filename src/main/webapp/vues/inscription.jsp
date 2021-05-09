<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/style/style.css">
<title>NewsLetter.com : Inscription</title>
</head>
<body>
	<form:form modelAttribute="utilisateur" cssClass="box" action="inscription" method="post">
		<h1>Inscription</h1>
		<form:input path="nom" type="text" placeholder="Nom" autocomplete="off"/>
		<form:input path="prenom" type="text" placeholder="Prénom" autocomplete="off"/>
		<br><form:input path="pseudo" type="text" placeholder="Pseudonyme" autocomplete="off"/>
		<form:input path="password" type="password" placeholder="Mot de passe" />
		<br><label for="rang">Indiquez le type d'utilisateur à inscrire :</label>
		<select name="rang">
			<option value="">--choisir un rang--</option>
			<option value="redacteur">Rédacteur</option>
			<option value="lecteur">Lecteur</option>
		</select>		
		<p>
			<form:errors path="nom"  cssClass="error"/>
			<form:errors path="prenom" cssClass="error"/>
			<form:errors path="pseudo"  cssClass="error"/>
			<form:errors path="password" cssClass="error"/>			
		</p>
		<input type="submit" id="greenbutton" name="buttonSInscrire" value="S'inscrire">
		<input type="submit" id="redbutton" name="buttonRetour" value="Retour">
	</form:form>
</body>
</html>