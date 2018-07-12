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
		//���ڱ��������û���username
		List<String> list=null;
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String action=request.getParameter("action");
		UserDao dao=new UserImp();
		boolean flag=false;
		// System.out.println("[username]:"+username+"[password]:"+password);//������
		UserService userService = new UserService();
		
		if (session.getAttribute("list")!=null) {
			//�����Ѿ������û��б���
			list =(List<String>) session.getAttribute("list");
		}else {
			//��û�д����û��б�
			list= new ArrayList<String>();
			
		}
		if ("check".equals(action)) {
			
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

			if (username != null) {
				try {
					if ((userService.login(username, password)) == 1) {// ��ǰ�����Ѿ���֤
						request.setAttribute("name", username);
						flag=true;
						list.add(username);
//						System.out.println(session);
						
					} else if (userService.login(username, password) == 0) {// ��ǰ�û�����δ��֤
						String validate = "no";
						request.setAttribute("validate", validate);
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					} else {// ��ǰ�û����������
						String login = "no";
						request.setAttribute("login", login);
						

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				flag=true;
				String visitor = "�ο�" + UUID.randomUUID().toString().substring(0, 5);
				request.setAttribute("name", visitor);
				list.add(visitor);
			}
			if (flag) {//�����½
				
				session.setAttribute("list", list);
				request.getRequestDispatcher("/chatRoom.jsp").forward(request, response);
			}else {//�������½
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
		
	}

}
