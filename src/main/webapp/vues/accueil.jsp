<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body> 
	<c:choose>
		<c:when test="${user.getClass().getSimpleName() == 'Redacteur'}">
			<%@ include file="accueilRedacteur.jsp" %>
		</c:when><c:when test="${user.getClass().getSimpleName() == 'Lecteur'}">
			<%@ include file="accueilLecteur.jsp" %>
		</c:when>		
	</c:choose>
</body>
</html>