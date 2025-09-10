package dbSample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DbConnectSample05 {

        public static void main(String[] args) {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");


            con = DriverManager.getConnection(
            		"jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
            		"root",
            		"K9m!z4#Qw8Rp2@Ln"
            );

            String sql = "INSERT INTO city (Name,CountryCode,District,Population) VALUES ('Rafah',?,'Rafah',?)";

            pstmt = con.prepareStatement(sql);

            System.out.print("CountryCodeを入力してください > ");
            String str1 = keyIn();

            System.out.println("Populationを数字で入力してください > ");
            int num1 = Integer.parseInt(keyIn());

            pstmt.setString(1, str1);
            pstmt.setInt(2,num1);

            int count = pstmt.executeUpdate();
            System.out.println(count);

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
                        if (pstmt != null) {
                                try {
                                        pstmt.close();
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