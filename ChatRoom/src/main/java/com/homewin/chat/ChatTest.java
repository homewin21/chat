//package com.yc.web.websocket;
// 
//import java.io.IOException;
//import java.util.Set;
//import java.util.concurrent.CopyOnWriteArraySet;
//import java.util.concurrent.atomic.AtomicInteger;
// 
//import javax.servlet.http.HttpSession;
//import javax.websocket.EndpointConfig;
//import javax.websocket.OnClose;
//import javax.websocket.OnError;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.ServerEndpoint;
// 
//import com.yc.web.model.GetHttpSessionConfigurator;
// 
//@ServerEndpoint(value = "/chat", configurator = GetHttpSessionConfigurator.class)
//public class WebSocket {
// 
//	private static final String GUEST_PREFIX = "Guest";
//	private static final AtomicInteger connectionIds = new AtomicInteger(0);
//	private static final Set<WebSocket> connections = new CopyOnWriteArraySet<>();
// 
//	private final String nickname;
//	private Session session;
//	private static HttpSession httpSession;
// 
//	public WebSocket() {
//		nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
//	}
// 
//	@OnOpen
//	public void start(Session session, EndpointConfig config) {
//		this.session = session;
//		httpSession = (HttpSession) config.getUserProperties().get(
//				HttpSession.class.getName());
//		connections.add(this);
//		System.out.println(httpSession.getAttribute("name"));
//		String message = String.format("* %s %s", nickname, "has joined.");
//		broadcast(message);
//	}
// 
//	@OnClose
//	public void end() {
//		connections.remove(this);
//		String message = String
//				.format("* %s %s", nickname, "has disconnected.");
//		broadcast(message);
//	}
// 
//	// 监听要发送的内容
//	@OnMessage
//	public void incoming(String message) {
//		// Never trust the client
//		// TODO: 过滤输入的内容
//		broadcast(message);
//	}
// 
//	@OnError
//	public void onError(Throwable t) throws Throwable {
//		System.out.println("Chat Error: " + t.toString());
//	}
// 
//	private static void broadcast(String msg) {
//		for (WebSocket client : connections) {
//			try {
//				synchronized (client) {
//					client.session.getBasicRemote().sendText(msg);
//				}
//			} catch (IOException e) {
//				System.out
//						.println("Chat Error: Failed to send message to client");
//				connections.remove(client);
//				try {
//					client.session.close();
//				} catch (IOException e1) {
//				}
//				String message = String.format("* %s %s", client.nickname,
//						"has been disconnected.");
//				broadcast(message);
//			}
//		}
//	}
//}
