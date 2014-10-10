package se.lenz.websockets;

import java.io.IOException;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(
		value ="/shout",
		encoders = JSONEncoderDecoder.class,
		decoders = JSONEncoderDecoder.class
		)
public class WebSocketEndPoint {

	static PostItcontroller postits = new PostItcontroller();

	@OnOpen
	public void myOnOpen(Session session) {
		System.out.println("size of collection: " + postits.getAllpostits().size());
		System.out.println("Client connected " + session.getId());
		sendMessage(session);
	
	}

	@OnMessage
	public void myOnMessage(Session session, Message message)throws IOException {
		
		if (message.getService().equals("create")) {
			postits.addPostit(message);
			sendMessage(session);
			
		} else if (message.getService().equals("update")) {
			postits.updatePostit(message);
			sendMessage(session);

		} else if (message.getService().equals("delete")) {
			postits.deletePostIt(message);
			sendMessage(session);
		}

	}

	public void sendMessage(Session session) {
		try {
			for (Session currentSession : session.getOpenSessions()) {
				if (currentSession.isOpen()) {
					try {
						System.out.println("collection= " + postits.getAllpostits());
						currentSession.getBasicRemote().sendObject(postits.getAllpostits());
					} catch (IOException | EncodeException e) {
						System.out.println(e.toString());
					}

				} 
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@OnClose
	public void myOnClose(Session session, CloseReason reason) {
		System.out.println("Client closed");
	}

	@OnError
	public void myOnError(Session session, Throwable trowable) {
		System.out.println("Client Error");
		trowable.printStackTrace();
	}
}


