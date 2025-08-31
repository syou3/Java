package book.web;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		Book bbo;
		
		if(id>=1) {
			try(BookDAO dao=new BookDAO()){
				bbo=dao.detail(id);
			}catch(Exception e) {
				throw new ServletException(e);
			}
		}else{
			bbo=new Book();
			bbo.setId(0);
		}
		request.setAttribute("book", bbo);
		
		RequestDispatcher rd=request.getRequestDispatcher("/detail.jsp");
		rd.forward(request, response);
	}
}
