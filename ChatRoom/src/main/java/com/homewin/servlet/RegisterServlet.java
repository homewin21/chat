package com.homewin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homewin.dao.UserDao;
import com.homewin.daoImp.UserImp;
import com.homewin.service.UserService;


public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String action=request.getParameter("action");
		UserDao dao=new UserImp();
		PrintWriter out = response.getWriter();
	//	System.out.println(userName+" "+password+" "+email);
		UserService userService=new UserService();
		if ("check".equals(action)) {
			String username=request.getParameter("username");
			try {
				if (dao.checkExit(username)==1) {//�û��Ѵ���
					out.write("1");
				}else {
					out.write("0");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				if(userService.register(userName,password,email)){
					request.setAttribute("msg", "ע��ɹ������¼���伤���˺�");
				}else{
					request.setAttribute("msg", "ע��ʧ�ܣ����������Ϣ");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("/result.jsp").forward(request, response);
		}
		

		
	}
	
	
}
