package se.lenz.websockets;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
;

public class PostItcontroller {
	 int id;
	
	static Set<PostIt> PostItSet = Collections.synchronizedSet(new HashSet<PostIt>());

	public void addPostit(Message message) {

		PostIt postitadd = new PostIt();
		
		for (id = message.getId(); id <= PostItSet.size(); id++)
		{
			postitadd.setId(id);
		}	
			postitadd.setName(message.getName());
			postitadd.setTextarea(message.getTextarea());
			postitadd.setColor(message.getColor());
		
		

		PostItSet.add(postitadd);
		System.out.println("size of collection: " + PostItSet.size());

	}

	public void updatePostit(Message message) {

		PostIt postitupdate = new PostIt();
		
		postitupdate = searchpost(message);

		postitupdate.setId(message.getId());
		postitupdate.setName(message.getName());
		postitupdate.setTextarea(message.getTextarea());
		postitupdate.setColor(message.getColor());
		
		PostItSet.add(postitupdate);

	}

	public void deletePostIt(Message message) {

		PostIt p = new PostIt();

		p = searchpost(message);
		PostItSet.remove(p);
		System.out.println("size of collection: " + PostItSet.size());

	}

	public PostIt searchpost(Message message) {
		for (PostIt postIt : PostItSet) {
			if (postIt.getId() == message.getId()) {
				return postIt;
			}
		}
		return null;
	}

	public Set<PostIt> getAllpostits() {
		return PostItSet;

	}

}
