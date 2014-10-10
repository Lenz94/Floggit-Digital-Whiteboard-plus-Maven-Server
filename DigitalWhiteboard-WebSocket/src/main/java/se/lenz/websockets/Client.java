package se.lenz.websockets;
import javax.websocket.Session;

public class Client{
	
	private Session session;
	protected Whiteboard wb;
	
	public Client(Session session){
		this.session = session;
	}
	
	public void joinWhiteboard(Whiteboard whiteboard){
		this.wb = whiteboard;
		
	}
	
	public Session getSession() {
		return session;
	}

	
	public String getId(){
		return this.session.getId();
	}
	
}
