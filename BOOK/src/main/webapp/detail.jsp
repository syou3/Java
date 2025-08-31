<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="book.web.Book"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍通帳</title>
</head>
<body>
	<h1>書籍詳細</h1>
	<%
	Book book = (Book) request.getAttribute("book");
	pageContext.setAttribute("book", book);
	%>

	<form action="RegisterServlet" method="post">
		<table border="">
			<tr>
				<th>番号</th>
				<th>タイトル</th>
				<th>著者</th>
				<th>読み始め</th>
			</tr>
			<%
			if (book.getId() > 0) {
			%>
			<td>${ book.id }</td>
			<%
			} else {
			%>
			<td>新規</td>
			<%
			}
			%>
			<td><input type="text" name="title" value="${ book.title}" /></td>
			<td><input type="text" name="name" value="${ book.name}" /></td>
			<td><input type="text" name="inputdate"
				value="${ book.inputdate.getCurrentDateString()}" /></td>
			<td><input type="text" name="userid" value="${book.userid }"/><td>
		</table>
<br>
		<input type="hidden" name="id" value="${book.id }" /> <input
			type="submit" value="更新" />
	</form>
	<br>
	<form action="DeleteServlet" method="post">
	<input type="hidden" name="id" value="${book.id }" />
	<input type="submit" value="削除" />
	</form>
</body>
</html>