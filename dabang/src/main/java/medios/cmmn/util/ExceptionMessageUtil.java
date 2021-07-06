package medios.cmmn.util;

public class ExceptionMessageUtil {

	public static StringBuilder getThrowableMessage(Throwable throwable) {
		return new StringBuilder("Exception : " + getCauseMessage(throwable));
	}
	
	private static StringBuilder getCauseMessage(Throwable throwable) {
		StringBuilder sb = new StringBuilder(throwable.getMessage());
		Throwable cause = throwable.getCause();
		if(cause != null) sb.append("| Caused by: ").append(getCauseMessage(cause));
		
		return sb;
	}
}
