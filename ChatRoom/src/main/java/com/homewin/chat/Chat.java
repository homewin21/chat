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
	 * �ͻ�������ʱ�����÷���
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
			//	System.out.println("���ǿ�ʼ���б�===============List["+i+"]"+list.get(i));
			}
		}
	
		
	}

	/*
	 * �ͻ��˶Ͽ�ʱ�����÷���
	 */
	@OnClose
	public void end() {
		clientSet.remove(name, this);
		list = (List<String>)httpSession.getAttribute("list");
		//System.out.println("���ǶϿ�����ʱ���б�============="+list);
		if (list!=null) {
			for (int i = 0; i < list.size(); i++) {
				if (name.equals(list.get(i))) {
					list.remove(i);
				}
			}
		}
	
	//	System.out.println("���ǶϿ����Ӻ���б�============"+list);
		count--;
		String message = String.format("   ��%s %s��", name, "�뿪�������ң�")+"��ǰ����������"+count.toString();
		// ������Ϣ
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
	 * �ͻ����յ���Ϣʱ�����÷���
	 */
	@OnMessage
	public void print(String msg,EndpointConfig config) {
		if (first) {			
			name = msg;	
			clientSet.put(name, this);
			//����������
			list = (List<String>)httpSession.getAttribute("list");
		//	System.out.println(list);
			broadcast("wjdlasjdajsdaheflajdf");
		for (int i = 0; i < list.size(); i++) {
				String a = list.get(i)+"djalsdalsi1213123jlasd";
				broadcast(a);
		//		System.out.println("�����յ�������Ϣʱ���б�===============List["+i+"]"+list.get(i));
			}

			count++;
	

			String message = String.format("   ��%s %s��", name, "�����������ң�")+"��ǰ��������Ϊ��"+count.toString();

			// ������Ϣ
			broadcast(message);
			first = false;
		} else {
		
			broadcast("   ��" + name + "��" + ":" + msg);
			}
		}
	

	// ���ͻ���ͨ�ų��ִ���ʱ�������÷���
	@OnError
	public void onError(Throwable t) throws Throwable {
		System.out.println("WebSocket����˴��� " + t);
	}

	public void broadcast(String msg) {
		// �������������������пͻ���
		Chat client = null;
		for (String nickname : clientSet.keySet()) {

			try {
				client = (Chat) clientSet.get(nickname);
				synchronized (client) {
					// ������Ϣ
					client.session.getBasicRemote().sendText(msg);
				}
			} catch (IOException e) {
				System.out.println("���������ͻ��� " + client + " ������Ϣ���ִ���");
				clientSet.remove(name, client);
				try {
					client.session.close();
				} catch (IOException e1) {
				}
				String message = String.format("��%s %s��", client.name, "�Ѿ����Ͽ������ӡ�");
				broadcast(message);
			}
		}
	}

}
