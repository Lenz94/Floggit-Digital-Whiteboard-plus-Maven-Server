package se.lenz.websockets;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Whiteboard {
	protected Set<PostIt> postIts = Collections.synchronizedSet(new HashSet<PostIt>());
	protected Map<String, Client> clients = Collections.synchronizedMap(new HashMap<String, Client>());
	
	public void add(PostIt postIt){
		this.postIts.add(postIt);
	}
	
	public void addClient(Client client){
		this.clients.put(client.getId(), client);
	}
	
	public boolean hasAccess(Client client){
		return this.clients.containsKey(client.getId());
	}
	
	public void Broadcast(){}
}
