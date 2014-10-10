package se.lenz.websockets;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

public class ClientManager {
	private static ClientManager instance = null;
	protected Map<String, Client> clients = Collections.synchronizedMap(new HashMap<String, Client>());
	
	private ClientManager(){}
	
	public Client add(Session session){
		Client client = new Client(session);
		this.clients.put(session.getId(), client);
		return client;
		
	}
	public static ClientManager getInstance(){
		if(ClientManager.instance == null){
			ClientManager.instance = new ClientManager();
		}
		return ClientManager.instance;
	}
}
