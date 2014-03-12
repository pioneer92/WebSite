
import java.sql.*;

public class UserBeanCl {
	private Connection connection=null;
	private Statement statement=null;
	private ResultSet resultSet=null;
	
	public int checkUser(String username,String password){
		int b=0;
		try {
			ConnDB connDB=new ConnDB();
			connection=connDB.getConnection();
			statement=connection.createStatement();
			resultSet=statement.executeQuery("select password from users where username = '"+username+"' limit 1;");
			if (resultSet.next()) {
				b++;
				String dbPassword=resultSet.getString(1);
				if (dbPassword.equals(password)) {
					b++;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			this.closeDB();
		}
		return b;
	}
	
	public void closeDB(){
		try {
			if (resultSet!=null) {
				resultSet.close();
				resultSet=null;
			}
			if (statement!=null) {
				statement.close();
				statement=null;
			}
			if (connection!=null) {
				connection.close();
				connection=null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
