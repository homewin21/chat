package com.homewin.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.homewin.bean.User;
import com.homewin.dao.UserDao;
import com.homewin.util.DBUtil;


public class UserImp implements UserDao {

	@Override
	public int save(User user) throws SQLException {
		int flag=0;
		Connection connection = DBUtil.getConnection();
		String sql ="insert into user(username,email,password,state,code) values(?,?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getEmail());
		pstmt.setString(3, user.getPassword());
		pstmt.setInt(4, user.getState());
		pstmt.setString(5, user.getCode());
		flag=pstmt.executeUpdate();
		DBUtil.close(connection, pstmt, null);
		return flag;
	}

	@Override
	public int activeUser(String code) throws SQLException {
		// TODO Auto-generated method stub
		int flag=0;
		Connection connection = DBUtil.getConnection();
		String sql="update user set state=1 where code=?";
		PreparedStatement pstmt=connection.prepareStatement(sql);
		pstmt.setString(1, code);
		flag=pstmt.executeUpdate();
		DBUtil.close(connection, pstmt, null);
		return flag;
	}

	@Override
	public int login(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		//1表示已经激活，0表示还未激活，2表示
		int flag=2;
	
		Connection conn=DBUtil.getConnection();

		String sql="select * from user where username=? and password=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet rs= pstmt.executeQuery();
		if (rs.next()) {
			flag=rs.getInt(5);//根据state激活状态决定是否可以允许登陆
//			System.out.println(flag);
		}
		
		return flag;
	}

	@Override
	public int checkExit(String username) throws Exception {
		int flag=0;
		String sql="select * from user where username=?";
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, username);

		ResultSet rs= pstmt.executeQuery();
		if (rs.next()) {
			flag=1;
		}
		return flag;
	}

}
