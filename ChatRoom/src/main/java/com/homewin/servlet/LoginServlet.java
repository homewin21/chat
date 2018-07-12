package com.homewin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.homewin.dao.UserDao;
import com.homewin.daoImp.UserImp;
import com.homewin.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//用于保存在线用户的username
		List<String> list=null;
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String action=request.getParameter("action");
		UserDao dao=new UserImp();
		boolean flag=false;
		// System.out.println("[username]:"+username+"[password]:"+password);//测试用
		UserService userService = new UserService();
		
		if (session.getAttribute("list")!=null) {
			//当下已经存在用户列表了
			list =(List<String>) session.getAttribute("list");
		}else {
			//还没有存在用户列表
			list= new ArrayList<String>();
			
		}
		if ("check".equals(action)) {
			
			try {
				if (dao.checkExit(username)==1) {//用户已存在
					out.write("1");
				}else {
					out.write("0");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {

			if (username != null) {
				try {
					if ((userService.login(username, password)) == 1) {// 当前邮箱已经验证
						request.setAttribute("name", username);
						flag=true;
						list.add(username);
//						System.out.println(session);
						
					} else if (userService.login(username, password) == 0) {// 当前用户邮箱未验证
						String validate = "no";
						request.setAttribute("validate", validate);
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					} else {// 当前用户名密码错误
						String login = "no";
						request.setAttribute("login", login);
						

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				flag=true;
				String visitor = "游客" + UUID.randomUUID().toString().substring(0, 5);
				request.setAttribute("name", visitor);
				list.add(visitor);
			}
			if (flag) {//允许登陆
				
				session.setAttribute("list", list);
				request.getRequestDispatcher("/chatRoom.jsp").forward(request, response);
			}else {//不允许登陆
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
		
	}

}
