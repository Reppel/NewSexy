package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by m on 2015/6/6.
 */
public class JdbcUtil {
	private static String driver="com.mysql.jdbc.Driver";
	private String url="jdbc:mysql://127.0.0.1:3306/newsexy?useUnicode=true&amp;characterEncoding=UTF-8&amp;";;
	private String username="root";
	private String password="123456";


	private static JdbcUtil util;
	public JdbcUtil(){};

	public static JdbcUtil getInstance(){
		if(util == null){
			util = new JdbcUtil();
		}

		return util;
	}

	static {
		try {
			Class.forName(driver).newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection(){
		Connection connection=null;
		try {
			connection= DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;

	}


}
