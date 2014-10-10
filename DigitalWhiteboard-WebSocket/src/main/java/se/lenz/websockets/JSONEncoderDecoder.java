package se.lenz.websockets;

import java.io.StringReader;
import java.util.Set;

import javax.json.*;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

	public class JSONEncoderDecoder implements Encoder.Text<Set<PostIt> >,
	Decoder.Text<Message> {

		@Override
		public void destroy() {
		}
		
		@Override
		public void init(EndpointConfig ec) {
		}
		
		@Override
		public Message decode(String message) throws DecodeException {
		System.out.println("decoding works");
		
		JsonObject json = Json.createReader(new StringReader(message))
				.readObject();
		
		Message m = new Message();
		m.setId(json.getInt("id"));
		m.setService(json.getString("service"));
		m.setName(json.getString("name"));
		m.setTextarea(json.getString("textarea"));
		m.setColor(json.getString("color"));
		
		return m;
		
		}
		
		@Override
		public boolean willDecode(String message) {
		
		// Check if incoming message is valid JSON
		try {
			Json.createReader(new StringReader(message)).readObject();
		} catch (JsonException e) {
			return false;
		}
		return true;
		}
		
		@Override
		public String encode(Set<PostIt> postits) throws EncodeException {
		
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		
		for(PostIt p: postits){
			jsonArrayBuilder.add
			(Json.createObjectBuilder()
				.add("id", p.getId())
				.add("name", p.getName())
				.add("textarea", p.getTextarea())
				.add("color", p.getColor())
		);
		}
		
		return jsonArrayBuilder.build().toString();
		
		}
		
}