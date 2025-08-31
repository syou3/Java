package book.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//JavaからSQLを実行するためのメソッド

public class DAO implements AutoCloseable{
	
	public Connection connection=null;

//	DB接続+データソース取得、例外処理あり（取得済み→既存接続利用、未接続→新たに取得）
	public Connection getConnection() throws Exception{
		try {
		if(connection==null||connection.isClosed()) {
			InitialContext initCtx=new InitialContext();
			DataSource ds=(DataSource)initCtx.lookup("java:comp/env/jdbc/localDB");
			ds.getConnection();
		}
		}catch(NamingException|SQLException e) {
			e.printStackTrace();
			connection=null;
			throw e;
		}
		return connection;
	}

//	jdbcを利用するためのSQL文の発行をして、それを返す
	public PreparedStatement getPreparedStatement(String sql) throws Exception{
		return getConnection().prepareStatement(sql);
	}
//	更新の確定(コミット)をする
	public void commit() throws SQLException{
		connection.commit();
	}
	
//	更新の取り消し(ロールバック)をする
	public void rollback() throws SQLException{
		connection.rollback();
	}
	
//	DB接続を閉じる
	public void close() {
		try {
		connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connection=null;
		}
	}
}
