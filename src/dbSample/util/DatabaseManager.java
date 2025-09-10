package dbSample.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Data Access Object(DAO)を作成
//特徴：SQLを実行しDBとやりとりする(開く、閉じる、selectする）
//データベースとの接続、クローズ処理をするDAO

public class DatabaseManager {
	private static Connection con;
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//接続
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
	            "root",
	            "K9m!z4#Qw8Rp2@Ln"
				);
		
		return con;
	}
	
	public static void close() {
		
		if(con != null) {
			try {
				con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
