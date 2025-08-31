package book.web;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String paramId=request.getParameter("id");
		
		try(BookDAO bao =new BookDAO()){
			bao.delete(Integer.parseInt(paramId));
		}catch(Exception e) {
			throw new ServletException(e);
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("/SearchServlet");
		rd.forward(request, response);
	}

}
