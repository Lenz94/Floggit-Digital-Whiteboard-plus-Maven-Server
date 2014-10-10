package se.lenz.websockets;

public class WhiteboardManager {
	private static WhiteboardManager instance = null;
	
	private WhiteboardManager(){}
	
	public static WhiteboardManager getInstance(){
		if(WhiteboardManager.instance == null){
			WhiteboardManager.instance = new WhiteboardManager();
		}
		return WhiteboardManager.instance;
	}
}
