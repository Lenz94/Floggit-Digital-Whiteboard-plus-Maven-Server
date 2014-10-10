package se.lenz.websockets;


public class Message {
	public String message;
	private int id;
	private String service;
	private String name;
	private String textarea;
	private String color;

	public Message(String message, String name, String textarea, String color,
			int id, String service) {
		this.message = message;
		this.name = name;
		this.textarea = textarea;
		this.color = color;
		this.id = id;
		this.service = service;
	}

	public Message() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTextarea() {
		return textarea;
	}

	public void setTextarea(String textarea) {
		this.textarea = textarea;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Message(String s) {
		this.message = s;
	}

	public boolean isMessage() {
		return true;
	}

}