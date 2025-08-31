<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="book.web.Book,java.util.*,java.text.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍通帳</title>
</head>
<body>
	<h1>書籍一覧</h1>
	<%
	String loginUser=(String)request.getUserPrincipal().getName();
	session.setAttribute("loginUser",loginUser);
	%>
	<p>ユーザー: ${loginUser}</p>
	<form action="logout.jsp">
		<input type="submit" value="ログアウト"></input>
	</form>
	<table border="1">
		<tr>
			<th>番号</th>
			<th>タイトル</th>
			<th>著者</th>
			<th>読み始め</th>
			<th>書籍情報</th>
		</tr>

		<tr>
			<%
			List<Book> books = (List<Book>) request.getAttribute("bookList");
			if (books != null) {
				Book book;
				for (int i = 0; i < books.size(); i++) {
					book = books.get(i);
			%>

			<td><%=book.getId()%></td>
			<td><%=book.getTitle()%></td>
			<td><%=book.getName()%></td>
			<td><%=book.getInputdate() %>
			<td><a href="DetailServlet?title=<%=book.getTitle()%>"></a>詳細</td>
		</tr>
		<%
		}
		}
		%>
			</table>
		<form action="DetailServlet" method="get">
			<input type="hidden" name="id" value="0" /> <input type="submit"
				value="新規" />
		</form>

</body>
</html>