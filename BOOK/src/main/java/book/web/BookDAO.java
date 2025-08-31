package book.web;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends DAO{
	
//	DBにあるテーブルbook_listの全データを取得
	public List<Book> bookList() throws Exception{
		List<Book> returnList = new ArrayList<Book>();
		String sql="select id,title,name,inputdate from booklist ";
		PreparedStatement statement=getPreparedStatement(sql);
		ResultSet rs=statement.executeQuery();
		while(rs.next()) {
			Book bbo=new Book();
			bbo.setId(rs.getInt("id"));
			bbo.setTitle(rs.getString("title"));
			bbo.setName(rs.getString("name"));
			bbo.setInputdate(rs.getTimestamp("inputdate"));
			returnList.add(bbo);
		}
		return returnList;
	}
//	書籍ごとの詳細データを表示
	public Book detail(int title) throws Exception{
	String sql="select id,title,name,inputdate from booklist where title=?";
	PreparedStatement statement=getPreparedStatement(sql);
	ResultSet rs=statement.executeQuery();
	
	Book bbo=new Book();
	while(rs.next()) {
		bbo.setId(rs.getInt("id"));
		bbo.setTitle(rs.getString("title"));
		bbo.setName(rs.getString("name"));
		bbo.setInputdate(rs.getTimestamp("inputdate"));
	}
	return bbo;
	}
	
	
//	書籍の新規登録
	public int registerInsert(Book bbo) throws Exception{
		String sql="insert into booklist (title,name,inputdate,userid) values(?,?,now(),?)";
		int result=0;
		try {
			PreparedStatement statement=getPreparedStatement(sql);
			statement.setString(1,bbo.getTitle());
			statement.setString(2,bbo.getName());
			statement.setString(3,bbo.getUserid());
			result=statement.executeUpdate();
			super.commit();
		}catch(Exception e) {
			super.rollback();
			throw e;
		}
		return result;
	}
	
//	指定された書籍IDの評価を編集
public int registerUpdate(Book bbo) throws Exception{
	String sql="update booklist set evaluation=?,evadate=now() where id=?";
	int result=0;
	try {
		PreparedStatement statement=getPreparedStatement(sql);
		statement.setInt(1,bbo.getEvaluation());
		statement.setInt(2,bbo.getId());
		result=statement.executeUpdate();
		super.commit();
	}catch(Exception e) {
		super.rollback();
		throw e;
	}
	return result;
}
	
//	指定された書籍IDの情報を削除
	public int delete(int id)throws Exception{
		String sql="delete from booklist where id=?";
		int result=0;
		try {
			PreparedStatement statement=getPreparedStatement(sql);
			statement.setInt(1,id);
			result=statement.executeUpdate();
			super.commit();
		}catch(Exception e) {
			super.rollback();
			throw e;
		}
		return result;
	}
}
