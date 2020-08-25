
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn { //DB���� ��� ������ �س��� �̰� �ٸ������� ��ӹ޾Ƽ� �̿��Ұ�
	//����̺�ε�
		static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException cnfe) {
			System.out.println("JDBC Driver �ε� ����...-->" + cnfe.getMessage());
		}
	}
		Connection conn;
		PreparedStatement pstmt;
		ResultSet rs;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "scott";
		String password = "tiger";
	public DBConn() {
		//�����ڿ� �ƹ��͵� �ȳְ�
	}
	//DB���� �޼ҵ�
	public void getConn() {
		try {
			conn = DriverManager.getConnection(url, userid, password); //DB����
		}catch(SQLException se) {
			System.out.println("DB���� �����߻� -->" + se.getMessage());
		}
	}
	//DB�ݱ� 
	public void getClose() {
		try {
			if(rs != null)rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(SQLException se) {
			System.out.println("DB�ݱ� �����߻� -->" + se.getMessage());
		}
	}
}
