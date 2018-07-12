package com.homewin.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class DBUtil {
	private static ComboPooledDataSource dataSource = null;
	static {
		dataSource = new ComboPooledDataSource("mysql");

	}

	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		connection = dataSource.getConnection();
		return connection;
	}

	public static void close(Connection connection,PreparedStatement pstmt,ResultSet rs) throws SQLException {
		if (connection!=null) {
			connection.close();
		}
		if (pstmt!=null) {
			pstmt.close();
		}
		if (rs!=null) {
			rs.close();
			
		}
	}

}
