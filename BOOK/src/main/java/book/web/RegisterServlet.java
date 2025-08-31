package book.web;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//idに紐づいた新規登録・更新処理をし、SearchServletへフォワード

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id=Integer.parseInt(request.getParameter("id"));
		String title=request.getParameter("title");
		String name=request.getParameter("name");
		String inputdate=request.getParameter("inputdate");
		
		Book bbo=new Book();
		bbo.setId(id);
		bbo.setTitle(title);
		bbo.setName(name);
		bbo.getInputdate();
		
		try(BookDAO dao=new BookDAO()){
			if(id==0) {
				dao.registerInsert(bbo);
			}else {
				dao.registerUpdate(bbo);
			}
		}catch(Exception e) {
			throw new ServletException(e);
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("/SearchServlet");
		rd.forward(request, response);
	}

}
