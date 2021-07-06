package medios.cmmn.websocket.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;
import medios.cmmn.MediosAbstractVO;

@Getter @Setter
public class MediosMessage extends MediosAbstractVO {

	private static final long serialVersionUID = 5151496882883496396L;

	private static final Logger LOGGER = LoggerFactory.getLogger(MediosMessage.class);

	private MediosMessageType	messageType;
	private MediosMessageCmd	messageCmd;
	private Object				message;
	
	private Object				sessionId;
	private Object				userId;
	private Object				sender;

	public MediosMessage(MediosMessageType messageType, MediosMessageCmd messageCmd, Object message) {
		this.messageType	= messageType;
		this.messageCmd		= messageCmd;
		this.message		= message;
		
//		this.sender			= new HashSet<Object>();
	}
	
	public MediosMessage(MediosMessageType messageType, MediosMessageCmd messageCmd, String userId, Object message) {
		this.messageType	= messageType;
		this.messageCmd		= messageCmd;
		this.message		= message;
		
//		this.sender			= new HashSet<Object>();
		
		this.userId			= userId;
	}
	
	public MediosMessage(MediosMessageType messageType, MediosMessageCmd messageCmd, String sessionId, String userId, Object message) {
		this.messageType	= messageType;
		this.messageCmd		= messageCmd;
		this.message		= message;
		
//		this.sender			= new HashSet<Object>();
		
		this.sessionId		= sessionId;
		this.userId			= userId;
	}

	public MediosMessage(String message) {
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(message);
			
			String messageType	= (String) jsonObject.get("messageType");
			this.messageType	= MediosMessageType.valueOf(messageType);
			String messageCmd	= (String) jsonObject.get("messageCmd");
			this.messageCmd		= MediosMessageCmd.valueOf(messageCmd);
			this.message		= (Object) jsonObject.get("message");
			
			if(this.message instanceof JSONObject) {
				this.message = new HashMap<Object,Object>((Map<?,?>) this.message);
			} else if(this.message instanceof JSONArray) {
				this.message = new ArrayList<Object>((List<?>) this.message);
			}
			
			this.sessionId		= (Object) jsonObject.get("sessionId");
			this.userId			= (Object) jsonObject.get("userId");
			
			this.sender			= (Object) jsonObject.get("sender");

		} catch (ParseException e) {
			LOGGER.error(e.getLocalizedMessage(), e);
		}
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		StringBuffer sbMsg = new StringBuffer();
		if(message instanceof Map) {
			sbMsg.append(JSONObject.toJSONString((Map<?,?>) message));
		} else if(message instanceof List) {
			sbMsg.append(JSONArray.toJSONString((List<?>) message));
		} else {
			sbMsg.append("\"").append(message).append("\"");
		}
		
		sb.append("\"").append("messageType"	).append("\"").append(":").append("\"").append(messageType	).append("\"");
		sb.append(",");
		sb.append("\"").append("messageCmd"		).append("\"").append(":").append("\"").append(messageCmd	).append("\"");
		sb.append(",");
		sb.append("\"").append("message"		).append("\"").append(":").append(sbMsg);
		return sb.insert(0, "{").append("}").toString();
	}

	public String toFullString() {
		StringBuffer sb = new StringBuffer();
		StringBuffer sbMsg = new StringBuffer();
		if(message instanceof Map) {
			sbMsg.append(JSONObject.toJSONString((Map<?,?>) message));
		} else if(message instanceof List) {
			sbMsg.append(JSONArray.toJSONString((List<?>) message));
		} else {
			sbMsg.append("\"").append(message).append("\"");
		}
		
		Object			sessionId	= this.sessionId!=null?this.sessionId	:"";
		Object			userId		= this.userId	!=null?this.userId		:"";
		Object			sender		= this.sender	!=null?this.sender		:"";
		
		
		sb.append("\"").append("messageType"	).append("\"").append(":").append("\"").append(messageType	).append("\"");
		sb.append(",");
		sb.append("\"").append("messageCmd"		).append("\"").append(":").append("\"").append(messageCmd	).append("\"");
		sb.append(",");
		sb.append("\"").append("sessionId"		).append("\"").append(":").append("\"").append(sessionId	).append("\"");
		sb.append(",");
		sb.append("\"").append("userId"			).append("\"").append(":").append("\"").append(userId		).append("\"");
		sb.append(",");
		sb.append("\"").append("sender"			).append("\"").append(":").append("\"").append(sender		).append("\"");
		sb.append(",");
		sb.append("\"").append("message"		).append("\"").append(":").append(sbMsg);
		return sb.insert(0, "{").append("}").toString();
	}
}
