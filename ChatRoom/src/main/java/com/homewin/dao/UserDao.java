package com.homewin.dao;

import java.sql.SQLException;

import com.homewin.bean.User;

public interface UserDao {
	int save(User user) throws SQLException;//保存用户信息
	int activeUser(String code) throws Exception;//激活用户
	int login(String username,String password)throws Exception;//登陆查询用户信息
	int checkExit(String username)throws Exception;
}
