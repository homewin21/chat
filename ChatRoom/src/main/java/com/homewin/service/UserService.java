package com.homewin.service;

import java.sql.SQLException;
import java.util.UUID;


import com.homewin.bean.User;
import com.homewin.dao.UserDao;
import com.homewin.daoImp.UserImp;

public class UserService {
	public  boolean register (String username,String password,String email) throws SQLException {
		if(!email.matches("^\\w+@(\\w+\\.)+\\w+$")){
			return false;
		}
		String code=UUID.randomUUID().toString().substring(0, 15);
		User user = new User(null, username, password, email, 0, code);
		UserDao dao = new UserImp();
		if (dao.save(user)>0) {
			new Thread(new com.homewin.util.MailUtil(email, code)).start();
			return true;
		}
		return false;
	}
	public boolean activeUser(String code) throws Exception {
		UserDao userDao=new UserImp();
		if(userDao.activeUser(code)>0){
			return true;
		}else{
			return false;
		}
	}
	public int login(String username,String password) throws Exception {
		UserDao dao = new UserImp();
		return dao.login(username, password);
	}
}
