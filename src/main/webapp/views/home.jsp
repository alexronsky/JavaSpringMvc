<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Таблица</title>
	<style>
		td { padding: 5px;	}
		.err { color: red; }
	</style>
</head>
<body>
	<!-- Таблица -->
	<h2>Таблица </h2>
	<form action="" method="POST">
	<table>
	<c:forEach items="${tableItems}" var="item"> 
		<tr>
		<td>
			<input type="checkbox" name="deleteItems" value="${item.id}" />
		</td>
		<td>
			${item.id} 
		</td>
		<td>
			${item.name}
		</td>
		<td>
			<fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd HH:mm" />			
		</td>
		</tr>
	</c:forEach>
	</table>
	<input type="submit" value="Удалить выбранные строки" />
	</form>
	
	<!-- Добавление новой строки -->	
	<form action="" method="POST">
		<h2>Добавить новую строку:</h2>
		
		<c:if test="${not empty error}">
			<p class="err">${error}</p>
		</c:if>
		
		<label for="newName">Имя</label>
		<input type="text" name="newName" id="newName" size="30" value="${newName}" />
		
		<label for="newDate">Дата</label>
		<input type="text" name="newDate" id="newDate" size="30" value="${newDate}" />
		
		<input type="submit" value="Добавить" />
	</form>	
	
</body>
</html>
