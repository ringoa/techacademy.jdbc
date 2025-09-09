package dbSample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectSample03 {
        public static void main(String[] args) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");


            con = DriverManager.getConnection(
            		"jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
            		"root",
            		"K9m!z4#Qw8Rp2@Ln");


                        stmt = con.createStatement();


            System.out.print("検索キーワードを入力してください > ");
            String input = keyIn();

            String sql = "select * from country where Name = '" + input + "'";
            rs = stmt.executeQuery(sql);


                        while (rs.next()) {

                                String name = rs.getString("Name");


                                int population = rs.getInt("Population");


                                System.out.println(name);
                                System.out.println(population);
                        }
                } catch (ClassNotFoundException e) {
                        System.err.println("JDBCドライバのロードに失敗しました。");
                        e.printStackTrace();
                } catch (SQLException e) {
                        System.err.println("データベースに異常が発生しました。");
                        e.printStackTrace();
                } finally {

                        if (rs != null) {
                                try {
                                        rs.close();
                                } catch (SQLException e) {
                                        System.err.println("ResultSetを閉じるときにエラーが発生しました。");
                                        e.printStackTrace();
                                }
                        }
                        if (stmt != null) {
                                try {
                                        stmt.close();
                                } catch (SQLException e) {
                                        System.err.println("Statementを閉じるときにエラーが発生しました。");
                                        e.printStackTrace();
                                }
                        }
                }
                if (con != null) {
                        try {
                                con.close();
                        } catch (SQLException e) {
                                System.err.println("データベース切断時にエラーが発生しました。");
                                e.printStackTrace();
                        }
                }
        }


        private static String keyIn() {
                String line = null;
                try {
                        BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
                        line = key.readLine();
                } catch (IOException e) {

                }
                return line;
        }
}