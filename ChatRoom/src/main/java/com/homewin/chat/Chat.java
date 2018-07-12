package com.homewin.chat;

import java.io.IOException;
import java.util.*;


import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;



import com.homewin.config.GetHttpSessionConfigurator;

@ServerEndpoint(value="/chat", configurator = GetHttpSessionConfigurator.class)
public class Chat {
	private boolean first = true;
	private String name;

	private HttpSession httpSession;
	private Session session;
	private static Integer count=0;
	private static final HashMap<String, Object> clientSet = new HashMap<String, Object>();
	private static List<String> list ;
	/*
	 * 客户端连接时触发该方法
	 */
	@OnOpen
	public void start(Session session, EndpointConfig config) throws IOException {
		
		this.session = session;
		httpSession= (HttpSession) config.getUserProperties().get(
				HttpSession.class.getName());
		list = (List<String>)httpSession.getAttribute("list");
//		System.out.println(list);
		broadcast("wjdlasjdajsdaheflajdf");
		if (list!=null) {
			for (int i = 0; i < list.size(); i++) {
				String a = list.get(i)+"djalsdalsi1213123jlasd";
				broadcast(a);
			//	System.out.println("这是开始的列表===============List["+i+"]"+list.get(i));
			}
		}
	
		
	}

	/*
	 * 客户端断开时触发该方法
	 */
	@OnClose
	public void end() {
		clientSet.remove(name, this);
		list = (List<String>)httpSession.getAttribute("list");
		//System.out.println("这是断开连接时的列表============="+list);
		if (list!=null) {
			for (int i = 0; i < list.size(); i++) {
				if (name.equals(list.get(i))) {
					list.remove(i);
				}
			}
		}
	
	//	System.out.println("这是断开连接后的列表============"+list);
		count--;
		String message = String.format("   【%s %s】", name, "离开了聊天室！")+"当前在线人数："+count.toString();
		// 发送消息
		broadcast(message);
		broadcast("wjdlasjdajsdaheflajdf");
		if (list!=null) {
			for (int i = 0; i < list.size(); i++) {
				String a = list.get(i)+"djalsdalsi1213123jlasd";
				broadcast(a);
				
			}
		}
		

		
	}

	/*
	 * 客户端收到消息时触发该方法
	 */
	@OnMessage
	public void print(String msg,EndpointConfig config) {
		if (first) {			
			name = msg;	
			clientSet.put(name, this);
			//测试啦啦啦
			list = (List<String>)httpSession.getAttribute("list");
		//	System.out.println(list);
			broadcast("wjdlasjdajsdaheflajdf");
		for (int i = 0; i < list.size(); i++) {
				String a = list.get(i)+"djalsdalsi1213123jlasd";
				broadcast(a);
		//		System.out.println("这是收到连接消息时的列表===============List["+i+"]"+list.get(i));
			}

			count++;
	

			String message = String.format("   【%s %s】", name, "加入了聊天室！")+"当前在线人数为："+count.toString();

			// 发送消息
			broadcast(message);
			first = false;
		} else {
		
			broadcast("   【" + name + "】" + ":" + msg);
			}
		}
	

	// 当客户端通信出现错误时，激发该方法
	@OnError
	public void onError(Throwable t) throws Throwable {
		System.out.println("WebSocket服务端错误 " + t);
	}

	public void broadcast(String msg) {
		// 遍历服务器关联的所有客户端
		Chat client = null;
		for (String nickname : clientSet.keySet()) {

			try {
				client = (Chat) clientSet.get(nickname);
				synchronized (client) {
					// 发送消息
					client.session.getBasicRemote().sendText(msg);
				}
			} catch (IOException e) {
				System.out.println("聊天错误，向客户端 " + client + " 发送消息出现错误。");
				clientSet.remove(name, client);
				try {
					client.session.close();
				} catch (IOException e1) {
				}
				String message = String.format("【%s %s】", client.name, "已经被断开了连接。");
				broadcast(message);
			}
		}
	}

}
