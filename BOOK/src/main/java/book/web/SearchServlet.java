package book.web;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 書籍一覧を取得し、list.jspへフォワードする処理

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		BookDAOクラスのインスタンスdao作成+エラーがあったらServlet検索例外送出
		try(BookDAO bao=new BookDAO()){
//			book_listの全データ取得
			List<Book> list=bao.bookList();
//			リクエスト属性へセット
			request.setAttribute("bookList", list);
		
		}catch(Exception e) {
//			例外はServletExceptionへ送出
			throw new ServletException(e);
		}
		
//		list.jspへフォワードするために設定
		RequestDispatcher rd=request.getRequestDispatcher("/list.jsp");
//		list.jspへフォワードする
		rd.forward(request, response);
	}

}
