package medios.cmmn.websocket.message;

//public interface MediosMessageCmd {
//	public static String BROADCAST			= "broadcast";
//	public static String BROADCAST_SESSON	= "broadcastSession";
//	public static String CHECK_UUID			= "checkUUID";
//	public static String CHECK_AUTH			= "checkAuth";
//	public static String CHECK_AUTH_RESULT	= "checkAuthResult";
//	public static String ERROR_CLOSE		= "errorClose";
//}

public enum MediosMessageCmd {
	CHECK_UUID,
	CHECK_AUTH,
	CHECK_AUTH_RESULT,
	ERROR_CLOSE,
	CHECK_CLIENT_MEMBER,
	SEND_MAX_INACTIVE_INTERVAL,
	UPDATE_RX_STATE,
	MESSAGE
}